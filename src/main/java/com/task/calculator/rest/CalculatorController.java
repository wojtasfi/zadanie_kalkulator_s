package com.task.calculator.rest;

import com.task.calculator.dto.CountryCostsInformationDto;
import com.task.calculator.dto.CountryCostsInformationUpdateDto;
import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.service.CountryCostsInformationService;
import com.task.calculator.service.SalaryCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("calculator")
public class CalculatorController {
    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private SalaryCalculationService salaryCalculationService;

    @Autowired
    private CountryCostsInformationService countryCostsInformationService;

    @RequestMapping(value = "/calculateSalary", method = POST)
    private ResponseEntity<String> calculateSalary(@RequestBody @Valid SalaryRequestDto salaryRequestDto) {
        LOGGER.info("Received request to calculate salary for {} with daily salary: {}", salaryRequestDto.getCountryCode(), salaryRequestDto.getDailyGrossSalary());

        ResponseEntity<String> response;
        String body;
        if (countryExists(salaryRequestDto)) {
            String salary = salaryCalculationService.calculateSalary(salaryRequestDto).toString();
            response = new ResponseEntity<>(salary, HttpStatus.OK);
        } else {
            body = "Country " + salaryRequestDto.getCountryCode() + " does not exists";
            response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        return response;

    }

    private boolean countryExists(SalaryRequestDto salaryRequestDto) {
        return countryCostsInformationService.findByCountryCode(salaryRequestDto.getCountryCode()) != null;
    }

    @RequestMapping(value = "countries", method = GET)
    private List<String> getAvailableCountryCodes() {
        LOGGER.info("Received request for available country codes");

        return countryCostsInformationService.retrieveAllAvailableCountryCodes();

    }

    @RequestMapping(value = "currencyCode", method = GET)
    private String getCurrencyCode(@RequestParam String countryCode) {
        LOGGER.info("Received request currency code for country code: {}", countryCode);

        return countryCostsInformationService.retrieveCurrencyCode(countryCode);

    }

    @RequestMapping(value = "addCountryCostsInformation", method = POST)
    private ResponseEntity<String> addCountryCostsInformation(@RequestBody @Valid CountryCostsInformationDto dto) {
        LOGGER.info("Received request for adding new CountryCostsInformation with countryCode: {}, currencyCode: {}, fixedCosts: {}, incomeTax {}",
                dto.getCountryCode(), dto.getCurrencyCode(), dto.getFixedCosts(), dto.getIncomeTaxPercentage());

        ResponseEntity<String> response;
        String body;
        if (!countryCostsInformationService.checkIfExists(dto.getCountryCode())) {
            countryCostsInformationService.addNewCountryCodeInformation(dto);
            body = "New costs information has been created";
            response = new ResponseEntity<>(body, HttpStatus.CREATED);
            return response;
        }

        body = "Costs information for " + dto.getCountryCode() + " already exists";
        response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        return response;

    }

    @RequestMapping(value = "updateCountryCostsInformation", method = PUT)
    private ResponseEntity<String> updateCountryCostsInformation(
            @RequestParam String countryCode,
            @RequestBody @Valid CountryCostsInformationUpdateDto dto) {

        LOGGER.info("Received request for update CountryCostsInformation for country {} with countryCode: {}, currencyCode: {}, fixedCosts: {}, incomeTax {}",
                countryCode, dto.getCountryCode(), dto.getCurrencyCode(), dto.getFixedCosts(), dto.getIncomeTaxPercentage());

        ResponseEntity<String> response;
        String body;
        if (countryCostsInformationService.checkIfExists(countryCode)) {
            countryCostsInformationService.updateCountryCodeInformation(countryCode, dto);
            body = "Costs information for " + countryCode + " has been updated";
            response = new ResponseEntity<>(body, HttpStatus.OK);
            return response;
        }

        body = "Costs information for " + countryCode + " does not exists";
        response = new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        return response;

    }
}

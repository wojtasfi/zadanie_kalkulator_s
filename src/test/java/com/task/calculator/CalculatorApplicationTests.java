package com.task.calculator;

import com.task.calculator.dao.CountryCostsInformationRepository;
import com.task.calculator.dto.CountryCostsInformationDto;
import com.task.calculator.dto.SalaryDto;
import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.entity.CountryCostsInformation;
import com.task.calculator.service.CountryCostsInformationService;
import com.task.calculator.service.CurrencyInformationService;
import com.task.calculator.service.SalaryCalculationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorApplicationTests {

    @Autowired
    private CountryCostsInformationRepository repository;

    @Test
    public void calculateSalary() throws Exception {
        //given
        final CurrencyInformationService currencyService = spy(new CurrencyInformationService());
        final CountryCostsInformationService countryService = new CountryCostsInformationService(repository);
        final SalaryCalculationService salaryCalculationService = new SalaryCalculationService(countryService, currencyService);

        String countryCode = "UK";
        Double rate = 4.23;
        Double dailyGrossSalary = 100d;
        Double expectedSalary = 5076d;

        SalaryRequestDto salaryRequestDto = new SalaryRequestDto();
        salaryRequestDto.setCountryCode(countryCode);
        salaryRequestDto.setDailyGrossSalary(dailyGrossSalary);

        //when
        doReturn(rate).when(currencyService).getCurrentCurrencyRate(Mockito.anyString());
        SalaryDto salaryDto = salaryCalculationService.calculateSalary(salaryRequestDto);

        //then
        assertEquals(expectedSalary, salaryDto.getSalary());
    }

    @Test
    public void getAllCountryCodes() {
        //given
        final CountryCostsInformationService countryService = new CountryCostsInformationService(repository);
        List<String> expectedCountryCodes = Arrays.asList("UK", "PL", "DE");

        //when
        List<String> countryCodes = countryService.retrieveAllAvailableCountryCodes();

        //then
        assertTrue(countryCodes.containsAll(expectedCountryCodes));
    }

    @Test
    public void addNewCostsInformation() {
        //given
        final CountryCostsInformationService countryService = new CountryCostsInformationService(repository);
        String countryCode = "US";
        String currencyCode = "USD";
        Double taxPercentage = 0.24;
        Integer fixedCosts = 250;

        CountryCostsInformationDto countryCostsInformationDto = new CountryCostsInformationDto();
        countryCostsInformationDto.setCountryCode(countryCode);
        countryCostsInformationDto.setFixedCosts(fixedCosts);
        countryCostsInformationDto.setCurrencyCode(currencyCode);
        countryCostsInformationDto.setIncomeTaxPercentage(taxPercentage);

        //when
        countryService.addNewCountryCodeInformation(countryCostsInformationDto);
        CountryCostsInformation countryInfo = repository.findByCountryCode(countryCode);

        //then
        assertEquals(countryInfo.getCountryCode(), countryCode);
        assertEquals(countryInfo.getCurrencyCode(), currencyCode);
        assertEquals(countryInfo.getIncomeTaxPercentage(), taxPercentage);
        assertEquals(countryInfo.getFixedCosts(), fixedCosts);

    }
}

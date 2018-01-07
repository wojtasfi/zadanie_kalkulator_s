package com.task.calculator.rest;

import com.task.calculator.dto.SalaryDto;
import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.service.SalaryCalculationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("calculator")
public class CalculatorController {
    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorController.class);

    @Autowired
    private SalaryCalculationService salaryCalculationService;

    @RequestMapping(value = "/calculateSalary", method = POST)
    private SalaryDto calculateSalary(@RequestBody @Valid SalaryRequestDto salaryRequestDto) {
        LOGGER.info("Received request to calculate salary for {} with daily salary: {}", salaryRequestDto.getCountryCode(), salaryRequestDto.getDailyGrossSalary());

        return salaryCalculationService.calculateSalary(salaryRequestDto);

    }
}

package com.task.calculator;

import com.task.calculator.dao.CountryCostsInformationRepository;
import com.task.calculator.dto.SalaryDto;
import com.task.calculator.dto.SalaryRequestDto;
import com.task.calculator.service.CountryCostsInformationService;
import com.task.calculator.service.CurrencyInformationService;
import com.task.calculator.service.SalaryCalculationService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
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

        doReturn(rate).when(currencyService).getCurrentCurrencyRate(Mockito.anyString());
        SalaryDto salaryDto = salaryCalculationService.calculateSalary(salaryRequestDto);

        //then
        assertEquals(expectedSalary, salaryDto.getSalary());
    }

    @After
    public void clear() {
        repository.deleteAll();
    }

}

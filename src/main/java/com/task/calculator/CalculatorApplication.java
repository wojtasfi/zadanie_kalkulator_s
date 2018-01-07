package com.task.calculator;

import com.task.calculator.dao.CountryCostsInformationRepository;
import com.task.calculator.entity.CountryCostsInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@SpringBootApplication
public class CalculatorApplication implements ApplicationRunner {

    private static Logger LOGGER = LoggerFactory.getLogger(CalculatorApplication.class);

    @Autowired
    private CountryCostsInformationRepository countryCostsInformationRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        CountryCostsInformation UK = new CountryCostsInformation();
        UK.setCountryCode("UK");
        UK.setCurrencyCode("GBP");
        UK.setFixedCosts(600);
        UK.setIncomeTaxPercentage(0.25);

        CountryCostsInformation DE = new CountryCostsInformation();
        DE.setCountryCode("DE");
        DE.setCurrencyCode("EUR");
        DE.setFixedCosts(800);
        DE.setIncomeTaxPercentage(0.2);

        CountryCostsInformation PL = new CountryCostsInformation();
        PL.setCountryCode("PL");
        PL.setCurrencyCode("PLN");
        PL.setFixedCosts(1200);
        PL.setIncomeTaxPercentage(0.19);

        String db = jdbcTemplate.getDataSource().getConnection().getMetaData().getUserName();
        LOGGER.info("Adding provided data to {}", db);
        countryCostsInformationRepository.save(Arrays.asList(UK, DE, PL));

    }
}

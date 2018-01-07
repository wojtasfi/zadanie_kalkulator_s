package com.task.calculator.service;

import com.task.calculator.dao.CountryCostsInformationRepository;
import com.task.calculator.entity.CountryCostsInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryCostsInformationService {

    @Autowired
    private CountryCostsInformationRepository countryCostsInformationRepository;

    public CountryCostsInformation findByCountryCode(String countryCode) {
        return countryCostsInformationRepository.findByCountryCode(countryCode);
    }
}

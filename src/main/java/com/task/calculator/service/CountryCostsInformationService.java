package com.task.calculator.service;

import com.task.calculator.dao.CountryCostsInformationRepository;
import com.task.calculator.dto.CountryCostsInformationDto;
import com.task.calculator.entity.CountryCostsInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryCostsInformationService {


    private CountryCostsInformationRepository countryCostsInformationRepository;

    @Autowired
    public CountryCostsInformationService(CountryCostsInformationRepository countryCostsInformationRepository) {
        this.countryCostsInformationRepository = countryCostsInformationRepository;
    }

    public CountryCostsInformation findByCountryCode(String countryCode) {
        return countryCostsInformationRepository.findByCountryCode(countryCode);
    }

    public List<String> retrieveAllAvailableCountryCodes() {

        return countryCostsInformationRepository.findAll().stream()
                .map(CountryCostsInformation::getCountryCode)
                .distinct()
                .collect(Collectors.toList());

    }

    public void addNewCountryCodeInformation(CountryCostsInformationDto dto) {
        CountryCostsInformation countryCostsInformation = new CountryCostsInformation();
        countryCostsInformation.setCountryCode(dto.getCountryCode());
        countryCostsInformation.setFixedCosts(dto.getFixedCosts());
        countryCostsInformation.setCurrencyCode(dto.getCurrencyCode());
        countryCostsInformation.setIncomeTaxPercentage(dto.getIncomeTaxPercentage());

        countryCostsInformationRepository.save(countryCostsInformation);
    }

    public boolean checkIfAlreadyExists(CountryCostsInformationDto dto) {

        List<String> existingCodes = countryCostsInformationRepository.findAll().stream()
                .map(CountryCostsInformation::getCountryCode)
                .distinct()
                .collect(Collectors.toList());

        return existingCodes.contains(dto.getCountryCode());
    }
}

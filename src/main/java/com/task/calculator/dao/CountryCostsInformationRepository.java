package com.task.calculator.dao;

import com.task.calculator.entity.CountryCostsInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryCostsInformationRepository extends JpaRepository<CountryCostsInformation, Long> {

    CountryCostsInformation findByCountryCode(String countryCode);
}

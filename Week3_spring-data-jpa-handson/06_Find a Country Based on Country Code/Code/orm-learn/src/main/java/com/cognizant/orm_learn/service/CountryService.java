package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.service.exception.CountryNotFoundException;

import java.util.List;

public interface CountryService {
    Country findCountryByCode(String code) throws CountryNotFoundException;
    List<Country> getAllCountries();
    Country addCountry(Country country);
    Country updateCountry(Country country);
    void deleteCountry(String code);
    List<Country> findByPartialName(String name);
}

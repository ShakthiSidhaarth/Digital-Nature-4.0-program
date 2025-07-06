package com.cognizant.ormlearn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@RestController
@RequestMapping("/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;


    @GetMapping("/{code}")
    public Country getCountry(@PathVariable String code) {
        return countryService.findCountryByCode(code);
    }

    @PostMapping
    public Country addCountry(@RequestBody Country country) {
        return countryService.addCountry(country);
    }

    @PutMapping
    public Country updateCountry(@RequestBody Country country) {
        return countryService.updateCountry(country);
    }

    @DeleteMapping("/{code}")
    public void deleteCountry(@PathVariable String code) {
        countryService.deleteCountry(code);
    }

    @GetMapping("/search")
    public List<Country> searchCountries(@RequestParam String name) {
        return countryService.findByPartialName(name);
    }

    @GetMapping
    public List<Country> getCountries(){
        return countryService.getCountries();
    }
}

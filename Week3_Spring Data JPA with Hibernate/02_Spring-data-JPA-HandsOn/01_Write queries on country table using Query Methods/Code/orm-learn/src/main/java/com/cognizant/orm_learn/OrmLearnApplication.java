package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Country;
import com.cognizant.orm_learn.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

	@Autowired
	private CountryRepository countryRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		displayAllCountries();
		searchByKeyword();
		searchByKeywordSorted();
		searchByStartingLetter();
	}

	private void displayAllCountries() {
		System.out.println("\nAll Countries:");
		List<Country> countries = countryRepository.findAll();
		countries.forEach(System.out::println);
	}

	private void searchByKeyword() {
		System.out.println("\nCountries containing 'ou':");
		List<Country> countries = countryRepository.findByNameContainingIgnoreCase("ou");
		countries.forEach(System.out::println);
	}

	private void searchByKeywordSorted() {
		System.out.println("\nCountries containing 'ou' (sorted):");
		List<Country> countries = countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc("ou");
		countries.forEach(System.out::println);
	}

	private void searchByStartingLetter() {
		System.out.println("\nCountries starting with 'Z':");
		List<Country> countries = countryRepository.findByNameStartingWithIgnoreCase("Z");
		countries.forEach(System.out::println);
	}
}

package com.cognizant.orm_learn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cognizant.orm_learn.model.Country;

public interface CountryRepository extends JpaRepository<Country, String> {

    List<Country> findByNameContainingIgnoreCase(String keyword);

    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String keyword);

    List<Country> findByNameStartingWithIgnoreCase(String prefix);
}

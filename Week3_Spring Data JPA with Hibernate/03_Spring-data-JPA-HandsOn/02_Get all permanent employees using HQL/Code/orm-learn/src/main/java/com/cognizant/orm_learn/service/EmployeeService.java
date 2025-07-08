package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllPermanentEmployees();
}

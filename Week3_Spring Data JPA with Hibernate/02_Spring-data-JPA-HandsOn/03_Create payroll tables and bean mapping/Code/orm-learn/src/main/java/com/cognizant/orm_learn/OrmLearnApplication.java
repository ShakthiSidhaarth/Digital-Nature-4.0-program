package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) {
		List<Employee> employees = employeeRepository.findAll();
		for (Employee e : employees) {
			System.out.println("==================================");
			System.out.println("Employee: " + e);
			System.out.println("Department: " + e.getDepartment());
			System.out.println("Skills: " + e.getSkills());
			System.out.println("==================================");
		}
	}
}

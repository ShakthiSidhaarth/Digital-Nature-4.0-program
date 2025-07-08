package com.cognizant.orm_learn;

import com.cognizant.orm_learn.model.Department;
import com.cognizant.orm_learn.model.Employee;
import com.cognizant.orm_learn.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;

import java.util.Set;

@SpringBootApplication
@EntityScan("com.cognizant.orm_learn.model")
public class OrmLearnApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		testGetDepartment(context);
	}

	public static void testGetDepartment(ApplicationContext context) {
		DepartmentService departmentService = context.getBean(DepartmentService.class);

		Department department = departmentService.get(1);
		System.out.println("Department: " + department);

		Set<Employee> employeeList = department.getEmployeeList();
		System.out.println("Employees in Department:");
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
	}
}

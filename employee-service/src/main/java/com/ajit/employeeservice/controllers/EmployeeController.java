package com.ajit.employeeservice.controllers;

import com.ajit.employeeservice.models.Employee;
import com.ajit.employeeservice.repositories.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeRepository employeeRepository;

    @PostMapping
    public Employee add(@RequestBody Employee employee) {

        logger.info("Employee add");
        return employeeRepository.addEmployee(employee);
    }

    @GetMapping
    public List<Employee> findAll() {
        logger.info("Employee find all");
        return employeeRepository.findAll();
    }


    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        logger.info("Employee find by id{}",id);
        return employeeRepository.findById(id);
    }

    @GetMapping("/department/{departmentId}")
    public List<Employee> findDepartment(@PathVariable Long departmentId) {

        return employeeRepository.findByDepartmentId(departmentId);
    }


}

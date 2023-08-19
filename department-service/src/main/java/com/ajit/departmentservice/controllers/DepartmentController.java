package com.ajit.departmentservice.controllers;

import com.ajit.departmentservice.clients.EmployeeClient;
import com.ajit.departmentservice.models.Department;
import com.ajit.departmentservice.repositories.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {


    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeClient employeeClient;


    @PostMapping
    public Department add(@RequestBody Department department) {

        logger.info("Department add");
        return departmentRepository.addDepartment(department);
    }

    @GetMapping
    public List<Department> findAll() {
        logger.info("Department find all");
        return departmentRepository.findAll();
    }

    @GetMapping("/{id}")
    public Department findById(@PathVariable Long id) {
        logger.info("Department find by id{}",id);
        return departmentRepository.findById(id);
    }

    @GetMapping("/with-employees")
    public List<Department> findWithAllEmployees() {
        logger.info("Department with-employees all");
        List<Department> departments = departmentRepository.findAll();
        departments.forEach(department -> department
                .setEmployees(employeeClient.findDepartment(department.getId())));
        return departments;
    }


}

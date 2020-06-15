package com.bealdung.onboarding.adapters.controller;

import com.bealdung.onboarding.application.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/employee")
public class HexagonalArchitectureController {
    private EmployeeService employeeService;

    public HexagonalArchitectureController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/find")
    public String findEmployee(@RequestParam Long id) {
        return employeeService.getNameAndFamily(id);
    }
}

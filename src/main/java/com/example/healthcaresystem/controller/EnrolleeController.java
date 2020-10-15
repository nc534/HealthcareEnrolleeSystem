package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Enrollee;
import com.example.healthcaresystem.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;

    @GetMapping("/enrollees")
    public List<Enrollee> getAllEnrollees() {
        return enrolleeService.enrolleeRepository.findAll();
    }
}

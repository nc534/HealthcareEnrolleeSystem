package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.model.Enrollee;
import com.example.healthcaresystem.service.DependentService;
import com.example.healthcaresystem.service.EnrolleeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;

    //get all enrollees
    @GetMapping("/enrollees")
    public List<Enrollee> getAllEnrollees() {
        return enrolleeService.getAllEnrollees();
    }

    //get an enrollee
    @GetMapping("/enrollee/{enrolleeId}")
    public Optional<Enrollee> getEnrolleeById(@PathVariable String enrolleeId) {
        return enrolleeService.getEnrolleeById(enrolleeId);
    }

    //add a new enrollee
    @PostMapping("/enrollees")
    public Enrollee createEnrollee(@RequestBody Enrollee newEnrollee) {
        return enrolleeService.createEnrollee(newEnrollee);
    }

    //modify an enrollee
    @PutMapping("/enrollee/{enrolleeId}")
    public String updateEnrollee(@RequestBody Enrollee updatedEnrollee, @PathVariable String enrolleeId) {
        return enrolleeService.updateEnrollee(updatedEnrollee, enrolleeId);
    }

    //remove an enrollee
    @DeleteMapping("/enrollee/{enrolleeId}")
    public String deleteEnrollee(@PathVariable String enrolleeId) {
        return enrolleeService.deleteEnrollee(enrolleeId);
    }

}

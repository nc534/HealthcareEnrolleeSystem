package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.service.DependentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
public class DependentController {

    @Autowired
    DependentService dependentService;

    //get all dependents by enrollee
    @GetMapping("/enrollee/{enrolleeId}/dependents")
    public List<Dependent> getDependents(@PathVariable String enrolleeId) {
        return dependentService.getDependents(enrolleeId);
    }

    //get a dependent to an enrollee
    @GetMapping("/enrollee/{enrolleeId}/dependent/{dependentId}")
    public Optional<Dependent> getDependent(@PathVariable String enrolleeId, @PathVariable String dependentId) {
        return dependentService.getDependent(enrolleeId, dependentId);
    }

    //add a dependent to an enrollee
    @PostMapping("/enrollee/{enrolleeId}/dependent")
    public Dependent createDependent(@RequestBody Dependent newDependent, @PathVariable String enrolleeId) {
        return dependentService.createDependent(enrolleeId, newDependent);
    }

    //modify a dependent
    @PutMapping("/enrollee/{enrolleeId}/dependent/{dependentId}")
    public String updateDependent(@RequestBody Dependent newDependent, @PathVariable String enrolleeId, @PathVariable String dependentId) {
        return dependentService.updateDependent(newDependent, enrolleeId, dependentId);
    }

    //delete a dependent
    @DeleteMapping("/enrollee/{enrolleeId}/dependent/{dependentId}")
    public String deleteDependent(@PathVariable String enrolleeId, @PathVariable String dependentId) {
        return dependentService.deleteDependent(enrolleeId, dependentId);
    }
}

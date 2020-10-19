package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Enrollee;
import com.example.healthcaresystem.service.EnrolleeService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EnrolleeController {

    @Autowired
    EnrolleeService enrolleeService;

    //get all enrollees
    @GetMapping(value = "/enrollee", produces = "application/json")
    public List<Enrollee> getAllEnrollees() {
        return enrolleeService.getAllEnrollees();
    }

    //get an enrollee
    @GetMapping(value = "/enrollee/{enrolleeId}", produces = "application/json")
    public Optional<Enrollee> getEnrolleeById(@ApiParam(value = "Get the specific  enrollee using the enrollee's id", required = true)
                                                @PathVariable String enrolleeId) {
        return enrolleeService.getEnrolleeById(enrolleeId);
    }

    //add a new enrollee
    @PostMapping(value = "/enrollee", produces = "application/json")
    public Enrollee createEnrollee(@ApiParam(value = "All required fields and any optional fields of the new enrollee in JSON format", required = true)
                                       @RequestBody Enrollee newEnrollee) {
        return enrolleeService.createEnrollee(newEnrollee);
    }

    //modify an enrollee
    @PutMapping(value = "/enrollee/{enrolleeId}",  consumes = "application/json", produces = "text/plain")
    public String updateEnrollee(@ApiParam(value = "All fields of the existing enrollee with the changed value(s) in JSON format", required = true)
                                    @RequestBody Enrollee updatedEnrollee,
                                 @ApiParam(value = "Modify the specific enrollee using the enrollee's id", required = true)
                                    @PathVariable String enrolleeId) {
        return enrolleeService.updateEnrollee(updatedEnrollee, enrolleeId);
    }

    //remove an enrollee
    @DeleteMapping(value = "/enrollee/{enrolleeId}", produces = "text/plain")
    public String deleteEnrollee(@ApiParam(value = "Delete the specific enrollee using the enrollee's id", required = true)
                                    @PathVariable String enrolleeId) {
        return enrolleeService.deleteEnrollee(enrolleeId);
    }

}

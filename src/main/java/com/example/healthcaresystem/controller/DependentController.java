package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.service.DependentService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DependentController {

    @Autowired
    DependentService dependentService;

    //get all dependents by enrollee
    @GetMapping(value = "/enrollee/{enrolleeId}/dependents", produces = "application/json")
    public List<Dependent> getDependents(@ApiParam(value = "Get the dependents of an enrollee using the enrollee's id", required = true)
                                             @PathVariable String enrolleeId) {
        return dependentService.getDependents(enrolleeId);
    }

    //get a dependent to an enrollee
    @GetMapping(value = "/enrollee/{enrolleeId}/dependent/{dependentId}", produces = "application/json")
    public Optional<Dependent> getDependent(@ApiParam(value = "Get the specific dependent of an enrollee using the enrollee's id", required = true)
                                                @PathVariable String enrolleeId,
                                            @ApiParam(value = "Get the specific dependent with it's id", required = true)
                                                @PathVariable String dependentId) {
        return dependentService.getDependent(enrolleeId, dependentId);
    }

    //add a dependent to an enrollee
    @PostMapping(value = "/enrollee/{enrolleeId}/dependent", produces = "application/json")
    public Dependent createDependent(@ApiParam(value = "Fields of the new dependent in JSON format", required = true)
                                        @RequestBody Dependent newDependent,
                                     @ApiParam(value = "Add a dependent to the enrollee with the enrollee's id", required = true)
                                        @PathVariable String enrolleeId) {
        return dependentService.createDependent(enrolleeId, newDependent);
    }

    //modify a dependent
    @PutMapping(value = "/enrollee/{enrolleeId}/dependent/{dependentId}", produces = "text/plain")
    public String updateDependent(@ApiParam(value = "All fields of the existing dependent with the changed value(s) in JSON format", required = true)
                                    @RequestBody Dependent newDependent,
                                  @ApiParam(value = "Modify the specific dependent of an enrollee using the enrollee's id", required = true)
                                    @PathVariable String enrolleeId, @PathVariable String dependentId) {
        return dependentService.updateDependent(newDependent, enrolleeId, dependentId);
    }

    //remove a dependent
    @DeleteMapping(value = "/enrollee/{enrolleeId}/dependent/{dependentId}", produces = "text/plain")
    public String deleteDependent(@ApiParam(value = "Delete the specific dependent of an enrollee using the enrollee's id", required = true)
                                    @PathVariable String enrolleeId,
                                  @ApiParam(value = "Delete the specific dependent with it's id", required = true)
                                    @PathVariable String dependentId) {
        return dependentService.deleteDependent(enrolleeId, dependentId);
    }
}

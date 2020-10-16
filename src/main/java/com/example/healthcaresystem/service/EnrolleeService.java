package com.example.healthcaresystem.service;

import com.example.healthcaresystem.dao.EnrolleeRepository;
import com.example.healthcaresystem.model.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrolleeService {

    @Autowired
    private EnrolleeRepository enrolleeRepository;

    //get all enrollees
    public List<Enrollee> getAllEnrollees() {
        return enrolleeRepository.findAll();
    }

    //get an enrollee
    public Optional<Enrollee> getEnrolleeById(String enrolleeId) {
        return enrolleeRepository.findById(enrolleeId);
    }

    //add a new enrollee
    //ToDo: check if enrollee already exists
    public Enrollee createEnrollee(Enrollee newEnrollee) {
        return enrolleeRepository.save(newEnrollee);
    }

    //modify an enrollee
    public String updateEnrollee(Enrollee updatedEnrollee, String enrolleeId) {
        //check if enrollee exists
        Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeId);

        if(enrollee.isPresent()) {
            //set id so it will not create a new enrollee
            updatedEnrollee.setId(enrolleeId);
            enrolleeRepository.save(updatedEnrollee);
            return "Enrollee with the id " + enrolleeId + " is updated.";
        }else{
            return "Failed to update. Enrollee with the id " + enrolleeId + " does not exist.";
        }
    }

    //remove an enrollee
    public String deleteEnrollee(String enrolleeId) {
        //check if enrollee exists
        Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeId);

        if(enrollee.isPresent()) {
            enrolleeRepository.deleteById(enrolleeId);
            return "Enrollee with the id " + enrolleeId + " is deleted.";
        }else{
            return "Failed to delete. Enrollee with the id " + enrolleeId + " does not exist.";
        }
    }

}

package com.example.healthcaresystem.service;

import com.example.healthcaresystem.dao.EnrolleeRepository;
import com.example.healthcaresystem.model.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class EnrolleeService {

    @Autowired
    public EnrolleeRepository enrolleeRepository;

}

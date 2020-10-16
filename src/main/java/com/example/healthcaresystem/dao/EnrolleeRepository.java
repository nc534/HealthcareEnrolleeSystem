package com.example.healthcaresystem.dao;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.model.Enrollee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrolleeRepository extends MongoRepository<Enrollee, String> {
}

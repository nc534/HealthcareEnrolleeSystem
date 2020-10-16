package com.example.healthcaresystem.dao;

import com.example.healthcaresystem.model.Dependent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DependentRepository extends MongoRepository<Dependent, String> {

}

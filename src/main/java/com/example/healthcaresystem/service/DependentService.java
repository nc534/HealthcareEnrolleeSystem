package com.example.healthcaresystem.service;

import com.example.healthcaresystem.dao.DependentRepository;
import com.example.healthcaresystem.dao.EnrolleeRepository;
import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.model.Enrollee;
import com.mongodb.BasicDBObject;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DependentService {

    @Autowired
    private EnrolleeRepository enrolleeRepository;

    @Autowired
    private DependentRepository dependentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    //get all dependents by enrollee
    public List<Dependent> getDependents(String enrolleeId) {
        Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeId);

        return enrollee.map(Enrollee::getDependent).orElse(null);
    }

    //get a dependent
    public Optional<Dependent> getDependent(String enrolleeId, String dependentId) {

        //find enrollee with the dependent
        Enrollee enrollee = mongoTemplate.findOne(new Query(Criteria.where("dependent.id").is(dependentId)), Enrollee.class);

        //return the dependent
        return enrollee != null ? enrollee.getDependent().stream().filter(d -> d.getId().equals(dependentId)).findAny() : Optional.empty();
    }

    //add a dependent to an enrollee
    //ToDo: check if dependent already exists
    public Dependent createDependent(String enrolleeId, Dependent newDependent) {

        //check if enrollee exists
        Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeId);

        if(enrollee.isPresent()) {
            //create and set an ObjectId for the new dependent
            newDependent.setId(String.valueOf(new ObjectId()));
            List<Dependent> newDependentList = enrollee.get().getDependent();

            //create a collection if enrollee does not have a dependent yet
            if(newDependentList == null) {
                newDependentList = new ArrayList<>();
            }

            //add new dependent and save enrollee with new dependent
            newDependentList.add(newDependent);
            enrollee.get().setDependent(newDependentList);
            enrolleeRepository.save(enrollee.get());
            return newDependent;
        }else{
            return null;
        }
    }

    //modify a dependent
    public String updateDependent(Dependent updatedDependent, String enrolleeId, String dependentId) {
        //find enrollee with the dependent
        Query query = new Query().addCriteria(Criteria.where("id").is(enrolleeId).and("dependent.id").is(dependentId));

        //update dependent fields
        Update update = new Update()
                .set("dependent.$.name", updatedDependent.getName())
                .set("dependent.$.dateOfBirth", updatedDependent.getDateOfBirth());

        UpdateResult result = mongoTemplate.updateFirst(query, update, Enrollee.class);

        if(result.wasAcknowledged()) {
            return "Dependent with the id " + dependentId + " is updated.";
        }else{
            return "Failed to update. Dependent with the id " + dependentId + " does not exist.";
        }
    }

    //remove a dependent
    public String deleteDependent(String enrolleeId, String dependentId) {

        //check if enrollee exists
        Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeId);
        //get dependent of enrollee
        Optional<Dependent> dependent = getDependent(enrolleeId, dependentId);

        if(enrollee.isPresent()) {
            //remove if enrollee has the dependent
            if(dependent.isPresent()) {
                Update update = new Update()
                        .pull("dependent", new BasicDBObject("id", dependentId));

                UpdateResult result = mongoTemplate.updateMulti(new Query(), update, Enrollee.class);

                if(result.wasAcknowledged()) {
                    return "Dependent with the id " + dependentId + " is deleted.";
                }else{
                    return "Failed to delete. Dependent with the id " + dependentId + " does not exist.";
                }
            }else{
                return "Failed to delete. Enrollee with the id " + enrolleeId + " does not have a dependent with the id " + dependentId + ".";
            }
        }else{
            return "Failed to delete. Enrollee with the id " + enrolleeId + " does not exist.";
        }

    }
}

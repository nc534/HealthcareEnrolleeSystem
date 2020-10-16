package com.example.healthcaresystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "enrollee")
public class Enrollee {

    @Id
    private String id;
    private String name;
    private String phone;
    @Field("activation_status")
    private boolean activationStatus;
    @Field("date_of_birth")
    private String dateOfBirth;
    private List<Dependent> dependent;

    public Enrollee(String name, String phone, boolean activationStatus, String dateOfBirth, List<Dependent> dependent) {
        this.name = name;
        this.phone = phone;
        this.activationStatus = activationStatus;
        this.dateOfBirth = dateOfBirth;
        this.dependent = dependent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Dependent> getDependent() {
        return dependent;
    }

    public void setDependent(List<Dependent> dependent) {
        this.dependent = dependent;
    }
}

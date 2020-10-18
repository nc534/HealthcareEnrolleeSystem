package com.example.healthcaresystem.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "enrollee")
public class Enrollee {

    @Id
    @ApiModelProperty(notes = "The auto-generated unique ObjectId of the enrollee", required = true, example = "5f87640ea7923d74cc279026")
    private String id;
    @ApiModelProperty(notes = "The full name of the enrollee", required = true, example = "John Smith")
    private String name;
    @ApiModelProperty(notes = "The phone number of the enrollee (###-###-####)", example = "223-456-7890")
    private String phone;
    @Field("activation_status")
    @ApiModelProperty(notes = "The activation status of the enrollee", required = true)
    private boolean activationStatus;
    @Field("date_of_birth")
    @ApiModelProperty(notes = "The birthdate of the enrollee (yyyy-mm-dd)", required = true, example = "1992-11-30")
    private String dateOfBirth;
    @ApiModelProperty(notes = "The dependents of the enrollee")
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

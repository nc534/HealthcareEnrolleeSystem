package com.example.healthcaresystem.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.mongodb.core.mapping.Field;

public class Dependent {

    @ApiModelProperty(notes = "The auto-generated unique ObjectId of the dependent", required = true, example = "5f87640ea7523d74ec239028")
    private String id;
    @ApiModelProperty(notes = "The full name of the dependent", required = true, example = "Billy Smith")
    private String name;
    @Field("date_of_birth")
    @ApiModelProperty(notes = "The birthdate of the dependent (yyyy-mm-dd)", required = true, example = "2015-02-25")
    private String dateOfBirth;

    public Dependent(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

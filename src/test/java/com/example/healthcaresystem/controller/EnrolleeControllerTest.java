package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.model.Enrollee;
import com.example.healthcaresystem.service.EnrolleeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EnrolleeController.class)
class EnrolleeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EnrolleeService enrolleeService;

    private static Enrollee enrollee;
    private static List<Enrollee> enrolleeList = new ArrayList<>();

    @BeforeAll
    static void setUp() {

        List<Dependent> dependents = new ArrayList<>();
        dependents.add(new Dependent("Sue Bond", "2015-03-15"));
        dependents.add(new Dependent("Angie Bond", "2018-09-27"));
        enrollee = new Enrollee("James Bond", "222-333-4444", true, "1994-07-30", dependents);
        enrollee.setId(String.valueOf(new ObjectId("5f87640ea7923d74cc279026")));

        enrolleeList.add(enrollee);

    }

    @Test
    void getAllEnrollees() throws Exception {

        when(enrolleeService.getAllEnrollees()).thenReturn(enrolleeList);
        mockMvc.perform(get("/enrollee")).andExpect(status().isOk()).andExpect(content().contentType("application/json"));
    }

    @Test
    void getExistingEnrolleeById() throws Exception {
        String existingEnrolleeId = "5f87640ea7923d74cc279026";

        when(enrolleeService.getEnrolleeById(anyString())).thenReturn(java.util.Optional.ofNullable(enrollee));

        mockMvc.perform(get("/enrollee/{enrolleeId}", existingEnrolleeId))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));
    }

    @Test
    void getNonExistingEnrolleeById() throws Exception {
        String nonExistingEnrolleeId = "123456abc";

        when(enrolleeService.getEnrolleeById(nonExistingEnrolleeId)).thenReturn(null);

        mockMvc.perform(get("/enrollee/{enrolleeId}", nonExistingEnrolleeId))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$").doesNotExist());

    }

    @Test
    void createEnrollee() throws Exception {

        Enrollee newEnrollee = new Enrollee();
        newEnrollee.setId(String.valueOf(new ObjectId("5f87680da7913e74bc239125")));
        newEnrollee.setName("William Jones");
        newEnrollee.setDateOfBirth("1999-04-19");
        newEnrollee.setActivationStatus(false);

        when(enrolleeService.createEnrollee(any(Enrollee.class))).thenReturn(newEnrollee);

        mockMvc.perform(post("/enrollee")
                        .characterEncoding("utf-8")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newEnrollee))
                        .accept(MediaType.APPLICATION_JSON))
                            .andExpect(status().isOk())
                            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                            .andExpect(jsonPath("id").value(newEnrollee.getId()));

    }

    @Test
    void updateExistingEnrollee() throws Exception {
        String enrolleeId = "5f87640ea7923d74cc279026";

        Enrollee updatedEnrollee = enrollee;
        updatedEnrollee.setActivationStatus(false);

//        when(enrolleeService.updateEnrollee(updatedEnrollee, enrolleeId)).thenReturn("Enrollee with the id" + updatedEnrollee.getId() + " is updated.");
//
//        mockMvc.perform(put("/enrollee/{enrolleeId}", enrolleeId)
//                        .characterEncoding("utf-8")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(updatedEnrollee)))
//                            .andExpect(status().isOk())
//                            .andExpect(content().string("Enrollee with the id 5f87640ea7923d74cc279026 is updated."));
    }

    @Test
    void updateNonExistingEnrollee() throws Exception {
        String nonExistingEnrolleeId = "123456abc";


    }

    @Test
    void deleteExistingEnrollee() throws Exception {
        String existingEnrolleeId = "5f87640ea7923d74cc279026";

        when(enrolleeService.deleteEnrollee(existingEnrolleeId)).thenReturn("Enrollee with the id " + existingEnrolleeId + " is deleted.");

        mockMvc.perform(delete("/enrollee/{enrolleeId}", existingEnrolleeId))
                        .andExpect(status().isOk())
                        .andExpect(content().string("Enrollee with the id 5f87640ea7923d74cc279026 is deleted."));

    }

    @Test
    void deleteNonExistingEnrollee() throws Exception {
        String nonExistingEnrolleeId = "123456abc";

        when(enrolleeService.deleteEnrollee(nonExistingEnrolleeId)).thenReturn("Failed to delete. Enrollee with the id " + nonExistingEnrolleeId + " does not exist.");

        mockMvc.perform(delete("/enrollee/{enrolleeId}", nonExistingEnrolleeId))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed to delete. Enrollee with the id 123456abc does not exist."));
    }
}
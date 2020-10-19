package com.example.healthcaresystem.controller;

import com.example.healthcaresystem.model.Dependent;
import com.example.healthcaresystem.model.Enrollee;
import com.example.healthcaresystem.service.DependentService;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = DependentController.class)
class DependentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DependentService dependentService;

    private static Enrollee enrollee;
    private static Dependent dependent;
    private static List<Dependent> dependents;

    @BeforeAll
    static void setUp() {

        dependent = new Dependent("Sue Bond", "2015-03-15");
        dependent.setId(String.valueOf(new ObjectId("5e69840fa6913d74dc279125")));

        dependents = new ArrayList<>();
        dependents.add(dependent);
        enrollee = new Enrollee("James Bond", "222-333-4444", true, "1994-07-30", dependents);
        enrollee.setId(String.valueOf(new ObjectId("5f87640ea7923d74cc279026")));

    }

    @Test
    void getDependentsOfExistingEnrollee() throws Exception {

        String existingEnrolleeId = "5f87640ea7923d74cc279026";

        when(dependentService.getDependents(existingEnrolleeId)).thenReturn(dependents);

        mockMvc.perform(get("/enrollee/{enrolleeId}/dependents", existingEnrolleeId))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));
    }

    @Test
    void getDependentsOfNonExistingEnrollee() throws Exception {

        String nonExistintEnrolleeId = "14340349fewr";

        when(dependentService.getDependents(nonExistintEnrolleeId)).thenReturn(null);

        mockMvc.perform(get("/enrollee/{enrolleeId}/dependents", nonExistintEnrolleeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());

    }

    @Test
    void getSpecificDependentOfExistingDependent() throws Exception{

        String existingEnrolleeId = "5f87640ea7923d74cc279026";
        String existingDependentId = "5e69840fa6913d74dc279125";

        when(dependentService.getDependent(existingEnrolleeId, dependent.getId())).thenReturn(java.util.Optional.ofNullable(dependent));

        mockMvc.perform(get("/enrollee/{enrolleeId}/dependent/{dependentId}", existingEnrolleeId, existingDependentId))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType("application/json"));

    }

    @Test
    void getSpecificDependentOfNonExistingDependent() throws Exception {
        String existingEnrolleeId = "5f87640ea7923d74cc279026";
        String nonExistingDependentId = "3408294rfka0skf1ds";

        when(dependentService.getDependent(existingEnrolleeId, dependent.getId())).thenReturn(null);

        mockMvc.perform(get("/enrollee/{enrolleeId}/dependent/{dependentId}", existingEnrolleeId, nonExistingDependentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void createDependentOfExistingEnrollee() throws Exception {

        String existingEnrolleeId = "5f87640ea7923d74cc279026";

        Dependent newDependent = new Dependent();
        newDependent.setId(String.valueOf(new ObjectId("5f87680da7913e74bc239125")));
        newDependent.setName("William Jones");
        newDependent.setDateOfBirth("1999-04-19");

        when(dependentService.createDependent(eq(existingEnrolleeId), any(Dependent.class))).thenReturn(newDependent);

        mockMvc.perform(post("/enrollee/{enrolleeId}/dependent", existingEnrolleeId)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDependent))
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("id").value(newDependent.getId()));
    }

    @Test
    void createDependentOfNonExistingEnrollee() throws Exception{

        String nonExistintEnrolleeId = "14340349fewr";

        Dependent newDependent = new Dependent();
        newDependent.setId(String.valueOf(new ObjectId("5f87680da7913e74bc239125")));
        newDependent.setName("William Jones");
        newDependent.setDateOfBirth("1999-04-19");

        when(dependentService.createDependent(eq(nonExistintEnrolleeId), any(Dependent.class))).thenReturn(null);

        mockMvc.perform(post("/enrollee/{enrolleeId}/dependent", nonExistintEnrolleeId)
                .characterEncoding("utf-8")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDependent))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void updateSpecificDependentOfExistingDependent() {

        String existingEnrolleeId = "5f87640ea7923d74cc279026";
    }

    @Test
    void updateSpecificDependentOfNonExistingDependent() {
    }

    @Test
    void deleteSpecificDependentOfExistingDependent() throws Exception {

        String existingEnrolleeId = "5f87640ea7923d74cc279026";
        String existingDependentId = "5e69840fa6913d74dc279125";

        when(dependentService.deleteDependent(existingEnrolleeId, existingDependentId)).thenReturn("Dependent with the id " + existingDependentId + " is deleted.");

        mockMvc.perform(delete("/enrollee/{enrolleeId}/dependent/{dependentId}", existingEnrolleeId, existingDependentId))
                .andExpect(status().isOk())
                .andExpect(content().string("Dependent with the id 5e69840fa6913d74dc279125 is deleted."));
    }

    @Test
    void deleteSpecificDependentOfNonExistingDependent() throws Exception {

        String existingEnrolleeId = "5f87640ea7923d74cc279026";
        String nonExistintEnrolleeId = "14340349fewr";

        when(dependentService.deleteDependent(existingEnrolleeId, nonExistintEnrolleeId)).thenReturn("Failed to delete. Dependent with the id " + nonExistintEnrolleeId + " does not exist.");

        mockMvc.perform(delete("/enrollee/{enrolleeId}/dependent/{dependentId}", existingEnrolleeId, nonExistintEnrolleeId))
                .andExpect(status().isOk())
                .andExpect(content().string("Failed to delete. Dependent with the id 14340349fewr does not exist."));
    }
}
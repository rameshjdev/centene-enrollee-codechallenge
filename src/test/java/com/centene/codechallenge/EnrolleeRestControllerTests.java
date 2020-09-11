package com.centene.codechallenge;

import com.centene.codechallenge.model.Dependent;
import com.centene.codechallenge.model.Enrollee;
import com.centene.codechallenge.repository.DependentRepository;
import com.centene.codechallenge.repository.EnrolleeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 
 * @author ramesh
 * Test class for EnrolleeRestController
 *
 */
@WebMvcTest
public class EnrolleeRestControllerTests {
	
	@Autowired
    private MockMvc mockMvc;
 
    @MockBean
    private EnrolleeRepository repository;
    
    @MockBean
    private DependentRepository dependentRepository;
    
    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testGetEnrollee() throws Exception {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        Optional<Enrollee> enrolleeOptional = Optional.of(enrollee);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(enrolleeOptional);
        mockMvc.perform(get("/enrollees/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Ramesh")));
    }
    
    @Test
    public void testGetEnrollees() throws Exception {
    	List<Enrollee> enrolleeList = new ArrayList<Enrollee>();
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        enrolleeList.add(enrollee);
        Mockito.when(repository.findAll()).thenReturn(enrolleeList);
        mockMvc.perform(get("/enrollees")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.equalTo("Ramesh")));
    }
    
    @Test
    public void testAddEnrollee() throws Exception {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        String json = mapper.writeValueAsString(enrollee);
        Mockito.when(repository.save(Mockito.any(Enrollee.class))).thenReturn(enrollee);
        mockMvc.perform(post("/enrollees").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Ramesh")));
    }
    
    @Test
    public void testUpdateEnrollee() throws Exception {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        Optional<Enrollee> enrolleeOptional = Optional.of(enrollee);
        String json = mapper.writeValueAsString(enrollee);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(enrolleeOptional);
        Mockito.when(repository.save(Mockito.any(Enrollee.class))).thenReturn(enrollee);
        mockMvc.perform(put("/enrollees/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Ramesh")));
    }
    
    @Test
    public void testGetDependent() throws Exception {
    	Dependent dependent = new Dependent();
        dependent.setId(1);
        dependent.setName("Rajesh");
        Optional<Dependent> dependentOptional = Optional.of(dependent);
        Mockito.when(dependentRepository.findById(Mockito.anyLong())).thenReturn(dependentOptional);
        mockMvc.perform(get("/enrollees/1/dependents/1")).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Rajesh")));
    }
    
    @Test
    public void testDeleteEnrollee() throws Exception {
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        doNothing().when(repository).deleteById(Mockito.anyLong());
        mockMvc.perform(delete("/enrollees/1")).andExpect(status().isOk());
    }
    
    @Test
    public void testAddDependent() throws Exception {
        Dependent dependent = new Dependent();
        dependent.setId(1);
        dependent.setName("Rajesh");
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        Optional<Enrollee> enrolleeOptional = Optional.of(enrollee);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(enrolleeOptional);
        String json = mapper.writeValueAsString(dependent);
        Mockito.when(dependentRepository.save(Mockito.any(Dependent.class))).thenReturn(dependent);
        mockMvc.perform(post("/enrollees/1/dependents").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Rajesh")));
    }
    
    @Test
    public void testUpdateDependent() throws Exception {
    	Dependent dependent = new Dependent();
        dependent.setId(1);
        dependent.setName("Rajesh");
        Enrollee enrollee = new Enrollee();
        enrollee.setId(1);
        enrollee.setName("Ramesh");
        Optional<Enrollee> enrolleeOptional = Optional.of(enrollee);
        String json = mapper.writeValueAsString(dependent);
        Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(enrolleeOptional);
        Mockito.when(dependentRepository.save(Mockito.any(Dependent.class))).thenReturn(dependent);
        mockMvc.perform(put("/enrollees/1//dependents/1").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.equalTo("Rajesh")));
    }
    
    @Test
    public void testDeleteDependent() throws Exception {
    	Dependent dependent = new Dependent();
        dependent.setId(1);
        dependent.setName("Rajesh");
        Optional<Dependent> dependentOptional = Optional.of(dependent);
        Mockito.when(dependentRepository.findByIdAndId(Mockito.anyLong(), Mockito.anyLong())).thenReturn(dependentOptional);
        doNothing().when(dependentRepository).deleteById(Mockito.anyLong());
        mockMvc.perform(delete("/enrollees/1/dependents/1")).andExpect(status().isOk());
    }

}

package com.centene.codechallenge.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.centene.codechallenge.model.Dependent;
import com.centene.codechallenge.model.Enrollee;
import com.centene.codechallenge.repository.DependentRepository;
import com.centene.codechallenge.repository.EnrolleeRepository;

/**
 *
 * @author ramesh
 * Description: Rest Controller for Enrollee and Dependent
 *
 */
@RestController
@RequestMapping("/enrollees")
public class EnrolleeRestController {

    @Autowired
    private EnrolleeRepository repository;

    @Autowired
    private DependentRepository dependentRepository;

    /**
     * endpoint to get all Enrolleess
     * @param id
     * @return
     */
    @GetMapping
    public List<Enrollee> getEnrollees() {
    	List<Enrollee> enrolleeList = new ArrayList<>();
    	Iterable<Enrollee> enrolleIterable = repository.findAll();
    	enrolleIterable.forEach(enrolleeList::add);
        return enrolleeList;
    }

    /**
     * endpoint to get Enrollee by Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Enrollee getEnrollee(@PathVariable("id") long id) {
        return repository.findById(id).get();
    }

    /**
     * endpoint to add Enrollee
     * @param enrollee
     * @return
     */
    @PostMapping
    public ResponseEntity<Enrollee> addEnrollee(@RequestBody Enrollee enrollee) {
        return new ResponseEntity<Enrollee>(repository.save(enrollee), HttpStatus.CREATED);
    }

    /**
     * Endpoint to update Enrollee
     * @param id
     * @param enrollee
     * @return
     */
    @PutMapping("/{id}")
    public Enrollee updateEnrolle(@PathVariable("id") long id, @RequestBody Enrollee enrollee) {
        Enrollee existingEnrolee = repository.findById(id).get();
        existingEnrolee.setName(enrollee.getName());
        existingEnrolee.setActivationStatus(enrollee.isActivationStatus());
        existingEnrolee.setDob(enrollee.getDob());
        existingEnrolee.setPhoneNumber(enrollee.getPhoneNumber());
        return repository.save(existingEnrolee);
    }

    /**
     * endpoint to delete Enrollee by id
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteEnrollee(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    /**
     * endpoint to get all dependents for the Enrollee
     * @param id
     * @return
     */
    @GetMapping("{id}/dependents")
    public List<Dependent> getDependents(@PathVariable("id") long id) {
        Optional<Enrollee> enrollee = repository.findById(id);
        return enrollee.get().getDependents();
    }
    /**
     * Endpoint to get Dependent by Id
     * @param id
     * @param dependentId
     * @return
     */
    @GetMapping("/{id}/dependents/{dependentId}")
    public Dependent getDependent(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId) {
        return dependentRepository.findById(dependentId).get();
    }

    /**
     * endpoint to add Dependent
     * @param id
     * @param dependent
     * @return
     * @throws Exception
     */
    @PostMapping("{id}/dependents")
    public ResponseEntity<Dependent> addDependent(@PathVariable("id") long id, @RequestBody Dependent dependent) throws Exception {
    	Optional<Dependent> dependentObj = repository.findById(id).map(enrollee -> {
    		dependent.setEnrollee(enrollee);
            return dependentRepository.save(dependent);
        });
    	return new ResponseEntity<Dependent>(dependentObj.get(), HttpStatus.CREATED);
    }

    /**
     * endpoint to delete dependent by Id
     * @param id
     * @param dependentId
     * @throws Exception
     */
    @DeleteMapping("{id}/dependents/{dependentId}")
    public void deleteDependent(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId) throws Exception {
    	dependentRepository.findByIdAndId(dependentId, id).map(dependent -> {
    		dependentRepository.delete(dependent);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new Exception(
            "Dependent not found with dependentId " + dependentId + " and id " + id));
    }

    /**
     * endpoint to update dependent by Id
     * @param id
     * @param dependentId
     * @param dependent
     * @return
     * @throws Exception
     */
    @PutMapping("{id}/dependents/{dependentId}")
    public Dependent updateDependent(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId, @RequestBody Dependent dependent) throws Exception {
    	return repository.findById(id).map(enrollee -> {
            dependent.setEnrollee(enrollee);
            return dependentRepository.save(dependent);
        }).orElseThrow(() -> new Exception("Enrollee not found"));
    }
}

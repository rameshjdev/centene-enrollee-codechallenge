package com.centene.codechallenge.rest;

import com.centene.codechallenge.EnrolleeRepository;
import com.centene.codechallenge.model.Enrollee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enrollees")
public class EnrolleeRestController {

    @Autowired
    private EnrolleeRepository repository;

    @GetMapping
    public Iterable<Enrollee> getEnrollees() {
        return repository.findAll();
    }

    @PostMapping
    public void addEnrollee(@RequestBody Enrollee enrollee) {
        repository.save(enrollee);
    }

    @PutMapping("/{id}")
    public void updateEnrolle(@PathVariable("id") long id, @RequestBody Enrollee enrollee) {
        Enrollee existingEnrolee = repository.findById(id).get();
        existingEnrolee.setName(enrollee.getName());
        existingEnrolee.setActivationStatus(enrollee.isActivationStatus());
        existingEnrolee.setDob(enrollee.getDob());
        existingEnrolee.setPhoneNumber(enrollee.getPhoneNumber());
        repository.save(existingEnrolee);
    }

    @DeleteMapping("{id}")
    public void deleteEnrollee(@PathVariable("id") long id) {
        repository.deleteById(id);
    }

    @PostMapping("{id}/dependents")
    public void addDependent(@PathVariable("id") long id, @RequestBody List<Enrollee> enrollee) {
        Enrollee existingEnrolee = repository.findById(id).get();
        Iterable<Enrollee> newDependents = repository.saveAll(enrollee);
        List<Enrollee> dependentList = new ArrayList<>();
        if(null == existingEnrolee.getDependents()) {
            existingEnrolee.setDependents(new ArrayList<>());
        }
        newDependents.forEach(dependentList::add);
        existingEnrolee.getDependents().addAll(dependentList);
        repository.save(existingEnrolee);
    }

    @DeleteMapping("{id}/dependents/{dependentId}")
    public void deleteDependent(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId) {
        Enrollee existingEnrolee = repository.findById(id).get();
        List<Enrollee> newDependents = new ArrayList<>();
        for(Enrollee dependent : existingEnrolee.getDependents()) {
            if(dependent.getId() != dependentId) {
                newDependents.add(dependent);
            }
        }
        existingEnrolee.setDependents(newDependents);
        repository.save(existingEnrolee);
    }

    @PutMapping("{id}/dependents/{dependentId}")
    public void updateDependent(@PathVariable("id") long id, @PathVariable("dependentId") long dependentId, @RequestBody Enrollee enrollee) {
        Enrollee existingEnrolee = repository.findById(id).get();
        for(Enrollee dependent : existingEnrolee.getDependents()) {
            if(dependent.getId() == dependentId) {
                dependent.setName(enrollee.getName());
                dependent.setActivationStatus(enrollee.isActivationStatus());
                dependent.setDob(enrollee.getDob());
                dependent.setPhoneNumber(enrollee.getPhoneNumber());
            }
        }
        repository.save(existingEnrolee);
    }
}

package com.centene.codechallenge.model;


import javax.persistence.*;
import java.util.List;

//@Document("enrollee")
@Entity
public class Enrollee {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;

    @Column
    private boolean activationStatus;

    @Column
    private String dob;

    @Column
    private String phoneNumber;

    @Column
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private List<Enrollee> dependents;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActivationStatus() {
        return activationStatus;
    }

    public void setActivationStatus(boolean activationStatus) {
        this.activationStatus = activationStatus;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Enrollee> getDependents() {
        return dependents;
    }

    public void setDependents(List<Enrollee> dependents) {
        this.dependents = dependents;
    }
}

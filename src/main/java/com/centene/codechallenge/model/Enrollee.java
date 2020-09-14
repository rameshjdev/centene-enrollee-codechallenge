package com.centene.codechallenge.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;

//@Document("enrollee")
@Entity
@Table(name = "enrollee")
public class Enrollee {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column
    private boolean activationStatus;

    @Column
    @NotBlank(message = "DOB is mandatory")
    private String dob;

    @Pattern(regexp = "([0-9]{10})")
    @Column
    private String phoneNumber;

    @OneToMany(mappedBy = "enrollee", cascade = {
            CascadeType.ALL
        })
    @JsonManagedReference
    private List<Dependent> dependents;


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

	public List<Dependent> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependent> dependents) {
		this.dependents = dependents;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Enrollee other = (Enrollee) obj;
		if (id != other.id)
			return false;
		return true;
	}



}

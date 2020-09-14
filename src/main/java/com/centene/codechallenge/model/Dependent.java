package com.centene.codechallenge.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

//@Document("enrollee")
@Entity
@Table(name = "dependent")
public class Dependent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column
    @NotBlank(message = "DOB is mandatory")
    private String dob;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name="enrollee_id", nullable=false)
    @JsonBackReference
    private Enrollee enrollee;



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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

	public void setEnrollee(Enrollee enrollee) {
		this.enrollee = enrollee;
	}

	public Enrollee getEnrollee() {
		return enrollee;
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
		Dependent other = (Dependent) obj;
		if (id != other.id)
			return false;
		return true;
	}


}

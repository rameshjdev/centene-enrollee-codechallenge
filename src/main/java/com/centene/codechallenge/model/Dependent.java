package com.centene.codechallenge.model;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

//@Document("enrollee")
@Entity
@Table(name = "dependent")
public class Dependent {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String dob;
    
    @ManyToOne(cascade = CascadeType.ALL)
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
	
	
    
    
    
}

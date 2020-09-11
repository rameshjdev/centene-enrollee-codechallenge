package com.centene.codechallenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.centene.codechallenge.model.Dependent;

public interface DependentRepository extends JpaRepository<Dependent, Long> {
	
	Optional<Dependent> findByIdAndId(Long dependentId, Long id);
}

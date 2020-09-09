package com.centene.codechallenge;

import com.centene.codechallenge.model.Enrollee;
import org.springframework.data.repository.CrudRepository;

public interface EnrolleeRepository extends CrudRepository<Enrollee, Long> {
}

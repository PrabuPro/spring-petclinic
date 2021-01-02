package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.Visit;
import org.springframework.data.repository.CrudRepository;

public interface VisitRepository extends CrudRepository<Visit, Long> {
}

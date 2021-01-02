package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.Speciality;
import org.springframework.data.repository.CrudRepository;

public interface SpecialtyRepository extends CrudRepository<Speciality, Long> {
}

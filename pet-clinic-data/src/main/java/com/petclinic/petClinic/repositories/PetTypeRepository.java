package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}

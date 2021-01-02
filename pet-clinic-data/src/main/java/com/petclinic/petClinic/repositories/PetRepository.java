package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}

package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
}

package com.petclinic.petClinic.repositories;

import com.petclinic.petClinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameContainingIgnoreCase(String anyString);
}

package com.petclinic.petClinic.service;

import com.petclinic.petClinic.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameContainingIgnoreCase(String anyString);
}

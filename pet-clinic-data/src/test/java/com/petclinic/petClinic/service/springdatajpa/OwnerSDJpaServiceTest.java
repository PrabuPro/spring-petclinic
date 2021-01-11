package com.petclinic.petClinic.service.springdatajpa;

import com.petclinic.petClinic.model.Owner;
import com.petclinic.petClinic.repositories.OwnerRepository;
import com.petclinic.petClinic.repositories.PetRepository;
import com.petclinic.petClinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LAST_NAME = "smith";
    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;

    Owner returnOwner;

    @BeforeEach
    void setUp() {
        returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
        when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
        Owner owner = ownerSDJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository).findByLastName(any());

    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = new HashSet<>();
        ownerSet.add(Owner.builder().id(1L).build());
        ownerSet.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(ownerSet);

        Set<Owner> owners = ownerSDJpaService.findAll();

        assertEquals(2, owners.size());

    }


    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(returnOwner));
        Owner owner = ownerSDJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerSDJpaService.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(returnOwner);
        Owner saveOwner = ownerSDJpaService.save(owner);
        assertNotNull(saveOwner);
    }

    @Test
    void delete() {
        ownerSDJpaService.delete(returnOwner);
        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        ownerSDJpaService.deleteById(returnOwner.getId());
        verify(ownerRepository, times(1)).deleteById(any());
    }
}
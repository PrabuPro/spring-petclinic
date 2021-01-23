package com.petclinic.petClinic.controllers;

import com.petclinic.petClinic.model.Owner;
import com.petclinic.petClinic.model.Pet;
import com.petclinic.petClinic.model.PetType;
import com.petclinic.petClinic.service.OwnerService;
import com.petclinic.petClinic.service.PetService;
import com.petclinic.petClinic.service.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @Mock
    PetService petService;

    @Mock
    OwnerService ownerService;

    @Mock
    PetTypeService petTypeService;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    Owner owner;
    Set<PetType> petType;

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();

        petType = new HashSet<>();
        petType.add(PetType.builder().id(1L).build());
        petType.add(PetType.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();

        when(petTypeService.findAll()).thenReturn(petType);
        when(ownerService.findById(anyLong())).thenReturn(owner);
    }

    @Test
    void getAddPetForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void savePet() throws Exception {
        mockMvc.perform(post("/owners/1/pets/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void getUpdatePetForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdatePetForm"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void updatePet() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("types"))
                .andExpect(model().attributeExists("pet"))
                .andExpect(model().attributeExists("owner"));
    }




}
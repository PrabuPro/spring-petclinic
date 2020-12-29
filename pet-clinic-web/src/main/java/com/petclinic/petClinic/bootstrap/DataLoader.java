package com.petclinic.petClinic.bootstrap;

import com.petclinic.petClinic.model.Owner;
import com.petclinic.petClinic.model.Pet;
import com.petclinic.petClinic.model.PetType;
import com.petclinic.petClinic.model.Vet;
import com.petclinic.petClinic.service.OwnerService;
import com.petclinic.petClinic.service.PetTypeService;
import com.petclinic.petClinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType saveCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("address line 1");
        owner1.setCity("city");
        owner1.setTelephone("112233445566");

        Pet mikesPet = new Pet();
        mikesPet.setPetType(saveDogType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthDate(LocalDate.now());
        mikesPet.setName("Rosco");

        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("address line 1");
        owner2.setCity("city");
        owner2.setTelephone("112233445566");

        Pet mikesPet2 = new Pet();
        mikesPet2.setPetType(saveDogType);
        mikesPet2.setOwner(owner1);
        mikesPet2.setBirthDate(LocalDate.now());
        mikesPet2.setName("Rosco");

        owner2.getPets().add(mikesPet2);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}

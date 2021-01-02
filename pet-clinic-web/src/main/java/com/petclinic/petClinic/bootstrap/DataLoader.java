package com.petclinic.petClinic.bootstrap;

import com.petclinic.petClinic.model.*;
import com.petclinic.petClinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtesService specialtesService;
    private final VisitsService visitsService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtesService specialtesService, VisitsService visitsService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtesService = specialtesService;
        this.visitsService = visitsService;
    }

    @Override
    public void run(String... args) throws Exception {

        int count = petTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("cat");
        PetType saveCatType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialtesService.save(radiology);

        Speciality radiology2 = new Speciality();
        radiology2.setDescription("Radiolog2");
        Speciality savedRadiology2 = specialtesService.save(radiology2);

        Speciality radiology3 = new Speciality();
        radiology3.setDescription("Radiolog3");
        Speciality savedRadiology3 = specialtesService.save(radiology3);

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

        Visit catVisit = new Visit();
        catVisit.setPet(mikesPet2);
        catVisit.setDate(LocalDate.now());
        catVisit.setDescription("nice cat");

        visitsService.save(catVisit);

        Vet vet1 = new Vet();
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialitySet().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vet2.getSpecialitySet().add(savedRadiology2);

        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }
}

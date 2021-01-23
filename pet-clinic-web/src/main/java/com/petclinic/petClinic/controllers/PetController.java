package com.petclinic.petClinic.controllers;

import com.petclinic.petClinic.model.Owner;
import com.petclinic.petClinic.model.Pet;
import com.petclinic.petClinic.model.PetType;
import com.petclinic.petClinic.service.OwnerService;
import com.petclinic.petClinic.service.PetService;
import com.petclinic.petClinic.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}")
public class PetController {

    public static final String PETS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, OwnerService ownerService, PetTypeService petTypeService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetType(){
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable String ownerId){

        return ownerService.findById(Long.valueOf(ownerId));
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/pets/new")
    public String getNewPetForm(Owner owner,Model model){
        Pet pet = new Pet();
        owner.getPets().add(pet);
        pet.setOwner(owner);
        model.addAttribute("pet", pet);
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/new")
    public String savePet(Owner owner, @Validated Pet pet, BindingResult result,Model model){
        if(StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name","duplicate", "already exist");
        }
        owner.getPets().add(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            petService.save(pet);

            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/pets/{id}/edit")
    public String getNewPetForm(@PathVariable String id, Model model){
        model.addAttribute("pet", petService.findById(Long.valueOf(id)));
        return PETS_CREATE_OR_UPDATE_PET_FORM;
    }

    @PostMapping("/pets/{id}/edit")
    public String updatePet(@Validated Pet pet, BindingResult result, Owner owner, Model model){
        if(result.hasErrors()){
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        } else {
            owner.getPets().add(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }
}

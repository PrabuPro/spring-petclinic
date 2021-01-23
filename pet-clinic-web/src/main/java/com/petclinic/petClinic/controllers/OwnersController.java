package com.petclinic.petClinic.controllers;

import com.petclinic.petClinic.model.Owner;
import com.petclinic.petClinic.service.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnersController {

    public static final String OWNERS_CREATE_OR_UPDATE_OWNER_FORM = "owners/createOrUpdateOwnerForm";
    private final OwnerService ownerService;

    public OwnersController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }
//
//    @RequestMapping({"", "/", "/index", "/index.html"})
//    public String ownersList(Model model){
//        model.addAttribute("owners", ownerService.findAll());
//
//        return "owners/index";
//    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @RequestMapping("/find")
    public String findOwners(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") Long ownerId){
        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject("owner",ownerService.findById(ownerId));
        return modelAndView;
    }

    @GetMapping()
    public String processFindForm(Owner owner, BindingResult result, Model model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<Owner> results = ownerService.findAllByLastNameContainingIgnoreCase(owner.getLastName() );
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        }
        else if (results.size() == 1) {
            // 1 owner found
            owner = results.get(0);
            return "redirect:/owners/" + owner.getId();
        }
        else {
            // multiple owners found
            model.addAttribute("selections", results);
            return "owners/ownersList";
        }
    }

    @GetMapping("/new")
    public String getOwnerForm(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/new")
    public String saveOwner(@Validated Owner owner, BindingResult result){
        if(result.hasErrors()){
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            Owner owner1 = ownerService.save(owner);
            return "redirect:/owners/" + owner1.getId();
        }
    }

    @GetMapping("/{id}/edit")
    public String getOwnerUpdateForm(@PathVariable String id, Model model){
        model.addAttribute("owner", ownerService.findById(Long.valueOf(id)));
        return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
    }

    @PostMapping("/{id}/edit")
    public String updateOwner(@Validated Owner owner, BindingResult result, @PathVariable String id){
        if(result.hasErrors()){
            return OWNERS_CREATE_OR_UPDATE_OWNER_FORM;
        } else {
            owner.setId(Long.valueOf(id));
            Owner owner1 = ownerService.save(owner);
            return "redirect:/owners/" + owner1.getId();
        }
    }

}

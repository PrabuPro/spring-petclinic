package com.petclinic.petClinic.service.map;

import com.petclinic.petClinic.model.Speciality;
import com.petclinic.petClinic.model.Vet;
import com.petclinic.petClinic.service.SpecialtesService;
import com.petclinic.petClinic.service.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "map"})
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtesService specialtesService;

    public VetServiceMap(SpecialtesService specialtesService) {
        this.specialtesService = specialtesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if(object.getSpecialitySet().size() > 0 ){
            object.getSpecialitySet().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality saveSepciality = specialtesService.save(speciality);
                    speciality.setId(saveSepciality.getId());
                }
            });
        }

        return super.save( object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}

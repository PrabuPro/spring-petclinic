package com.petclinic.petClinic.service.map;

import com.petclinic.petClinic.model.Speciality;
import com.petclinic.petClinic.service.SpecialtesService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SpecialtesMapService extends AbstractMapService<Speciality, Long> implements SpecialtesService {
    @Override
    public Set<Speciality> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Speciality object) {
        super.delete(object);
    }

    @Override
    public Speciality save(Speciality object) {
        return super.save(object);
    }

    @Override
    public Speciality findById(Long id) {
        return super.findById(id);
    }
}

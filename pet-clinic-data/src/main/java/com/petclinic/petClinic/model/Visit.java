package com.petclinic.petClinic.model;

import org.hibernate.annotations.Cache;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name ="visits")
public class Visit extends BaseEntity {

    @Column(name = "date")
    private LocalDate data;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="pat_id")
    private Pet pet;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}

package com.clinic.ClinicModule.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.PhysicianModel;

@Repository
public interface PhysicianRepository extends CrudRepository<PhysicianModel, Integer> {

}

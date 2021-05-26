package com.clinic.ClinicModule.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.PatientModel;

@Repository
public interface PatientRepository extends CrudRepository<PatientModel, Integer> {

}

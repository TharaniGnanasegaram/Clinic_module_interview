package com.clinic.ClinicModule.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.PatientModel;

@Repository
public interface PatientRepository
		extends CrudRepository<PatientModel, Integer>, JpaSpecificationExecutor<PatientModel> {

	List<PatientModel> findByMobile(String mobile);
}

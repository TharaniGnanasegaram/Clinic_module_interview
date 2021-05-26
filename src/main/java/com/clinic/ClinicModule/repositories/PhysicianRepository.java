package com.clinic.ClinicModule.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.PhysicianModel;

@Repository
public interface PhysicianRepository
		extends CrudRepository<PhysicianModel, Integer>, JpaSpecificationExecutor<PhysicianModel> {

	List<PhysicianModel> findByMobile(String mobile);
}

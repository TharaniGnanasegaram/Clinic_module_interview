package com.clinic.ClinicModule.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.BillingModel;

@Repository
public interface BillingRepository extends CrudRepository<BillingModel, Integer> {

}

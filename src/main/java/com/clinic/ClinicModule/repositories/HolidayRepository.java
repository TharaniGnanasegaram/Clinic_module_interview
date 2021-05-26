package com.clinic.ClinicModule.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.HolidayModel;

@Repository
public interface HolidayRepository extends CrudRepository<HolidayModel, Integer> {

}

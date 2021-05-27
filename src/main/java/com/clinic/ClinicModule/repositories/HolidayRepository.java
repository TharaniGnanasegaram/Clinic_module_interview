package com.clinic.ClinicModule.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.HolidayModel;

@Repository
public interface HolidayRepository
		extends CrudRepository<HolidayModel, Integer>, JpaSpecificationExecutor<HolidayModel> {

	List<HolidayModel> findByHolidaydate(Date holidaydate);
}

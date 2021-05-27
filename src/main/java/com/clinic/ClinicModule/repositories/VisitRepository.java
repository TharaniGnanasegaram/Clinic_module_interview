package com.clinic.ClinicModule.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.clinic.ClinicModule.model.VisitModel;

@Repository
public interface VisitRepository extends CrudRepository<VisitModel, Integer>, JpaSpecificationExecutor<VisitModel> {

	List<VisitModel> findByVisitdatetime(Date visitdatetime);
}

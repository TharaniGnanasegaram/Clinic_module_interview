package com.clinic.ClinicModule.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.ClinicModule.common.ClinicDateUtils;
import com.clinic.ClinicModule.common.ClinicUserUtils;
import com.clinic.ClinicModule.model.HolidayModel;
import com.clinic.ClinicModule.model.VisitModel;
import com.clinic.ClinicModule.repositories.VisitRepository;
import com.clinic.ClinicModuleException.ClinicApiBadRequestException;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

	@Autowired
	private VisitRepository visitRepository;

	@Autowired
	private HolidayController holidayController;

	@GetMapping
	public List<VisitModel> search(@RequestParam(value = "visitdatetime", required = false) Date visitdatetime) {
		if (visitdatetime == null) {
			return (List<VisitModel>) visitRepository.findAll();
		} else {
			return visitRepository.findByVisitdatetime(visitdatetime);
		}

	}

	@GetMapping("/{id}")
	public VisitModel findVisitById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<VisitModel> visit = visitRepository.findById(id);

		if (visit.isPresent()) {
			return visit.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@PostMapping
	public VisitModel saveVisit(@Validated @RequestBody VisitModel visit) {

		Date visitDay = new Date(visit.getVisitdatetime().getTime());
		List<HolidayModel> holiday = holidayController.search(visitDay);
		if (!holiday.isEmpty()) {
			throw new ClinicApiBadRequestException("The provided visit date is a holiday. Please select another date.");
		}

		visit.setVisitdatetime(new java.sql.Timestamp(visit.getVisitdatetime().getTime()));
		visit.setCreatedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		visit.setModifiedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		visit.setCreatedBy(ClinicUserUtils.getCurrentUser());
		visit.setModifiedBy(ClinicUserUtils.getCurrentUser());

		try {
			return visitRepository.save(visit);
		} catch (DataIntegrityViolationException e) {
			throw new ClinicApiBadRequestException("Error!. Please check whether the patient or the physician exists.");
		}

	}

	@PutMapping("/{id}")
	public VisitModel updateVisit(@Validated @RequestBody VisitModel updateVisit,
			@PathVariable(value = "id") Integer id) {
		Optional<VisitModel> existingVisit = visitRepository.findById(id);

		if (!existingVisit.isPresent() && (updateVisit.getId() != null && updateVisit.getId() != id)) {
			throw new ClinicApiBadRequestException("Error!. Provided Id not exists.");
		}

		updateVisit.setId(existingVisit.get().getId());
		if (updateVisit.getVisitdatetime() != null) {
			Date visitDay = new Date(updateVisit.getVisitdatetime().getTime());
			List<HolidayModel> holiday = holidayController.search(visitDay);
			if (!holiday.isEmpty()) {
				throw new ClinicApiBadRequestException(
						"The provided visit date is a holiday. Please select another date.");
			}
			existingVisit.get().setVisitdatetime(updateVisit.getVisitdatetime());
		}
		if (updateVisit.getPatientId() != null) {
			existingVisit.get().setPatientId(updateVisit.getPatientId());
		}
		if (updateVisit.getPhysicianId() != null) {
			existingVisit.get().setPhysicianId(updateVisit.getPhysicianId());
		}
		if (updateVisit.getReason() != null) {
			existingVisit.get().setReason(updateVisit.getReason());
		}

		existingVisit.get().setModifiedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		existingVisit.get().setModifiedBy(ClinicUserUtils.getCurrentUser());

		try {
			return visitRepository.save(existingVisit.get());
		} catch (DataIntegrityViolationException e) {
			throw new ClinicApiBadRequestException("Error!. Please check whether the patient or the physician exists.");
		}
	}

}

package com.clinic.ClinicModule.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.ClinicModule.model.VisitModel;
import com.clinic.ClinicModule.repositories.VisitRepository;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/visit")
public class VisitController {

	@Autowired
	private VisitRepository visitRepository;

	@GetMapping
	public List<VisitModel> findAllVisits() {
		return (List<VisitModel>) visitRepository.findAll();
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
		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		visit.setCreatedDatetime(currentDateTime);
		visit.setModifiedDatetime(currentDateTime);
		visit.setCreatedBy("");
		visit.setModifiedBy("");
		return visitRepository.save(visit);
	}

	@PutMapping("/{id}")
	public VisitModel updateVisit(@Validated @RequestBody VisitModel updateVisit,
			@PathVariable(value = "id") Integer id) {
		Optional<VisitModel> existingVisit = visitRepository.findById(id);

		if (!existingVisit.isPresent() && (updateVisit.getId() != null && updateVisit.getId() != id)) {
			return null;
		}

		updateVisit.setId(existingVisit.get().getId());
		if (updateVisit.getVisitDatetime() != null) {
			existingVisit.get().setVisitDatetime(updateVisit.getVisitDatetime());
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

		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		existingVisit.get().setModifiedDatetime(currentDateTime);
		existingVisit.get().setModifiedBy("");

		return visitRepository.save(existingVisit.get());
	}
}

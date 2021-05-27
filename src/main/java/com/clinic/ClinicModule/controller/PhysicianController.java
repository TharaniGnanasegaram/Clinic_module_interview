package com.clinic.ClinicModule.controller;

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
import com.clinic.ClinicModule.model.PhysicianModel;
import com.clinic.ClinicModule.repositories.PhysicianRepository;
import com.clinic.ClinicModuleException.ClinicApiBadRequestException;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/physician")
public class PhysicianController {

	@Autowired
	private PhysicianRepository physicianRepository;

	@GetMapping("/{id}")
	public PhysicianModel findPhysicianById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<PhysicianModel> physician = physicianRepository.findById(id);

		if (physician.isPresent()) {
			return physician.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@GetMapping
	public List<PhysicianModel> search(@RequestParam(value = "mobile", required = false) String mobile) {
		if (mobile == null) {
			return (List<PhysicianModel>) physicianRepository.findAll();
		} else {
			return physicianRepository.findByMobile(mobile);
		}

	}

	@PostMapping
	public PhysicianModel savePhysician(@Validated @RequestBody PhysicianModel physician) {
		physician.setCreatedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		physician.setModifiedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		physician.setCreatedBy(ClinicUserUtils.getCurrentUser());
		physician.setModifiedBy(ClinicUserUtils.getCurrentUser());
		try {
			return physicianRepository.save(physician);
		} catch (DataIntegrityViolationException e) {
			throw new ClinicApiBadRequestException("Physician already exists.");
		}

	}

	@PutMapping("/{id}")
	public PhysicianModel updatePhysician(@Validated @RequestBody PhysicianModel updatePhysician,
			@PathVariable(value = "id") Integer id) {
		Optional<PhysicianModel> existingPhysician = physicianRepository.findById(id);

		if (!existingPhysician.isPresent() && (updatePhysician.getId() != null && updatePhysician.getId() != id)) {
			return null;
		}

		updatePhysician.setId(existingPhysician.get().getId());
		if (updatePhysician.getName() != null) {
			existingPhysician.get().setName(updatePhysician.getName());
		}
		if (updatePhysician.getMobile() != null) {
			existingPhysician.get().setMobile(updatePhysician.getMobile());
		}

		existingPhysician.get().setModifiedDatetime(ClinicDateUtils.getCurrentTimeStamp());
		existingPhysician.get().setModifiedBy(ClinicUserUtils.getCurrentUser());

		try {
			return physicianRepository.save(existingPhysician.get());
		} catch (DataIntegrityViolationException e) {
			throw new ClinicApiBadRequestException(
					"Provided mobile number already exists for a Physician. Please check whether the physician already exists.");
		}
	}

}

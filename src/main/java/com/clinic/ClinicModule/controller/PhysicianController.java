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

import com.clinic.ClinicModule.model.PhysicianModel;
import com.clinic.ClinicModule.repositories.PhysicianRepository;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/physician")
public class PhysicianController {

	@Autowired
	private PhysicianRepository userRepository;

	@GetMapping
	public List<PhysicianModel> findAllPhysicians() {
		return (List<PhysicianModel>) userRepository.findAll();
	}

	@GetMapping("/{id}")
	public PhysicianModel findPhysicianById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<PhysicianModel> physician = userRepository.findById(id);

		if (physician.isPresent()) {
			return physician.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@PostMapping
	public PhysicianModel savePhysician(@Validated @RequestBody PhysicianModel physician) {
		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		physician.setCreatedDatetime(currentDateTime);
		physician.setModifiedDatetime(currentDateTime);
		physician.setCreatedBy("");
		physician.setModifiedBy("");
		return userRepository.save(physician);
	}

	@PutMapping("/{id}")
	public PhysicianModel updatePhysician(@Validated @RequestBody PhysicianModel updatePhysician,
			@PathVariable(value = "id") Integer id) {
		Optional<PhysicianModel> existingPhysician = userRepository.findById(id);

		if (!existingPhysician.isPresent() && (updatePhysician.getId() != null && updatePhysician.getId() != id)) {
			return null;
		}

		updatePhysician.setId(existingPhysician.get().getId());
		if (updatePhysician.getName() != null) {
			existingPhysician.get().setName(updatePhysician.getName());
		}
		if (updatePhysician.getMobileNumber() != null) {
			existingPhysician.get().setMobileNumber(updatePhysician.getMobileNumber());
		}

		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		existingPhysician.get().setModifiedDatetime(currentDateTime);
		existingPhysician.get().setModifiedBy("");

		return userRepository.save(existingPhysician.get());
	}

}

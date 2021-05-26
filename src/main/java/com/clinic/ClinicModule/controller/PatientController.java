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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.ClinicModule.model.PatientModel;
import com.clinic.ClinicModule.repositories.PatientRepository;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	@GetMapping
	public List<PatientModel> search(@RequestParam(value = "mobile", required = false) String mobile) {
		if (mobile == null) {
			return (List<PatientModel>) patientRepository.findAll();
		} else {
			return patientRepository.findByMobile(mobile);
		}

	}

	@GetMapping("/{id}")
	public PatientModel findPatientById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<PatientModel> patient = patientRepository.findById(id);

		if (patient.isPresent()) {
			return patient.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@PostMapping
	public PatientModel savePatient(@Validated @RequestBody PatientModel patient) {
		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		patient.setCreatedDatetime(currentDateTime);
		patient.setModifiedDatetime(currentDateTime);
		patient.setCreatedBy("");
		return patientRepository.save(patient);
	}

	@PutMapping("/{id}")
	public PatientModel updatePatient(@Validated @RequestBody PatientModel updatePatient,
			@PathVariable(value = "id") Integer id) {
		Optional<PatientModel> existingPatient = patientRepository.findById(id);

		if (!existingPatient.isPresent() && (updatePatient.getId() != null && updatePatient.getId() != id)) {
			return null;
		}

		updatePatient.setId(existingPatient.get().getId());
		if (updatePatient.getName() != null) {
			existingPatient.get().setName(updatePatient.getName());
		}
		if (updatePatient.getMobile() != null) {
			existingPatient.get().setMobile(updatePatient.getMobile());
		}
		if (updatePatient.getAge() != null) {
			existingPatient.get().setAge(updatePatient.getAge());
		}
		if (updatePatient.getGender() != null) {
			existingPatient.get().setGender(updatePatient.getGender());
		}

		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		existingPatient.get().setModifiedDatetime(currentDateTime);

		return patientRepository.save(existingPatient.get());
	}
}

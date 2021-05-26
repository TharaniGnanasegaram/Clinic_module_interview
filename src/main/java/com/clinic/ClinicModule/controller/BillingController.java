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

import com.clinic.ClinicModule.model.BillingModel;
import com.clinic.ClinicModule.repositories.BillingRepository;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

	@Autowired
	private BillingRepository billingRepository;

	@GetMapping
	public List<BillingModel> findAllBillings() {
		return (List<BillingModel>) billingRepository.findAll();
	}

	@GetMapping("/{id}")
	public BillingModel findBillingById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<BillingModel> billing = billingRepository.findById(id);

		if (billing.isPresent()) {
			return billing.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

	@PostMapping
	public BillingModel saveBill(@Validated @RequestBody BillingModel billing) {
		Date currentDateTime = new java.sql.Timestamp(new Date().getTime());
		billing.setBilledDatetime(currentDateTime);
		return billingRepository.save(billing);
	}

	@PutMapping("/{id}")
	public BillingModel updateBill(@Validated @RequestBody BillingModel updateBilling,
			@PathVariable(value = "id") Integer id) {
		Optional<BillingModel> existingBilling = billingRepository.findById(id);

		if (!existingBilling.isPresent() && (updateBilling.getId() != null && updateBilling.getId() != id)) {
			return null;
		}

		updateBilling.setId(existingBilling.get().getId());
		if (updateBilling.getPatientId() != null) {
			existingBilling.get().setPatientId(updateBilling.getPatientId());
		}
		if (updateBilling.getPhysicianId() != null) {
			existingBilling.get().setPhysicianId(updateBilling.getPhysicianId());
		}

		return billingRepository.save(existingBilling.get());
	}
}

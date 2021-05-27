package com.clinic.ClinicModule.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.ClinicModule.model.HolidayModel;
import com.clinic.ClinicModule.repositories.HolidayRepository;
import com.clinic.ClinicModuleException.ResourceNotFoundException;

@RestController
@RequestMapping("/api/holiday")
public class HolidayController {

	@Autowired
	private HolidayRepository holidayRepository;

	@GetMapping
	public List<HolidayModel> search(@RequestParam(value = "holidaydate", required = false) Date holidaydate) {
		if (holidaydate == null) {
			return (List<HolidayModel>) holidayRepository.findAll();
		} else {
			return holidayRepository.findByHolidaydate(holidaydate);
		}

	}

	@GetMapping("/{id}")
	public HolidayModel findHolidayById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
		Optional<HolidayModel> holiday = holidayRepository.findById(id);

		if (holiday.isPresent()) {
			return holiday.get();
		} else {
			throw new ResourceNotFoundException();
		}
	}

}

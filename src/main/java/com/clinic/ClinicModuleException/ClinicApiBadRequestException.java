package com.clinic.ClinicModuleException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public final class ClinicApiBadRequestException extends RuntimeException {

	private static final long serialVersionUID = -5605216926783790826L;
	private final String developerMessage;

	public ClinicApiBadRequestException(String aMessage) {
		super(aMessage);
		this.developerMessage = aMessage;
	}

	@Override
	public String getMessage() {
		if (this.developerMessage == null && this.getCause() != null) {
			return this.getCause().getMessage();
		} else {
			return this.developerMessage;
		}
	}

}
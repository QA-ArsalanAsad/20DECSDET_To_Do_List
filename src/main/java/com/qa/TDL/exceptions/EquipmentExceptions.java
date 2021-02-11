package com.qa.TDL.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "The id you have provided does not exist, please try again.")

public class EquipmentExceptions extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4900885947211606115L;

}

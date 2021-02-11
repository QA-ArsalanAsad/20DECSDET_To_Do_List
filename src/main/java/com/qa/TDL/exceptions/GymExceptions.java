package com.qa.TDL.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.METHOD_NOT_ALLOWED, reason = "The id you have provided does not exist, please try again.")

public class GymExceptions extends EntityNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5357695334597375676L;

}

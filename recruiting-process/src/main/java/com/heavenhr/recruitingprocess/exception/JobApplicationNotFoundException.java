package com.heavenhr.recruitingprocess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class JobApplicationNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8815012686700950756L;

	public JobApplicationNotFoundException() {
		super();
	}

	public JobApplicationNotFoundException(String s) {
		super(s);
	}

	public JobApplicationNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public JobApplicationNotFoundException(Throwable throwable) {
		super(throwable);
	}

}

package com.heavenhr.recruitingprocess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class DuplicateJobApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2799014470833325709L;

	public DuplicateJobApplicationException() {
		super();
	}

	public DuplicateJobApplicationException(String s) {
		super(s);
	}

	public DuplicateJobApplicationException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public DuplicateJobApplicationException(Throwable throwable) {
		super(throwable);
	}

}


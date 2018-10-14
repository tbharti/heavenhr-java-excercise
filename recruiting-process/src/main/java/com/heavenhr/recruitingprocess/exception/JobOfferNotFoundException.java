package com.heavenhr.recruitingprocess.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class JobOfferNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 196173937043743349L;

	public JobOfferNotFoundException() {
		super();
	}

	public JobOfferNotFoundException(String s) {
		super(s);
	}

	public JobOfferNotFoundException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public JobOfferNotFoundException(Throwable throwable) {
		super(throwable);
	}

}

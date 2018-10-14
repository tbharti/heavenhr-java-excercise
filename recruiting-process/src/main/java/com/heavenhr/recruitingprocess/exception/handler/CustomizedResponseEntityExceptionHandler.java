package com.heavenhr.recruitingprocess.exception.handler;

import java.util.Date;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.heavenhr.recruitingprocess.exception.DuplicateJobApplicationException;
import com.heavenhr.recruitingprocess.exception.DuplicateJobOfferException;
import com.heavenhr.recruitingprocess.exception.JobApplicationNotFoundException;
import com.heavenhr.recruitingprocess.exception.JobOfferNotFoundException;
import com.heavenhr.recruitingprocess.model.ErrorDetails;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(JobOfferNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleJobOfferNotFoundException(JobOfferNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateJobOfferException.class)
	public final ResponseEntity<ErrorDetails> handleDuplicateOfferFoundException(DuplicateJobOfferException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String errorMsg = ex.getBindingResult().getFieldErrors().stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage).findFirst().orElse(ex.getMessage());
		ErrorDetails errorDetails = new ErrorDetails(new Date(), errorMsg, request.getDescription(false));

		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(JobApplicationNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleJobApplicationNotFoundException(JobApplicationNotFoundException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(DuplicateJobApplicationException.class)
	public final ResponseEntity<ErrorDetails> handleDuplicateJobApplicationException(
			DuplicateJobApplicationException ex, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
	}
	
}

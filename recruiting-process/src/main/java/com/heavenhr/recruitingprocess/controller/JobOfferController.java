package com.heavenhr.recruitingprocess.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.model.request.JobApplicationRequest;
import com.heavenhr.recruitingprocess.model.request.JobOfferRequest;
import com.heavenhr.recruitingprocess.model.response.JobOfferResponse;
import com.heavenhr.recruitingprocess.service.JobOfferService;

/**
 * @author tbharti
 *
 */

@RestController
@RequestMapping(value = "/api/job-offers")
public class JobOfferController {

	@Autowired
	private JobOfferService jobOfferService;

	@PostMapping
	public ResponseEntity<?> createJobOffer(@Valid @RequestBody JobOfferRequest offerRequest) {

		JobOffer jobOffer = jobOfferService.createJobOffer(offerRequest);

		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(UriComponentsBuilder.fromUriString("/api/job-offers/{offerId}").buildAndExpand(jobOffer.getId()).toUri());
	    return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}
	
	@PostMapping(value = "/{jobOfferId}/job-applications")
	public ResponseEntity<?> applyToJobOffer(@PathVariable Long jobOfferId,
			@Valid @RequestBody JobApplicationRequest jobApplicationRequest) {

		JobApplication jobApplication = jobOfferService.applyToJobOffer(jobOfferId, jobApplicationRequest);
		
		HttpHeaders headers = new HttpHeaders();
	    headers.setLocation(UriComponentsBuilder.fromUriString("/api/job-offers/{offerId}/job-applications/{applicationId}").buildAndExpand(jobOfferId, jobApplication.getId()).toUri());
	    return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{jobOfferId}")
	public ResponseEntity<Object> getJobOfferById(@PathVariable long jobOfferId) {

		JobOffer jobOffer = jobOfferService.getJobOfferById(jobOfferId);

		return ResponseEntity.status(HttpStatus.OK).body(new JobOfferResponse(jobOffer.getId(), jobOffer.getJobTitle(),
				jobOffer.getStartDate(), jobOffer.getNumberOfApplications()));
	}
	
	@GetMapping
	public ResponseEntity<List<Object>> getJobOffers() {

		return ResponseEntity.status(HttpStatus.OK)
				.body(jobOfferService
						.getAllJobOffers().stream().map(offer -> new JobOfferResponse(offer.getId(),
								offer.getJobTitle(), offer.getStartDate(), offer.getNumberOfApplications()))
						.collect(Collectors.toList()));
	}
	
	

}

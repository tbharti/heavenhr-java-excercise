package com.heavenhr.recruitingprocess.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.request.JobStatusUpdateRequest;
import com.heavenhr.recruitingprocess.model.response.JobApplicationResponse;
import com.heavenhr.recruitingprocess.service.JobApplicationService;

/**
 * @author tbharti
 *
 */

@RestController
@RequestMapping(value = "/api/")
public class JobApplicationController {

	@Autowired
	private JobApplicationService jobApplicationService;

	@GetMapping(value = "/job-offers/{jobOfferId}/job-applications/{jobApplicationId}")
	public ResponseEntity<JobApplicationResponse> getJobApplicationByJobOfferId(@PathVariable long jobOfferId,
			@PathVariable long jobApplicationId) {

		JobApplication jobApplication = jobApplicationService.getJobApplicationByJobOfferId(jobOfferId,
				jobApplicationId);

		return ResponseEntity.status(HttpStatus.OK)
				.body(new JobApplicationResponse(jobApplication.getId(), jobApplication.getJobOffer().getId(),
						jobApplication.getEmail(), jobApplication.getResumeText(), jobApplication.getStatus()));
	}

	@GetMapping(value = "/job-offers/{jobOfferId}/job-applications")
	public ResponseEntity<List<JobApplicationResponse>> getAllJobApplicationsByJobOfferId(@PathVariable long jobOfferId) {

		return ResponseEntity.status(HttpStatus.OK)
				.body(jobApplicationService.getAllJobApplicationsByJobOfferId(jobOfferId).stream()
						.map(jobApplication -> new JobApplicationResponse(jobApplication.getId(),
								jobApplication.getJobOffer().getId(), jobApplication.getEmail(),
								jobApplication.getResumeText(), jobApplication.getStatus()))
						.collect(Collectors.toList()));
	}

	@GetMapping(value = "/job-applications")
	public ResponseEntity<List<JobApplicationResponse>> getAllJobApplications() {

		return ResponseEntity.status(HttpStatus.OK).body(jobApplicationService.getAllApplications().stream()
				.map(jobApplication -> new JobApplicationResponse(jobApplication.getId(),
						jobApplication.getJobOffer().getId(), jobApplication.getEmail(), jobApplication.getResumeText(),
						jobApplication.getStatus()))
				.collect(Collectors.toList()));
	}

	@PutMapping(value = "/job-offers/{jobOfferId}/job-applications/{jobApplicationId}/status")
	public ResponseEntity<JobApplicationResponse> updateJobApplicationStatus(@PathVariable long jobOfferId,
			@PathVariable long jobApplicationId, @Valid @RequestBody JobStatusUpdateRequest jobStatusUpdateRequest) {

		JobApplication jobApplication = jobApplicationService.updateJobApplicationStatus(jobApplicationId,
				jobStatusUpdateRequest.getStatus());

		return ResponseEntity.status(HttpStatus.OK)
				.body(new JobApplicationResponse(jobApplication.getId(), jobApplication.getJobOffer().getId(),
						jobApplication.getEmail(), jobApplication.getResumeText(), jobApplication.getStatus()));
	}

}

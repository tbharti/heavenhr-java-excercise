package com.heavenhr.recruitingprocess.service;

import java.util.List;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.model.request.JobApplicationRequest;
import com.heavenhr.recruitingprocess.model.request.JobOfferRequest;

public interface JobOfferService {
	
	JobOffer createJobOffer(JobOfferRequest offer);

	JobOffer getJobOfferById(long id);

    List<JobOffer> getAllJobOffers();

	JobApplication applyToJobOffer(Long id, JobApplicationRequest request);

}

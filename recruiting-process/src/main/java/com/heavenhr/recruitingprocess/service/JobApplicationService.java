package com.heavenhr.recruitingprocess.service;

import java.util.List;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;

public interface JobApplicationService {

	JobApplication getJobOfferApplicationByID(long id);

	List<JobApplication> getAllApplications();

	List<JobApplication> getAllJobApplicationsByJobOfferId(long jobOfferId);

	JobApplication getJobApplicationByJobOfferId(long jobOfferId, long jobApplicationId);

	JobApplication updateJobApplicationStatus(long applicationId, JobApplicationStatus toStatus);

}

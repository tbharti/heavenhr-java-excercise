package com.heavenhr.recruitingprocess.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitingprocess.exception.JobApplicationNotFoundException;
import com.heavenhr.recruitingprocess.exception.JobOfferNotFoundException;
import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;
import com.heavenhr.recruitingprocess.notification.listener.NotificationEventListerner;
import com.heavenhr.recruitingprocess.repository.JobApplicationRepository;
import com.heavenhr.recruitingprocess.service.JobApplicationService;

/**
 * @author tbharti
 *
 */
@Service
public class JobApplicationServiceImpl implements JobApplicationService {

	@Autowired
	private JobApplicationRepository jobApplicationRepository;
	
	@Autowired
	private NotificationEventListerner notificationEventListerner;
	
	@Override
	public JobApplication getJobOfferApplicationByID(long id) {
		return jobApplicationRepository.findById(id)
				.orElseThrow(() -> new JobApplicationNotFoundException("Job Application Id : " + id));
	}

	@Override
	public List<JobApplication> getAllApplications() {
		return jobApplicationRepository.findAll();
	}

	@Override
	public JobApplication updateJobApplicationStatus(long applicationId, JobApplicationStatus newStatus) {
		JobApplication jobApplication = jobApplicationRepository.findById(applicationId)
				.orElseThrow(() -> new JobApplicationNotFoundException("Job Application Id : " + applicationId));

		JobApplicationStatus oldStatus = jobApplication.getStatus();
		jobApplication.setStatus(newStatus);
		jobApplicationRepository.save(jobApplication);
		if(oldStatus != newStatus) {
			notificationEventListerner.publishNotification(jobApplication, oldStatus);
		}
		return jobApplication;
	}

	@Override
	public JobApplication getJobApplicationByJobOfferId(long jobOfferId, long jobApplicationId) {
		return jobApplicationRepository.findJobApplicationByOfferId(jobOfferId, jobApplicationId)
				.orElseThrow(() -> new JobOfferNotFoundException(
						"Job Offer Id : " + jobOfferId + ", Job Application Id : " + jobApplicationId));
	}

	@Override
	public List<JobApplication> getAllJobApplicationsByJobOfferId(long jobOfferId) {
		return jobApplicationRepository.findAllJobApplicationsByOfferId(jobOfferId)
				.orElseThrow(() -> new JobApplicationNotFoundException("Job Offer Id : " + jobOfferId));
	}

}

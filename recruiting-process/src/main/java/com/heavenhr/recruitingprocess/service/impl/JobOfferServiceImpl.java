package com.heavenhr.recruitingprocess.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitingprocess.exception.DuplicateJobOfferException;
import com.heavenhr.recruitingprocess.exception.JobOfferNotFoundException;
import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.model.request.JobApplicationRequest;
import com.heavenhr.recruitingprocess.model.request.JobOfferRequest;
import com.heavenhr.recruitingprocess.repository.JobApplicationRepository;
import com.heavenhr.recruitingprocess.repository.JobOfferRepository;
import com.heavenhr.recruitingprocess.service.JobOfferService;

/**
 * @author tbharti
 *
 */
@Service
public class JobOfferServiceImpl implements JobOfferService {

	@Autowired
	private JobOfferRepository jobOfferRepository;

	@Autowired
	private JobApplicationRepository jobApplicationRepository;

	@Override
	public JobOffer createJobOffer(JobOfferRequest jobOfferRequest) {

		JobOffer jobOffer = new JobOffer();

		BeanUtils.copyProperties(jobOfferRequest, jobOffer);

		try {
			jobOffer = jobOfferRepository.save(jobOffer);
		} catch (DataIntegrityViolationException uniqueViolation) {
			throw new DuplicateJobOfferException("Duplicate Job offer, Offer already exist with title = "
					+ jobOffer.getJobTitle() + " and startdate = " + jobOffer.getStartDate());
		}

		return jobOffer;
	}

	@Override
	public JobApplication applyToJobOffer(Long id, JobApplicationRequest jobApplicationRequest) {

		JobOffer jobOffer = jobOfferRepository.findById(id)
				.orElseThrow(() -> new JobOfferNotFoundException("jobOfferId : " + id));

		JobApplication jobApplication = new JobApplication();

		BeanUtils.copyProperties(jobApplicationRequest, jobApplication);

		jobApplication.setStatus(JobApplicationStatus.APPLIED);
		jobOffer.getApplications().add(jobApplication);

		jobApplication.setJobOffer(jobOffer);

		try {
			jobApplication = jobApplicationRepository.save(jobApplication);
		} catch (DataIntegrityViolationException uniqueViolation) {
			throw new DuplicateJobOfferException(
					"Duplicate Job Application, Job Application already exist with email = " + jobApplication.getEmail()
							+ " and resume text = " + jobApplication.getResumeText());
		}

		return jobApplication;
	}

	@Override
	public JobOffer getJobOfferById(long id) {
		return jobOfferRepository.findById(id).orElseThrow(() -> new JobOfferNotFoundException("jobOfferId : " + id));
	}

	@Override
	public List<JobOffer> getAllJobOffers() {
		return jobOfferRepository.findAll();
	}

}

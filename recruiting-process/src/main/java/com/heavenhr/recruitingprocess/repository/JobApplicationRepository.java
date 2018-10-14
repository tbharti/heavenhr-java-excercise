package com.heavenhr.recruitingprocess.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.heavenhr.recruitingprocess.model.JobApplication;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
	
	@Query("SELECT a FROM JOB_APPLICATION a WHERE JOB_OFFER_ID = ?1 AND JOB_APP_ID = ?2")
	Optional<JobApplication> findJobApplicationByOfferId(Long offerId, Long applicationId);
	
	@Query("SELECT a FROM JOB_APPLICATION a WHERE JOB_OFFER_ID = ?1")
	Optional<List<JobApplication>> findAllJobApplicationsByOfferId(Long id);

}

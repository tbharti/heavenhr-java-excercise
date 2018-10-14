package com.heavenhr.recruitingprocess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heavenhr.recruitingprocess.model.JobOffer;

public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

}

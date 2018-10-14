package com.heavenhr.recruitingprocess.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.model.request.JobOfferRequest;
import com.heavenhr.recruitingprocess.repository.JobOfferRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobOfferServiceImplTest {

	@InjectMocks
	private JobOfferServiceImpl offerService;

	@Mock
	private JobOfferRepository jobOfferRepository;

	@Test
	public void testCreateJobOffer() {

		JobOffer stub = mockJobOffer("Java Dev", "2018-10-15");
		Mockito.when(jobOfferRepository.save(Mockito.any(JobOffer.class))).thenReturn(stub);

		JobOffer result = offerService.createJobOffer(new JobOfferRequest());
		Assert.assertEquals("Java Dev", result.getJobTitle());
	}

	@Test
	public void testGetJobOfferById() {

		JobOffer stub = mockJobOffer("Java Dev", "2018-10-15");
		Mockito.when(jobOfferRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stub));

		JobOffer result = offerService.getJobOfferById(1L);
		Assert.assertEquals("Java Dev", result.getJobTitle());
	}

	@Test
	public void testGetAllJobOffers() {
		JobOffer stub = mockJobOffer("Java Dev 1", "2018-10-15");
		JobOffer stub1 = mockJobOffer("Java Dev 2", "2018-10-16");
		List<JobOffer> jobOffers = new ArrayList<JobOffer>();
		jobOffers.add(stub1);
		jobOffers.add(stub);

		Mockito.when(jobOfferRepository.findAll()).thenReturn(jobOffers);

		List<JobOffer> result = offerService.getAllJobOffers();
		Assert.assertEquals(jobOffers.size(), result.size());
		for (int i = 0; i < result.size(); i++) {
			Assert.assertEquals(jobOffers.get(i), result.get(i));
		}

	}

	private JobOffer mockJobOffer(String title, String date) {

		JobOffer jobOffer = new JobOffer();
		jobOffer.setId(1L);
		jobOffer.setJobTitle(title);
		jobOffer.setStartDate(LocalDate.now());
		jobOffer.setNumberOfApplications(0);

		return jobOffer;
	}
}

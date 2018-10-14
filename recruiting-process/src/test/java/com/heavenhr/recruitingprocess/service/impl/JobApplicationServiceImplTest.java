package com.heavenhr.recruitingprocess.service.impl;

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
import org.springframework.test.util.ReflectionTestUtils;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.repository.JobApplicationRepository;

@RunWith(MockitoJUnitRunner.class)
public class JobApplicationServiceImplTest {

	@InjectMocks
	private JobApplicationServiceImpl applicationService;

	@Mock
	private JobApplicationRepository applicationRepository;

	@Test
	public void testGetJobOfferApplicationByID() {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		Mockito.when(applicationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(application1));

		JobApplication result = applicationService.getJobOfferApplicationByID(1L);
		Assert.assertEquals(application1.getEmail(), result.getEmail());
	}

	@Test
	public void testGetJobApplicationByJobOfferId() {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		Mockito.when(applicationRepository.findJobApplicationByOfferId(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(Optional.of(application1));

		JobApplication result = applicationService.getJobApplicationByJobOfferId(1L, 1L);
		Assert.assertEquals(application1.getEmail(), result.getEmail());
	}

	@Test
	public void testUpdateJobApplicationStatus() {
		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		Mockito.when(applicationRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(application1));
		application1.setStatus(JobApplicationStatus.HIRED);
		Mockito.when(applicationRepository.save(Mockito.any(JobApplication.class))).thenReturn(application1);

		JobApplication result = applicationService.updateJobApplicationStatus(1L, JobApplicationStatus.HIRED);
		Assert.assertEquals(JobApplicationStatus.HIRED, result.getStatus());

	}

	@Test
	public void testGetAllApplications() {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		JobApplication application2 = mockJobApplication("tarunbharti1@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 2L);
		List<JobApplication> applicationList = new ArrayList<>();
		applicationList.add(application1);
		applicationList.add(application2);
		Mockito.when(applicationRepository.findAll()).thenReturn(applicationList);
		List<JobApplication> readApplications = applicationService.getAllApplications();

		List<JobApplication> readApplicationsList = new ArrayList<>(readApplications);
		Assert.assertEquals(applicationList.size(), readApplicationsList.size());
		for (int i = 0; i < applicationList.size(); i++) {
			Assert.assertEquals(applicationList.get(i), readApplicationsList.get(i));
		}

	}

	@Test
	public void testGetAllJobApplicationsByJobOfferId() {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		JobApplication application2 = mockJobApplication("tarunbharti1@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 2L);
		List<JobApplication> applicationList = new ArrayList<>();
		applicationList.add(application1);
		applicationList.add(application2);
		Mockito.when(applicationRepository.findAllJobApplicationsByOfferId(Mockito.anyLong()))
				.thenReturn(Optional.ofNullable(applicationList));
		List<JobApplication> readApplications = applicationService.getAllJobApplicationsByJobOfferId(1L);

		List<JobApplication> readApplicationsList = new ArrayList<>(readApplications);
		Assert.assertEquals(applicationList.size(), readApplicationsList.size());
		for (int i = 0; i < applicationList.size(); i++) {
			Assert.assertEquals(applicationList.get(i), readApplicationsList.get(i));
		}

	}

	private JobApplication mockJobApplication(String email) {

		JobOffer jobOffer = new JobOffer();
		jobOffer.setId(1L);

		JobApplication jobApplication = new JobApplication();
		jobApplication.setEmail(email);
		jobApplication.setId(1L);
		jobApplication.setResumeText("Java developer");
		jobApplication.setStatus(JobApplicationStatus.APPLIED);
		jobApplication.setJobOffer(jobOffer);

		return jobApplication;

	}

}

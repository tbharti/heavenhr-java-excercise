package com.heavenhr.recruitingprocess.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.service.JobApplicationService;
import com.heavenhr.recruitingprocess.service.JobOfferService;

@RunWith(SpringRunner.class)
@WebMvcTest(JobApplicationController.class)
public class JobApplicationControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private JobApplicationService applicationService;

	@MockBean
	private JobOfferService offerService;

	@Captor
	private ArgumentCaptor<JobApplication> applicationCaptor;

	@Captor
	private ArgumentCaptor<JobApplicationStatus> statusCaptor;

	@Test
	public void testUpdateStatus() throws Exception {
		String contentAsJson = "{\"status\":\"HIRED\"}";

		JobApplication jobApplication = mockJobApplication("tarun@hotmail.com");
		Mockito.when(applicationService.updateJobApplicationStatus(Mockito.anyLong(),
				Mockito.any(JobApplicationStatus.class))).thenReturn(jobApplication);

		this.mvc.perform(put("/api/job-offers/1/job-applications/1/status").contentType(MediaType.APPLICATION_JSON)
				.content(contentAsJson)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	public void testGetJobOfferApplicationByID() throws Exception {

		JobApplication jobApplication = mockJobApplication("tarunbharti@hotmail.com");
		ReflectionTestUtils.setField(jobApplication, "id", 1L);
		ReflectionTestUtils.setField(jobApplication, "status", JobApplicationStatus.APPLIED);
		Mockito.when(applicationService.getJobApplicationByJobOfferId(Mockito.anyLong(), Mockito.anyLong()))
				.thenReturn(jobApplication);

		this.mvc.perform(get("/api/job-offers/1/job-applications/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$.email").value("tarunbharti@hotmail.com"))
				.andExpect(jsonPath("$.resumeText").value("Java developer"))
				.andExpect(jsonPath("$.status").value(JobApplicationStatus.APPLIED.name()));

	}

	@Test
	public void testGetAllJobApplicationsByJobOfferId() throws Exception {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		JobApplication application2 = mockJobApplication("tarunbharti1@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		ReflectionTestUtils.setField(application2, "id", 2L);
		List<JobApplication> applications = new ArrayList<>();
		applications.add(application1);
		applications.add(application2);
		Mockito.when(applicationService.getAllJobApplicationsByJobOfferId(Mockito.anyLong())).thenReturn(applications);

		this.mvc.perform(get("/api/job-offers/1/job-applications/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].offerId").value(1))
				.andExpect(jsonPath("$[0].email").value("tarunbharti@hotmail.com"))
				.andExpect(jsonPath("$[0].resumeText").value("Java developer"))
				.andExpect(jsonPath("$[0].status").value(JobApplicationStatus.APPLIED.name()))
				.andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[1].offerId").value(1))
				.andExpect(jsonPath("$[1].email").value("tarunbharti1@hotmail.com"))
				.andExpect(jsonPath("$[1].resumeText").value("Java developer"))
				.andExpect(jsonPath("$[1].status").value(JobApplicationStatus.APPLIED.name()));

	}
	
	@Test
	public void testGetAllJobApplications() throws Exception {

		JobApplication application1 = mockJobApplication("tarunbharti@hotmail.com");
		JobApplication application2 = mockJobApplication("tarunbharti1@hotmail.com");
		ReflectionTestUtils.setField(application1, "id", 1L);
		ReflectionTestUtils.setField(application2, "id", 2L);
		List<JobApplication> applications = new ArrayList<>();
		applications.add(application1);
		applications.add(application2);
		Mockito.when(applicationService.getAllApplications()).thenReturn(applications);

		this.mvc.perform(get("/api/job-applications/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].offerId").value(1))
				.andExpect(jsonPath("$[0].email").value("tarunbharti@hotmail.com"))
				.andExpect(jsonPath("$[0].resumeText").value("Java developer"))
				.andExpect(jsonPath("$[0].status").value(JobApplicationStatus.APPLIED.name()))
				.andExpect(jsonPath("$[1].id").value(2)).andExpect(jsonPath("$[1].offerId").value(1))
				.andExpect(jsonPath("$[1].email").value("tarunbharti1@hotmail.com"))
				.andExpect(jsonPath("$[1].resumeText").value("Java developer"))
				.andExpect(jsonPath("$[1].status").value(JobApplicationStatus.APPLIED.name()));

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

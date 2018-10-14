package com.heavenhr.recruitingprocess.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
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
import com.heavenhr.recruitingprocess.model.JobOffer;
import com.heavenhr.recruitingprocess.model.request.JobApplicationRequest;
import com.heavenhr.recruitingprocess.model.request.JobOfferRequest;
import com.heavenhr.recruitingprocess.service.JobOfferService;

@RunWith(SpringRunner.class)
@WebMvcTest(JobOfferController.class)
public class JobOfferControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private JobOfferService jobOfferService;

	@Captor
	private ArgumentCaptor<JobOfferRequest> jobOfferRequestCaptor;

	@Test
	public void testGetJobOfferById() throws Exception {
		JobOffer mockJobOffer = mockJobOffer("Java Dev");
		Mockito.when(jobOfferService.getJobOfferById(1L)).thenReturn(mockJobOffer);

		this.mvc.perform(get("/api/job-offers/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(jsonPath("$.jobTitle").value("Java Dev"))
				.andExpect(jsonPath("$.startDate").value(LocalDate.now().toString()));
	}

	@Test
	public void testGetAllJobOffers() throws Exception {
		JobOffer offer1 = mockJobOffer("Java Dev");
		JobOffer offer2 = mockJobOffer("QA Engineer");
		ReflectionTestUtils.setField(offer1, "id", 1L);
		ReflectionTestUtils.setField(offer2, "id", 2L);
		List<JobOffer> offers = new ArrayList<>();
		offers.add(offer1);
		offers.add(offer2);
		Mockito.when(jobOfferService.getAllJobOffers()).thenReturn(offers);

		this.mvc.perform(get("/api/job-offers/").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andDo(print()).andExpect(jsonPath("$[0].jobTitle").value("Java Dev"))
				.andExpect(jsonPath("$[0].startDate").value(LocalDate.now().toString()))
				.andExpect(jsonPath("$[1].jobTitle").value("QA Engineer"))
				.andExpect(jsonPath("$[0].startDate").value(LocalDate.now().toString()));
	}

	@Test
	public void testCreateJobOffer() throws Exception {
		String contentAsJson = "{\"jobTitle\":\"Java Dev\",\"startDate\":\"2018-10-15\"}";

		Mockito.when(jobOfferService.createJobOffer(Mockito.any(JobOfferRequest.class)))
				.thenReturn(mockJobOffer("Java Dev"));

		this.mvc.perform(post("/api/job-offers/").contentType(MediaType.APPLICATION_JSON).content(contentAsJson))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", CoreMatchers.endsWith("/api/job-offers/1"))).andDo(print());
	}

	@Test
	public void testApplyToJobOffer() throws Exception {
		String contentAsJson = "{\"email\":\"tarunbharti@hotmail.com\",\"resumeText\":\"core java developer\"}";

		JobApplication jobApplication = new JobApplication();
		jobApplication.setId(1L);
		Mockito.when(jobOfferService.applyToJobOffer(Mockito.anyLong(), Mockito.any(JobApplicationRequest.class)))
				.thenReturn(jobApplication);

		this.mvc.perform(post("/api/job-offers/1/job-applications").contentType(MediaType.APPLICATION_JSON)
				.content(contentAsJson)).andExpect(status().isCreated())
				.andExpect(header().string("location", CoreMatchers.endsWith("/api/job-offers/1/job-applications/1")))
				.andDo(print());
	}

	private JobOffer mockJobOffer(String title) {

		JobOffer jobOffer = new JobOffer();
		jobOffer.setId(1L);
		jobOffer.setJobTitle(title);
		jobOffer.setStartDate(LocalDate.now());
		jobOffer.setNumberOfApplications(0);

		return jobOffer;
	}

}

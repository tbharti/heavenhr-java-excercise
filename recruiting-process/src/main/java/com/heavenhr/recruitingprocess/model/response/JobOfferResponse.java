package com.heavenhr.recruitingprocess.model.response;

import java.time.LocalDate;

public class JobOfferResponse {
	
	public JobOfferResponse(Long id) {
		this.id = id;
	}

	public JobOfferResponse(Long id, String jobTitle, LocalDate startDate, Integer numberOfApplications) {

		this.id = id;
		this.jobTitle = jobTitle;
		this.startDate = startDate;
		this.numberOfApplications = numberOfApplications;
	}

	private long id;
	private String jobTitle;
	private LocalDate startDate;
	private int numberOfApplications;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public int getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(int numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

}

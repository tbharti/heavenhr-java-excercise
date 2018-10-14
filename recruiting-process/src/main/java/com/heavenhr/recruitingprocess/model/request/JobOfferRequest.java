package com.heavenhr.recruitingprocess.model.request;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

/**
 * @author tbharti
 *
 */
public class JobOfferRequest {

	@NotNull(message = "Job Title is required")
	private String jobTitle;

	@NotNull(message = "Start Date is required")
	private LocalDate startDate;

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

}

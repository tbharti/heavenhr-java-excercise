package com.heavenhr.recruitingprocess.model.response;

import com.heavenhr.recruitingprocess.model.JobApplicationStatus;

public class JobApplicationResponse {

	private long id;

	private long offerId;

	private String email;

	private String resumeText;

	private JobApplicationStatus status;

	public JobApplicationResponse(long id, long offerId, String email, String resumeText, JobApplicationStatus status) {
		this.id = id;
		this.offerId = offerId;
		this.email = email;
		this.resumeText = resumeText;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOfferId() {
		return offerId;
	}

	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public JobApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(JobApplicationStatus status) {
		this.status = status;
	}

}

package com.heavenhr.recruitingprocess.model.request;

import javax.validation.constraints.NotNull;

import com.heavenhr.recruitingprocess.model.JobApplicationStatus;

public class JobStatusUpdateRequest {
	
	@NotNull(message = "Status is requried")
	private JobApplicationStatus status;

	public JobApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(JobApplicationStatus status) {
		this.status = status;
	}
}

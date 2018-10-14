package com.heavenhr.recruitingprocess.service;

import com.heavenhr.recruitingprocess.model.JobApplication;

public interface ApplicationStatusChangeService {

	void triggerNotification(JobApplication entity, JobApplicationService oldStatus, JobApplicationService newStatus);

}

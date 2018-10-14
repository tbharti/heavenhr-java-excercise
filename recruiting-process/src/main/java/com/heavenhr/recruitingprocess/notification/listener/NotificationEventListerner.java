package com.heavenhr.recruitingprocess.notification.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.heavenhr.recruitingprocess.model.JobApplication;
import com.heavenhr.recruitingprocess.model.JobApplicationStatus;

@Component
public class NotificationEventListerner {

	private static Logger logger = LoggerFactory.getLogger(NotificationEventListerner.class);

	public void publishNotification(JobApplication jobApplication, JobApplicationStatus oldStatus) {
		notifyLog(jobApplication.getId(), jobApplication.getStatus().toString(), oldStatus.toString());
		switch (jobApplication.getStatus()) {

		case APPLIED:

			triggerAppliedEvent(jobApplication, oldStatus);
			break;

		case INVITED:

			triggerInvitedEvent(jobApplication, oldStatus);
			break;

		case REJECTED:

			triggerRejectedEvent(jobApplication, oldStatus);
			break;

		case HIRED:

			triggerHiredEvent(jobApplication, oldStatus);
			break;
		}
	}

	public void triggerAppliedEvent(JobApplication jobApplication, JobApplicationStatus oldStatus) {
		logTrigger(jobApplication.getId(), jobApplication.getStatus().toString(), oldStatus.toString());
	}

	public void triggerInvitedEvent(JobApplication jobApplication, JobApplicationStatus oldStatus) {
		logTrigger(jobApplication.getId(), jobApplication.getStatus().toString(), oldStatus.toString());
	}

	public void triggerRejectedEvent(JobApplication jobApplication, JobApplicationStatus oldStatus) {
		logTrigger(jobApplication.getId(), jobApplication.getStatus().toString(), oldStatus.toString());
	}

	public void triggerHiredEvent(JobApplication jobApplication, JobApplicationStatus oldStatus) {
		logTrigger(jobApplication.getId(), jobApplication.getStatus().toString(), oldStatus.toString());
	}

	private void logTrigger(Long id, String newStatus, String oldStatus) {
		StringBuilder sb = new StringBuilder().append("Custom Event trigger for Job Application Id : ").append(id)
				.append(" becuase has changed from Status : ").append(oldStatus).append(" to : ").append(newStatus);
		logger.info(sb.toString());
	}

	private void notifyLog(Long id, String newStatus, String oldStatus) {
		StringBuilder sb = new StringBuilder().append("Job Application status for Job application Id : ").append(id)
				.append(" changed from Status : ").append(oldStatus).append(" to : ").append(newStatus);
		logger.info(sb.toString());
	}

}

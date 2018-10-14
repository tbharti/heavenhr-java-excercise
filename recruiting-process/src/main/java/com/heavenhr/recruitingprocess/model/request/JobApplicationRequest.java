package com.heavenhr.recruitingprocess.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class JobApplicationRequest {
	
	@Email(message = "Email format is incorrect")
    @NotEmpty(message = "Email is required")
    private String email;

	@NotEmpty(message = "Resume text is required")
    private String resumeText;

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


}

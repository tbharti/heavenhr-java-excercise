# heavenhr-java-excercise

## How to run the project

* cd to the recruiting-process folder
* execute mvn package
* execute java -jar target/recruiting-process-0.0.1-SNAPSHOT.jar

	
## Use cases
1. user has to be able to create a job offer and read a single and list all offers.
1.1 Endpoint to create job offer : http://localhost:8080/api/job-offers/
1.2 Endpoint to read particular job offer : http://localhost:8080/api/job-offers/{offerId}
1.3 Endpoint to read all the job offers : http://localhost:8080/api/job-offers/

2. candidate has to be able to apply for an offer.
2.1 Endpoint to apply for an offer : http://localhost:8080/api/job-offers/{offerId}/job-applications

3. user has to be able to read one and list all applications per offer.
3.1 Endpoint to read/list all the job applications for an offer : http://localhost:8080/api/job-offers/{offerId}/job-applications

4. user has to be able to progress the status of an application.
4.1 Endpoint to progress the status of an application : http://localhost:8080/api/job-offers/{offerId}/job-applications/{applicationId}/status

5. user has to be able to track the number of applications.
5.1 Endpoint to track all of the applications : http://localhost:8080/api/job-applications

6. status change triggers a notification (*)
Class NotificationEventListerner.java implements the listener for notifications functionality.
	
## Tests
- [Unit Tests](recruiting-process/src/test/java/com/heavenhr/recruiting-process/RecruitingProcessApplicationTests.java)

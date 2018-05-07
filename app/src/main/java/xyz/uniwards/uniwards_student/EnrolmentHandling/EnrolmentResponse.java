package xyz.uniwards.uniwards_student.EnrolmentHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class EnrolmentResponse {
    private String response_message;
    private EnrolmentEntity enrolment;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public EnrolmentEntity GetEnrolment() { return this.enrolment; }
    public void SetEnrolment(EnrolmentEntity enrolment) { this.enrolment = enrolment; }
}

package xyz.uniwards.uniwards_student.EnrolmentHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/28/2018.
 */

public class EnrolmentsResponse {
    private String response_message;
    private List<EnrolmentEntity> enrolments = null;

    public List<EnrolmentEntity> GetEnrolments() {
        return enrolments;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (EnrolmentEntity uni : enrolments) {
            //uni_codes.add(uni.GetDate());
        }

        return uni_codes;
    }

    public void SetEnrolments(List<EnrolmentEntity> enrolments) {
        this.enrolments = enrolments;
    }

    public String GetResponse() { return this.response_message; }
}
package xyz.uniwards.uniwards_student.EnrolmentHandling;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class EnrolmentsResult implements IReqResult<ListResultEntity<List<EnrolmentResponse>>> {
    private EnrolmentsResponse enrolmentsData;

    public enum Type {
        ENROLMENT_GET_FAILED,
        ENROLMENT_REGISTER_FAILED,
        ENROLMENT_DELETE_FAILED,
        ENROLMENT_UPDATE_FAILED,
        ENROLMENT_GET_SUCCESS,
        ENROLMENT_REGISTER_SUCCESS,
        ENROLMENT_DELETE_SUCCESS,
        ENROLMENT_UPDATE_SUCCESS
    }

    public EnrolmentsResult(EnrolmentsResponse data) {
        this.enrolmentsData = data;
    }

    public ListResultEntity<List<EnrolmentResponse>> GetResult() {
        ListResultEntity<List<EnrolmentResponse>> pointsEnt = new ListResultEntity("", null);
        switch (GetType()) {
            case ENROLMENT_GET_FAILED:
                pointsEnt.SetResponseMessage("Failed to get enrolments!");
                break;
            case ENROLMENT_GET_SUCCESS:
                pointsEnt.SetResponseMessage("Successfully got enrolments!");
                pointsEnt.SetList(enrolmentsData.GetEnrolments());
                break;
            default:
                pointsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return pointsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(enrolmentsData.GetResponse())];
    }

    public List<EnrolmentResponse> GetEnrolments() { return enrolmentsData.GetEnrolments(); }
}
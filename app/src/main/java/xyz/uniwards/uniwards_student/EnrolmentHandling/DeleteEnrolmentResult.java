package xyz.uniwards.uniwards_student.EnrolmentHandling;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/2/2018.
 */

public class DeleteEnrolmentResult implements IReqResult<String> {
    private GenericEnrolmentResponse deleteEnrolmentsData;

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

    public DeleteEnrolmentResult(GenericEnrolmentResponse data) {this.deleteEnrolmentsData = data; }

    public String GetResult() {
        String message = "";
        switch (GetType()) {
            case ENROLMENT_GET_FAILED:
                message = "Get failed!";
                break;
            case ENROLMENT_REGISTER_FAILED:
                message = "Register failed!";
                break;
            case ENROLMENT_DELETE_FAILED:
                message = "Deleted failed!";
                break;
            case ENROLMENT_UPDATE_FAILED:
                message = "Update failed!";
                break;
            case ENROLMENT_GET_SUCCESS:
                message = "Get Succeeded!";
                break;
            case ENROLMENT_REGISTER_SUCCESS:
                message = "Register Succeeded!";
                break;
            case ENROLMENT_DELETE_SUCCESS:
                message = "Delete  Succeeded!";
                break;
            case ENROLMENT_UPDATE_SUCCESS:
                message = "Update  Succeeded!";
                break;
            default:
                message = "An error occurred!";
                break;
        }

        return message;
    }

    public NewEnrolmentResult.Type GetType() {
        return NewEnrolmentResult.Type.values()[Integer.parseInt(deleteEnrolmentsData.GetResponse())];
    }
}

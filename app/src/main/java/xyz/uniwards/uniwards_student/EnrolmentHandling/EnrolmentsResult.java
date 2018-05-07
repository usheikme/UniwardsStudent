package xyz.uniwards.uniwards_student.EnrolmentHandling;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class EnrolmentsResult implements IReqResult<ListResultEntity<EnrolmentEntity>> {
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

    public ListResultEntity<EnrolmentEntity> GetResult() {
        ListResultEntity<EnrolmentEntity> enrolmentsEnt = new ListResultEntity("", null, ListResultEntity.Type.CARD_ENROLMENT);
        switch (GetType()) {
            case ENROLMENT_GET_FAILED:
                enrolmentsEnt.SetResponseMessage("Failed to get enrolments!");
                break;
            case ENROLMENT_GET_SUCCESS:
                enrolmentsEnt.SetResponseMessage("Successfully got enrolments!");
                enrolmentsEnt.SetList(enrolmentsData.GetEnrolments());
                break;
            default:
                enrolmentsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return enrolmentsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(enrolmentsData.GetResponse())];
    }

    public List<EnrolmentEntity> GetEnrolments() { return enrolmentsData.GetEnrolments(); }
}
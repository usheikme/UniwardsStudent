package xyz.uniwards.uniwards_student.StudentHandle;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;

/**
 * Created by Umayr on 5/2/2018.
 */

public class StudentResult implements IReqResult<StudentResponse> {
    private StudentResponse studentData;

    public enum Type {
        STUDENT_GET_FAILED,
        STUDENT_REGISTER_FAILED,
        STUDENT_DELETE_FAILED,
        STUDENT_UPDATE_FAILED,
        STUDENT_GET_SUCCESS,
        STUDENT_REGISTER_SUCCESS,
        STUDENT_DELETE_SUCCESS,
        STUDENT_UPDATE_SUCCESS
    }

    public StudentResult(StudentResponse data)
    {
        this.studentData = data;
    }

    //TODD FIX FUCKUP
    public StudentResponse GetResult() {
        switch(GetType()) {
            case STUDENT_GET_FAILED:
                //studentResp.SetResponse("Failed to get student!");
                break;
            case STUDENT_GET_SUCCESS:
                //studentResp.SetResponse("Successfully got rewards!");
                break;
            default:
                //studentResp.SetResponse("An unknown error occured!");
                break;
        }

        return this.studentData;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(studentData.GetResponse())];
    }
}
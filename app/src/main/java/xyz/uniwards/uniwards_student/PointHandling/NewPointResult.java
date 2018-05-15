package xyz.uniwards.uniwards_student.PointHandling;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.PointHandling.GenericPointResponse;

/**
 * Created by Umayr on 5/9/2018.
 */

public class NewPointResult  implements IReqResult<String> {
    private GenericPointResponse newPointData;

    public enum Type {
        POINT_GET_FAILED,
        POINT_REGISTER_FAILED,
        POINT_DELETE_FAILED,
        POINT_UPDATE_FAILED,
        POINT_GET_SUCCESS,
        POINT_REGISTER_SUCCESS,
        POINT_DELETE_SUCCESS,
        POINT_UPDATE_SUCCESS
    }

    public NewPointResult(GenericPointResponse data) {
        this.newPointData = data;
    }

    public String GetResult() {
        String message = "";
        switch (GetType()) {
            case POINT_GET_FAILED:
                message = "Get failed!";
                break;
            case POINT_REGISTER_FAILED:
                message = "Register failed!";
                break;
            case POINT_DELETE_FAILED:
                message = "Deleted failed!";
                break;
            case POINT_UPDATE_FAILED:
                message = "Update failed!";
                break;
            case POINT_GET_SUCCESS:
                message = "Get Succeeded!";
                break;
            case POINT_REGISTER_SUCCESS:
                message = "Register Succeeded!";
                break;
            case POINT_DELETE_SUCCESS:
                message = "Delete  Succeeded!";
                break;
            case POINT_UPDATE_SUCCESS:
                message = "Update  Succeeded!";
                break;
            default:
                message = "An error occurred!";
                break;
        }

        return message;
    }

    public NewPointResult.Type GetType() {
        return NewPointResult.Type.values()[Integer.parseInt(newPointData.GetResponse())];
    }
}


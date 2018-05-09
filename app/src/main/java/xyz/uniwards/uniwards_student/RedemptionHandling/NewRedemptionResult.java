package xyz.uniwards.uniwards_student.RedemptionHandling;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;

/**
 * Created by Umayr on 5/9/2018.
 */

public class NewRedemptionResult  implements IReqResult<String> {
    private GenericRedemptionResponse newRedemptionData;

    public enum Type {
        REDEMPTION_GET_FAILED,
        REDEMPTION_REGISTER_FAILED,
        REDEMPTION_DELETE_FAILED,
        REDEMPTION_UPDATE_FAILED,
        REDEMPTION_GET_SUCCESS,
        REDEMPTION_REGISTER_SUCCESS,
        REDEMPTION_DELETE_SUCCESS,
        REDEMPTION_UPDATE_SUCCESS
    }

    public NewRedemptionResult(GenericRedemptionResponse data) {
        this.newRedemptionData = data;
    }

    public String GetResult() {
        String message = "";
        switch (GetType()) {
            case REDEMPTION_GET_FAILED:
                message = "Get failed!";
                break;
            case REDEMPTION_REGISTER_FAILED:
                message = "Register failed!";
                break;
            case REDEMPTION_DELETE_FAILED:
                message = "Deleted failed!";
                break;
            case REDEMPTION_UPDATE_FAILED:
                message = "Update failed!";
                break;
            case REDEMPTION_GET_SUCCESS:
                message = "Get Succeeded!";
                break;
            case REDEMPTION_REGISTER_SUCCESS:
                message = "Register Succeeded!";
                break;
            case REDEMPTION_DELETE_SUCCESS:
                message = "Delete  Succeeded!";
                break;
            case REDEMPTION_UPDATE_SUCCESS:
                message = "Update  Succeeded!";
                break;
            default:
                message = "An error occurred!";
                break;
        }

        return message;
    }

    public NewRedemptionResult.Type GetType() {
        return NewRedemptionResult.Type.values()[Integer.parseInt(newRedemptionData.GetResponse())];
    }
}


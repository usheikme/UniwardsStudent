package xyz.uniwards.uniwards_student.RedemptionHandling;

import android.util.Pair;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class RedemptionsResult implements IReqResult<ListResultEntity<List<RedemptionResponse>>> {
    private RedemptionsResponse redemptionsData;

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

    public RedemptionsResult(RedemptionsResponse data)
    {
        this.redemptionsData = data;
    }

    public ListResultEntity<List<RedemptionResponse>> GetResult() {
        ListResultEntity<List<RedemptionResponse>> pointsEnt = new ListResultEntity("", null);
        switch(GetType()) {
            case REDEMPTION_GET_FAILED:
                pointsEnt.SetResponseMessage("Failed to get redemptions!");
                break;
            case REDEMPTION_GET_SUCCESS:
                pointsEnt.SetResponseMessage("Successfully got redemptions!");
                pointsEnt.SetList(redemptionsData.GetRedemptions());
                break;
            default:
                pointsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return pointsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(redemptionsData.GetResponse())];
    }

    public List<RedemptionResponse> GetRedemptions() { return redemptionsData.GetRedemptions(); }
}
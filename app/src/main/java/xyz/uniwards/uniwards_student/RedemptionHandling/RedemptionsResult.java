package xyz.uniwards.uniwards_student.RedemptionHandling;

import android.util.Pair;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class RedemptionsResult implements IReqResult<ListResultEntity<RedemptionResponse>> {
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

    public ListResultEntity<RedemptionResponse> GetResult() {
        ListResultEntity<RedemptionResponse> redemptionsEnt = new ListResultEntity("", null, ListResultEntity.Type.CARD_REDEMPTION);
        switch(GetType()) {
            case REDEMPTION_GET_FAILED:
                redemptionsEnt.SetResponseMessage("Failed to get redemptions!");
                break;
            case REDEMPTION_GET_SUCCESS:
                redemptionsEnt.SetResponseMessage("Successfully got redemptions!");
                redemptionsEnt.SetList(redemptionsData.GetRedemptions());
                break;
            default:
                redemptionsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return redemptionsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(redemptionsData.GetResponse())];
    }

    public List<RedemptionResponse> GetRedemptions() { return redemptionsData.GetRedemptions(); }
}
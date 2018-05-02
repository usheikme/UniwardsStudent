package xyz.uniwards.uniwards_student.RewardHandling;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */

public class RewardsResult implements IReqResult<ListResultEntity<RewardResponse>> {
    private RewardsResponse rewardData;

    public enum Type {
        REWARD_GET_FAILED,
        REWARD_REGISTER_FAILED,
        REWARD_DELETE_FAILED,
        REWARD_UPDATE_FAILED,
        REWARD_GET_SUCCESS,
        REWARD_REGISTER_SUCCESS,
        REWARD_DELETE_SUCCESS,
        REWARD_UPDATE_SUCCESS
    }

    public RewardsResult(RewardsResponse data)
    {
        this.rewardData = data;
    }

    public ListResultEntity<RewardResponse> GetResult() {
        ListResultEntity<RewardResponse> rewardsEnt = new ListResultEntity("", null, ListResultEntity.Type.CARD_REDEMPTION);
        switch(GetType()) {
            case REWARD_GET_FAILED:
                rewardsEnt.SetResponseMessage("Failed to get rewardsd!");
                break;
            case REWARD_GET_SUCCESS:
                rewardsEnt.SetResponseMessage("Successfully got rewards!");
                rewardsEnt.SetList(rewardData.GetRewards());
                break;
            default:
                rewardsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return rewardsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(rewardData.GetResponse())];
    }

    public List<RewardResponse> GetRedemptions() { return rewardData.GetRewards(); }
}
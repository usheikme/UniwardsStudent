package xyz.uniwards.uniwards_student.RewardHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/28/2018.
 */

public class RewardsResponse {
    private String response_message;
    private List<RewardEntity> rewards = null;

    public List<RewardEntity> GetRewards() {
        return rewards;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (RewardEntity uni : rewards) {
            //uni_codes.add(uni.GetRequirement());
        }

        return uni_codes;
    }

    public void SetRewards(List<RewardEntity> rewards) { this.rewards = rewards; }

    public String GetResponse() { return this.response_message; }

}

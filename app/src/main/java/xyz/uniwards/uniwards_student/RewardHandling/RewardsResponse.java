package xyz.uniwards.uniwards_student.RewardHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/28/2018.
 */

public class RewardsResponse {
    private List<RewardResponse> rewards = null;

    public List<RewardResponse> GetRewards() {
        return rewards;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (RewardResponse uni : rewards) {
            uni_codes.add(uni.GetRequirement());
        }

        return uni_codes;
    }

    public void SetRewards(List<RewardResponse> rewards) {
        this.rewards = rewards;
    }

}

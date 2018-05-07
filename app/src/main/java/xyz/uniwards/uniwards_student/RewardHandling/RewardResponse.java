package xyz.uniwards.uniwards_student.RewardHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class RewardResponse {
    private String response_message;
    private RewardEntity reward;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public RewardEntity GetReward() { return this.reward; }
    public void SetReward(RewardEntity reward) { this.reward = reward; }
}

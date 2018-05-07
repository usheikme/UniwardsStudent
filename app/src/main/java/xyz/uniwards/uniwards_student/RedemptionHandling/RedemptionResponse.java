package xyz.uniwards.uniwards_student.RedemptionHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class RedemptionResponse {
    private String response_message;
    private RedemptionEntity redemption;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public RedemptionEntity GetRedemption() { return this.redemption; }
    public void SetRedemption(RedemptionEntity redemption) { this.redemption = redemption; }
}

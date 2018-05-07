package xyz.uniwards.uniwards_student.RedemptionHandling;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Umayr on 4/28/2018.
 */

public class RedemptionsResponse {
    private String response_message;
    private List<RedemptionEntity> redemptions = null;

    public List<RedemptionEntity> GetRedemptions() {
        return redemptions;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (RedemptionEntity uni : redemptions) {
            //uni_codes.add(uni.GetDate());
        }

        return uni_codes;
    }

    public void SetRedemptions(List<RedemptionEntity> redemptions) {
        this.redemptions = redemptions;
    }

    public String GetResponse() { return this.response_message; }
}
package xyz.uniwards.uniwards_student.RedemptionHandling;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Umayr on 4/28/2018.
 */

public class RedemptionsResponse {
    private List<RedemptionResponse> redemptions = null;

    public List<RedemptionResponse> GetRedemptions() {
        return redemptions;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (RedemptionResponse uni : redemptions) {
            uni_codes.add(uni.GetDate());
        }

        return uni_codes;
    }

    public void SetRedemptions(List<RedemptionResponse> redemptions) {
        this.redemptions = redemptions;
    }

}
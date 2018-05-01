package xyz.uniwards.uniwards_student.CouponHandling;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Umayr on 4/28/2018.
 */

public class CouponsResponse {
    private String response_message;
    private List<CouponResponse> coupons = null;

    public List<CouponResponse> GetCoupons() {
        return coupons;
    }

    //TODO Empty Check
    public List<String> GetUniverisityCodes() {
        List<String> uni_codes = new ArrayList<>();
        for (CouponResponse uni : coupons) {
            uni_codes.add(uni.GetName());
        }

        return uni_codes;
    }

    public void SetCoupons(List<CouponResponse> coupons) {
        this.coupons = coupons;
    }

    public String GetResponse() { return this.response_message; }
}

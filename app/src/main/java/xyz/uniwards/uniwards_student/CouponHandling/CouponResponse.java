package xyz.uniwards.uniwards_student.CouponHandling;

/**
 * Created by Umayr on 5/7/2018.
 */

public class CouponResponse {
    private String response_message;
    private CouponEntity coupon;

    public String GetResponse() { return this.response_message; }
    public void SetResponse(String response_message) { this.response_message = response_message; }

    public CouponEntity GetCoupon() { return this.coupon; }
    public void SetCoupon(CouponEntity coupon) { this.coupon = coupon; }
}

package xyz.uniwards.uniwards_student;

import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Random;

import xyz.uniwards.uniwards_student.CouponHandling.CouponResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.PointHandling.PointResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionResponse;
import xyz.uniwards.uniwards_student.RewardHandling.RewardResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;

/**
 * Created by Umayr on 5/1/2018.
 */

public class ListResultEntity<T> {
    private String response_message;
    private List<T> resultList;
    private Type cardType;

    private String[] enromentTitleFormats = {"New Enrolment"};
    private String[] pointTitleFormats = {"Congratulations!", "Wow!", "Good job!", "Look at you go!"};
    private String[] redemptionTitleFormats = {"Enjoy!", "Time to go out!"};
    private String[] uniclassTitleFormats = {"Is this the class you're looking for?!", "Pick me!"};

    private String[] enromentDescFormats = {"You've enrolled in %s"};
    //TODO EARNED FOR DOING WHAT??
    private String[] pointDescFormats = {"You've earned %s points"};
    private String[] redemptionDescFormats = {"You've redeemed %s"};
    private String[] uniclassDescFormats = {"%s"};

    public enum Type {
        CARD_ENROLMENT,
        CARD_POINT,
        CARD_REDEMPTION,
        CARD_UNICLASS
    }

    public ListResultEntity(String response_message, List<T> resultList, Type type) {
        this.response_message = response_message;
        this.resultList = resultList;
        this.cardType = type;
    }

    public String GetResponseMessage() {
        return this.response_message;
    }

    public void SetResponseMessage(String response_message) {
        this.response_message = response_message;
    }

    public List<T> GetList() {
        return this.resultList;
    }

    public void SetList(List<T> resultList) {
        this.resultList = resultList;
    }

    public Type GetType() {
        return this.cardType;
    }

    public void SetType(Type cardType) {
        this.cardType = cardType;
    }

    public String GetTitle() {
        switch (GetType()) {
            case CARD_ENROLMENT:
                return enromentTitleFormats[new Random().nextInt(enromentTitleFormats.length)];
            case CARD_POINT:
                return pointTitleFormats[new Random().nextInt(pointTitleFormats.length)];
            case CARD_REDEMPTION:
                return redemptionTitleFormats[new Random().nextInt(redemptionTitleFormats.length)];
            case CARD_UNICLASS:
                return uniclassTitleFormats[new Random().nextInt(uniclassTitleFormats.length)];
            default:
                return "Hmm?";
        }
    }

    public String GetDesc(String formatData) {
        switch (GetType()) {
            case CARD_ENROLMENT:
                return String.format(enromentDescFormats[new Random().nextInt(enromentDescFormats.length)], formatData);
            case CARD_POINT:
                return String.format(pointDescFormats[new Random().nextInt(pointDescFormats.length)], formatData);
            case CARD_REDEMPTION:
                return String.format(redemptionDescFormats[new Random().nextInt(redemptionDescFormats.length)], formatData);
            case CARD_UNICLASS:
                return String.format(uniclassDescFormats[new Random().nextInt(uniclassDescFormats.length)], formatData);
            default:
                return "Hmm?";
        }
    }

    public Integer GetIcon() {
        switch (GetType()) {
            case CARD_ENROLMENT:
                return R.mipmap.classroom_detected;
            case CARD_POINT:
                return R.mipmap.reward_given;
            case CARD_REDEMPTION:
                return R.mipmap.coupon_redeemed;
            case CARD_UNICLASS:
                return R.mipmap.classroom_detected;
            default:
                return R.mipmap.classroom_detected;
        }
    }

    public String GetFormatData(Integer index) {
        switch (GetType()) {
            case CARD_ENROLMENT:
                Log.wtf("CARD_ENROLMENT", Integer.toString(Globals.getInstance().GetEnrolmentsResult().GetResult().GetList().size()));
                EnrolmentResponse enResponse = Globals.getInstance().GetEnrolmentsResult().GetResult().GetList().get(index);
                String uniclassName = "";
                for (UniclassResponse uniclass : Globals.getInstance().GetUniclassesResult().GetResult().GetList()) {
                    if (uniclass.GetID() == enResponse.GetUniclassID()) {
                        uniclassName = uniclass.GetName();
                    }
                }
                return uniclassName;
            case CARD_POINT:
                Log.wtf("CARD_POINT", Integer.toString(Globals.getInstance().GetEnrolmentsResult().GetResult().GetList().size()));
                PointResponse pointResponse = Globals.getInstance().GetPointsResult().GetResult().GetList().get(index);
                String points = "";
                for (RewardResponse reward : Globals.getInstance().GetRewardsResult().GetResult().GetList()) {
                    if (reward.GetID() == pointResponse.GetRewardID()) {
                        points = Integer.toString(reward.GetValue());
                    }
                }
                return points;
            case CARD_REDEMPTION:
                Log.wtf("CARD_REDEMPTION", Integer.toString(Globals.getInstance().GetEnrolmentsResult().GetResult().GetList().size()));
                RedemptionResponse redemptionResponse = Globals.getInstance().GetRedemptionsResult().GetResult().GetList().get(index);
                String couponName = "";
                for (CouponResponse coupon : Globals.getInstance().GetCouponsResult().GetResult().GetList()) {
                    if (coupon.GetID() == redemptionResponse.GetCouponID()) {
                        couponName = coupon.GetName();
                    }
                }
                return couponName;
            case CARD_UNICLASS:
                    return Globals.getInstance().GetUniclassesResult().GetResult().GetList().get(index).GetName();
            default:
                return "Unknown";
        }
    }
}

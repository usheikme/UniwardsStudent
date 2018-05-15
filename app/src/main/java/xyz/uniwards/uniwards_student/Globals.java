package xyz.uniwards.uniwards_student;

import android.content.Intent;
import android.graphics.Point;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.RequestThread;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResponse;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResult;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode.PasscodeResult;
import xyz.uniwards.uniwards_student.PointHandling.PointsResult;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResult;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResult;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResult;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;

/**
 * Created by Umayr on 4/30/2018.
 */
public class Globals {
    private static Globals instance;
    private RequestThread reqThread;
    private RewardsResult rewardsResult;
    private EnrolmentsResult enrolmentsResult;
    private PointsResult pointsResult;
    private RedemptionsResult redemptionsResult;
    private UniclassesResult uniclassesResult;
    private CouponsResult couponsResult;
    private StudentResult studentResult = null;
    private PasscodeResult studentPasscodeResult;
    private PasscodeResult vendorPasscodeResult;
    private Integer id;
    private Integer[] tiers = {1000, 2000, 3000};

    public static Globals getInstance() {
        return instance;
    }

    public static void setInstance(Globals instance) {
        Globals.instance = instance;
    }

    public Globals(RewardsResult rewardsResult, EnrolmentsResult enrolmentsResult, PointsResult pointsResult, RedemptionsResult redemptionsResult) {
        this.rewardsResult = rewardsResult;
        this.enrolmentsResult = enrolmentsResult;
        this.pointsResult = pointsResult;
        this.redemptionsResult = redemptionsResult;
    }

    public  Globals() {}

    public RequestThread GetReqThread() {
        return this.reqThread;
    }

    public void SetReqThread(RequestThread reqThread) {this.reqThread = reqThread; }

    public CouponsResult GetCouponsResult() {
        return this.couponsResult;
    }

    public void SetCouponsResult(CouponsResult couponsResult) {this.couponsResult = couponsResult; }

    public RewardsResult GetRewardsResult() {
        return this.rewardsResult;
    }

    public void SetRewardsResult(RewardsResult rewardResult) {
        this.rewardsResult = rewardResult;
    }

    public EnrolmentsResult GetEnrolmentsResult() {
        return this.enrolmentsResult;
    }

    public void SetEnrolmentsResult(EnrolmentsResult enrolmentsResult) { this.enrolmentsResult = enrolmentsResult; }

    public PointsResult GetPointsResult() {
        return this.pointsResult;
    }

    public void SetPointsResult(PointsResult pointsResult) {
        this.pointsResult = pointsResult;
    }

    public RedemptionsResult GetRedemptionsResult() {
        return this.redemptionsResult;
    }

    public void SetRedemptionsResult(RedemptionsResult redemptionsResult) { this.redemptionsResult = redemptionsResult; }

    public UniclassesResult GetUniclassesResult() {
        return this.uniclassesResult;
    }

    public void SetUniclassesResult(UniclassesResult uniclassesResult) { this.uniclassesResult = uniclassesResult; }

    public StudentResult GetStudentResult() {
        return this.studentResult;
    }

    public void SetStudentResult(StudentResult studentResult) { this.studentResult = studentResult; }

    public PasscodeResult GetStudentPasscodeResult() {
        return this.studentPasscodeResult;
    }

    public void SetStudentPasscodeResult(PasscodeResult studentPasscodeResult) { this.studentPasscodeResult = studentPasscodeResult; }

    public PasscodeResult GetVendorPasscodeResult() {
        return this.vendorPasscodeResult;
    }

    public void SetVendorPasscodeResult(PasscodeResult vendorPasscodeResult) { this.vendorPasscodeResult = vendorPasscodeResult; }

    public Integer GetID() {
        return this.id;
    }

    public void SetID(Integer id) {
        this.id = id;
    }

    public static String GetCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    //TODO move to backend
    public Integer GetCurrentTier(Integer currentPoints) {
        Integer tier = 0;
        for(int x = 0; x < tiers.length; x++) {
            if(currentPoints > tiers[x])
                tier = x;
        }
        return tier;
    }

    public Integer GetNextTierValue(Integer currentPoints) {
        return tiers[GetCurrentTier(currentPoints)] - currentPoints;
    }
}
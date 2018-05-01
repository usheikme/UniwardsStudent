package xyz.uniwards.uniwards_student;

import android.content.Intent;
import android.graphics.Point;

import java.util.List;

import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.PointHandling.PointsResult;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResult;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResult;

/**
 * Created by Umayr on 4/30/2018.
 */
public class Globals {
    private static Globals instance;
    private RewardsResult rewardsResult;
    private EnrolmentsResult enrolmentsResult;
    private PointsResult pointsResult;
    private RedemptionsResult redemptionsResult;
    private Integer id;

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

    public RewardsResult GetRewardsResult() {
        return this.rewardsResult;
    }

    public void SetRewardsResult(RewardsResult rewardResult) {
        this.rewardsResult = rewardResult;
    }

    public EnrolmentsResult GetEnrolmentsResult() {
        return this.enrolmentsResult;
    }

    public void SetEnrolmentsResult(EnrolmentsResult enrolmentsResult) {
        this.enrolmentsResult = enrolmentsResult;
    }

    public PointsResult GetPointsResult() {
        return this.pointsResult;
    }

    public void SetPointsResult(PointsResult pointsResult) {
        this.pointsResult = pointsResult;
    }

    public RedemptionsResult GetRedemptionsResult() {
        return this.redemptionsResult;
    }

    public void SetRedemptionsResult(RedemptionsResult redemptionsResult) {
        this.redemptionsResult = redemptionsResult;
    }

    public Integer GetID() {
        return this.id;
    }

    public void SetID(Integer id) {
        this.id = id;
    }
}
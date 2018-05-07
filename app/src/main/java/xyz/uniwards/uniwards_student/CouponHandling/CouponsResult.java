package xyz.uniwards.uniwards_student.CouponHandling;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.IReqResult;
import xyz.uniwards.uniwards_student.ListResultEntity;

/**
 * Created by Umayr on 5/1/2018.
 */


public class CouponsResult implements IReqResult<ListResultEntity<CouponEntity>> {
    private CouponsResponse couponsData;

    public enum Type {
        COUPON_GET_FAILED,
        COUPON_REGISTER_FAILED,
        COUPON_DELETE_FAILED,
        COUPON_UPDATE_FAILED,
        COUPON_GET_SUCCESS,
        COUPON_REGISTER_SUCCESS,
        COUPON_DELETE_SUCCESS,
        COUPON_UPDATE_SUCCESS
    }

    public CouponsResult(CouponsResponse data) {
        this.couponsData = data;
    }

    public ListResultEntity<CouponEntity> GetResult() {
        ListResultEntity<CouponEntity> couponsEnt = new ListResultEntity("", null, ListResultEntity.Type.CARD_COUPON);
        switch (GetType()) {
            case COUPON_GET_FAILED:
                break;
            case COUPON_GET_SUCCESS:
                couponsEnt.SetResponseMessage("Successfully got coupons!");
                couponsEnt.SetList(couponsData.GetCoupons());
                break;
            default:
                couponsEnt.SetResponseMessage("An unknown error occured!");
                break;
        }

        return couponsEnt;
    }

    public Type GetType() {
        return Type.values()[Integer.parseInt(couponsData.GetResponse())];
    }

    public List<CouponEntity> GetCoupons() { return couponsData.GetCoupons(); }
}
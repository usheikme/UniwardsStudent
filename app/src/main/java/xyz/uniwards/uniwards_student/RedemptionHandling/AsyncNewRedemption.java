package xyz.uniwards.uniwards_student.RedemptionHandling;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.CouponHandling.CouponEntity;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.MainScreens.Popups.SuccessfulRedemption;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassEntity;

/**
 * Created by Umayr on 5/9/2018.
 */

public class AsyncNewRedemption extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private APIHelper helper;
    private GenericRedemptionResponse newRedemptionResp;
    private CouponEntity newRedemptionCoupon;

    public AsyncNewRedemption(Activity activity, String couponName) {
        this.activity = activity;
        this.newRedemptionCoupon = GetCouponByName(couponName);
        this.helper = new APIHelper();
    }

    private CouponEntity GetCouponByName(String couponName) {
        CouponEntity theClass = null;
        for (CouponEntity coupon : Globals.getInstance().GetCouponsResult().GetCoupons()) {
            Log.d(couponName, coupon.GetName());
            if (couponName.equals(coupon.GetName())) {
                Log.d("XXXXXXXXXXXXXXXXXXXX", coupon.GetName());
                theClass = coupon;
            }
        }
        return theClass;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        if (newRedemptionCoupon != null)
            NewRedemptionStub(newRedemptionCoupon.GetID(), Globals.getInstance().GetID(), Globals.GetCurrentDate());
        else
            Toast.makeText(activity, "Failed to redeem!", Toast.LENGTH_LONG).show();
        return null;
    }

    private void NewRedemptionStub(Integer coupon_id, Integer student_id, String date) {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<GenericRedemptionResponse> call = uniapi.NewRedemption(TokenHandle.token, coupon_id, student_id, date);
        call.enqueue(new Callback<GenericRedemptionResponse>() {
            @Override
            public void onResponse(Call<GenericRedemptionResponse> call, Response<GenericRedemptionResponse> response) {
                newRedemptionResp = response.body();
                HandleNewRedemptionResponse(new NewRedemptionResult(newRedemptionResp));
            }

            @Override
            public void onFailure(Call<GenericRedemptionResponse> call, Throwable t) {
                HandleNewRedemptionResponse(t);
            }
        });
    }

    private void HandleNewRedemptionResponse(NewRedemptionResult newRedemptionResult) {
        if (newRedemptionResult.GetType() == NewRedemptionResult.Type.REDEMPTION_REGISTER_SUCCESS) {
            RedemptionComplete();
        } else {
            Toast.makeText(activity, newRedemptionResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleNewRedemptionResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void RedemptionComplete() {
        Intent popup_succesfulredemption = new Intent(activity, SuccessfulRedemption.class);
        popup_succesfulredemption.putExtra("couponName", newRedemptionCoupon.GetName());
        activity.startActivity(popup_succesfulredemption);
    }
}

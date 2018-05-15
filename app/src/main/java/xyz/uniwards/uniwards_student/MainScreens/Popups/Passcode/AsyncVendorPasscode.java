package xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.ReqThreadEntity;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.CouponHandling.CouponEntity;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Redemption.FailedRedemption;
import xyz.uniwards.uniwards_student.RedemptionHandling.AsyncNewRedemption;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;

/**
 * Created by Umayr on 5/9/2018.
 */

public class AsyncVendorPasscode extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private PasscodeResponse passcodeResponse;
    private String passcode;
    private APIHelper helper;
    private String couponName;

    public AsyncVendorPasscode(Activity activity, String couponName, String passcode) {
        this.activity = activity;
        this.couponName = couponName;
        this.passcode = passcode;
        this.helper = new APIHelper();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        ValidatePasscodeStub(GetVendorIDByCouponName(couponName));
        return null;
    }

    private Integer GetVendorIDByCouponName(String couponName) {
        Integer vendorID = null;
        for (CouponEntity coupon : Globals.getInstance().GetCouponsResult().GetCoupons()) {
            Log.d(couponName, coupon.GetName());
            if (couponName.equals(coupon.GetName())) {
                Log.d("XXXXXXXXXXXXXXXXXXXX", coupon.GetName());
                vendorID = coupon.GetVendorID();
            }
        }
        return vendorID;
    }

    private void ValidatePasscodeStub(Integer vendorID) {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        retrofit2.Call<PasscodeResponse> call = uniapi.ValidateVendorPasscode(TokenHandle.token, passcode, vendorID);
        call.enqueue(new Callback<PasscodeResponse>() {
            @Override
            public void onResponse(retrofit2.Call<PasscodeResponse> call, Response<PasscodeResponse> response) {
                passcodeResponse = response.body();
                HandleVendorCodeResponse(new PasscodeResult(passcodeResponse));
            }

            @Override
            public void onFailure(retrofit2.Call<PasscodeResponse> call, Throwable t) {
                HandleVendorCodeResponse(t);
            }
        });
    }

    private void HandleVendorCodeResponse(PasscodeResult passcodeResult) {
        if (passcodeResult.GetType() == PasscodeResult.Type.PASSCODE_SUCCESS) {
            Globals.getInstance().SetVendorPasscodeResult(passcodeResult);
            OnCodeValidation(passcodeResult);
        } else {
            Toast.makeText(activity, passcodeResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleVendorCodeResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
        Intent popup_failedredemption = new Intent(activity, FailedRedemption.class);
        popup_failedredemption.putExtra("message", "Failed to query server!");
        activity.startActivity(popup_failedredemption);
    }


    private void OnCodeValidation(PasscodeResult passcodeResult) {
        ReqThreadEntity request = new ReqThreadEntity(activity, new AsyncNewRedemption(activity, couponName));
        Globals.getInstance().GetReqThread().AddRequest(request);
    }
}

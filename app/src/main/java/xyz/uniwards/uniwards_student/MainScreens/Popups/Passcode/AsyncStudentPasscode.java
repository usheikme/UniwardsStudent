package xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;

/**
 * Created by Umayr on 5/9/2018.
 */

public class AsyncStudentPasscode extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private PasscodeResponse passcodeResponse;
    private String passcode;
    private APIHelper helper;
    private String couponName;

    public AsyncStudentPasscode(Activity activity, String couponName, String passcode) {
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
        ValidatePasscodeStub();
        return null;
    }

    private void ValidatePasscodeStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        retrofit2.Call<PasscodeResponse> call = uniapi.ValidateStudentPasscode(TokenHandle.token, passcode);
        call.enqueue(new Callback<PasscodeResponse>() {
            @Override
            public void onResponse(retrofit2.Call<PasscodeResponse> call, Response<PasscodeResponse> response) {
                passcodeResponse = response.body();
                HandleStudentCodeResponse(new PasscodeResult(passcodeResponse));
            }

            @Override
            public void onFailure(retrofit2.Call<PasscodeResponse> call, Throwable t) {
                HandleStudentCodeResponse(t);
            }
        });
    }

    private void HandleStudentCodeResponse(PasscodeResult passcodeResult) {
        if (passcodeResult.GetType() == PasscodeResult.Type.PASSCODE_SUCCESS) {
            Globals.getInstance().SetStudentPasscodeResult(passcodeResult);
            OnCodeValidation(passcodeResult);
        } else {
            Toast.makeText(activity, passcodeResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    //TODO Finish activity
    private void HandleStudentCodeResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }


    private void OnCodeValidation(PasscodeResult passcodeResult) {
        Intent popup_vendorcode = new Intent(activity, VendorCode.class);
        popup_vendorcode.putExtra("couponName", couponName);
        Toast.makeText(activity, passcodeResult.GetResult(), Toast.LENGTH_LONG).show();
        activity.startActivity(popup_vendorcode);
    }
}
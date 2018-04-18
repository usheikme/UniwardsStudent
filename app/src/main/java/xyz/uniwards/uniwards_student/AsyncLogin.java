package xyz.uniwards.uniwards_student;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Umayr on 4/15/2018.
 */

public class AsyncLogin extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private LoginResponse loginResp;
    private APIHelper helper;
    private ProgressDialog pDialog;

    public AsyncLogin(Activity activity) {
        this.activity = activity;
        this.helper = new APIHelper();
    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Logging In...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        final EditText text_username = activity.findViewById(R.id.text_username);
        final EditText text_password = activity.findViewById(R.id.text_password);

        String username = text_username.getText().toString();
        String password = text_password.getText().toString();

        final View usernameFocusView = text_username;
        final View passwordFocusView = text_password;
        Boolean badUsername = false;
        Boolean badPassword = false;

        if (TextUtils.isEmpty(username)) {
            Log.i("Empty", "username");
            badUsername = true;
        }

        if (TextUtils.isEmpty(password)) {
            Log.i("Empty", "password");
            badPassword = true;
        }

        if (badUsername) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                    text_username.setError("Please enter a valid username");
                    usernameFocusView.requestFocus();
                    Log.wtf("Empty", "username2");
                }
            });

        } else if (badPassword) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                    text_password.setError("Please enter a valid password");
                    passwordFocusView.requestFocus();
                    Log.wtf("Empty", "password");
                }
            });
        }
        else {
            StudentLoginStub(text_username.getText().toString(), text_password.getText().toString());
        }

        return null;
    }


    private void StudentLoginStub(final String username, String password) {
        Log.wtf("GG", "GG");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<LoginResponse> call = uniapi.StudentLogin("", username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                loginResp = response.body();
                //if(response.isSuccessful()) {
                Log.wtf("LoginData2", response.body().toString());
                HandleLogin(new LoginResult(loginResp), username);


            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                HandleLogin(t);
                Log.wtf("LoginData1", t.toString());
            }
        });
    }

    private void HandleLogin(LoginResult loginResult, String username) {
        if (loginResult.GetLoginType() == LoginResult.Type.LOGIN_SUCCESS) {
            TokenHandle.StoreToken(loginResult.GetToken(), username);
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(activity.getApplicationContext(), DashboardActivity.class);
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                    activity.startActivity(i);
                    //activity.finish();
                }
            });
        } else {
            DismissDialog();
            Toast.makeText(activity, loginResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleLogin(Throwable t) {
        DismissDialog();
    }

    private void DismissDialog() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (pDialog.isShowing()) {
                    pDialog.dismiss();
                }
            }
        });
    }
}
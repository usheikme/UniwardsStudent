package xyz.uniwards.uniwards_student.Registration;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.MainScreens.MainActivity;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;

/**
 * Created by Umayr on 4/18/2018.
 */

public class AsyncRegister extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private RegisterResponse registerResp;
    private APIHelper helper;
    private ProgressDialog pDialog;

    public AsyncRegister(Activity activity) {
        this.activity = activity;
        this.helper = new APIHelper();
    }

    @Override
    protected void onPreExecute() {
        pDialog = new ProgressDialog(activity);
        pDialog.setMessage("Registering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        final EditText text_rfname = activity.findViewById(R.id.text_rfname);
        final EditText text_rlname = activity.findViewById(R.id.text_rlname);
        final EditText text_rmobile = activity.findViewById(R.id.text_rmobile);
        final EditText text_rdate = activity.findViewById(R.id.text_rdate);
        final EditText text_rusername = activity.findViewById(R.id.text_rusername);
        final EditText text_remail = activity.findViewById(R.id.text_remail);
        final EditText text_rpassword = activity.findViewById(R.id.text_rpassword);
        final EditText text_rpasscode = activity.findViewById(R.id.text_rpasscode);
        final RadioButton radio_rstudent = activity.findViewById(R.id.radio_rstudent);
        final RadioButton radio_rtutor = activity.findViewById(R.id.radio_rtutor);
        final Spinner spinner_uni = activity.findViewById(R.id.spinner_uni);

        //TODO when one is checked uncheck other
        Integer st_type = 0;
        if(radio_rstudent.isChecked())
            st_type = 0;
        else if(radio_rtutor.isChecked())
            st_type = 1;

        //Integer uni_id = ParseUni_id(spinner_uni.getSelectedItem().toString());
        Integer uni_id = spinner_uni.getSelectedItemPosition()+1;
        Log.wtf("UNI_ID", Integer.toString(uni_id));
        //TODO Implement parseBD
        String birthdate = "12/02/1995";

        RegisterEntity newUser = new RegisterEntity(text_rfname.getText().toString(),
                                                        text_rlname.getText().toString(),
                                                        text_rmobile.getText().toString(),
                                                        birthdate,
                                                        text_rusername.getText().toString(),
                                                        text_remail.getText().toString(),
                                                        text_rpassword.getText().toString(),
                                                        st_type,
                                                        0,
                                                        Integer.parseInt(text_rpasscode.getText().toString()),
                                                        uni_id);



       /* final View usernameFocusView = text_username;
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
        else {*/
            RegisterUserStub(newUser);
        //}

        return null;
    }


    private void RegisterUserStub(final RegisterEntity user) {
        Log.wtf("GG", "GG");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<RegisterResponse> call = uniapi.RegisterUser("", user.GetFname(),
                                                                    user.GetLname(),
                                                                    user.GetMobile(),
                                                                    user.GetBirthdate(),
                                                                    user.GetUsername(),
                                                                    user.GetEmail(),
                                                                    user.GetPassword(),
                                                                    user.GetST_type(),
                                                                    user.GetAuth_Status(),
                                                                    user.GetPasscode(),
                                                                    user.GetUni_id());
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                registerResp = response.body();
                //if(response.isSuccessful()) {
                Log.wtf("LoginData2", response.body().toString());
                HandleRegister(new RegisterResult(registerResp));


            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                HandleRegister(t);
                Log.wtf("LoginData1", t.toString());
            }
        });
    }

    private void HandleRegister(RegisterResult registerResult) {
        if (registerResult.GetType() == RegisterResult.Type.REGISTER_SUCCESS) {
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                    activity.startActivity(i);
                    //activity.finish();
                }
            });
        } else {
            DismissDialog();
            Toast.makeText(activity, registerResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleRegister(Throwable t) {
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

    //TODO implement parseuni_idd
    private Integer ParseUni_id(String item) {
        return 0;
    }
}

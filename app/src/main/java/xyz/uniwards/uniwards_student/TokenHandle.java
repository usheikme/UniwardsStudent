package xyz.uniwards.uniwards_student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Umayr on 4/16/2018.
 */

public class TokenHandle {
    private static SharedPreferences preferences;
    public static Boolean isInitialized = false;
    public static Boolean isValid = false;
    public static Boolean hasBennValidated = false;
    private static String token;

    public static void InitTokenHandle(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isInitialized = true;
    }

    public static boolean TokenExists() {
        if(isInitialized) {
            token = ReadToken();
            if(token != null)
                return true;
            else
                return false;
        }

        return false;
    }

    public static void StoreToken(String token) {
        if(isInitialized) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("TOKEN", token);
            editor.apply();
        }
    }

    public static String ReadToken() {
        token = "";
        if(isInitialized) {
            token = preferences.getString("TOKEN", "");
            if(token.length() > 0) {
                return token;
            }
        }

        return null;
    }

    public static void ValidateToken(String token, final Activity activity) {
        if(isInitialized) {
            UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
            Call<ValidateTokenResponse> call = uniapi.ValidateToken(token);
            call.enqueue(new Callback<ValidateTokenResponse>() {
                @Override
                public void onResponse(Call<ValidateTokenResponse> call, Response<ValidateTokenResponse> response) {
                    hasBennValidated = true;
                    ValidateTokenResponse resp = response.body();
                    ValidateTokenResult tokenResult = new ValidateTokenResult(resp);
                    if(tokenResult.GetType() == ValidateTokenResult.Type.TOKEN_VALID) {
                        isValid = true;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(activity.getApplicationContext(), MeinActivity.class);
                                activity.startActivity(i);
                            }
                        });
                    }
                    else {
                        isValid = false;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(activity.getApplicationContext(), LoginActivity.class);
                                activity.startActivity(i);
                            }
                        });
                    }


                }

                @Override
                public void onFailure(Call<ValidateTokenResponse> call, Throwable t) {
                    hasBennValidated = false;
                    isValid = false;
                }
            });
        }
    }
}

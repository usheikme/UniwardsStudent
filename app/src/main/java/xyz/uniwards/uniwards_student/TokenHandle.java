package xyz.uniwards.uniwards_student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

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
    public static Boolean hasBeenValidated = false;
    private static String token;

    public static void InitTokenHandle(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isInitialized = true;
    }

    public static boolean TokenExists() {
        if(isInitialized) {
            Log.e("EREKT", "CCC");
            String raw_token = ReadToken();
            if(raw_token != null)
                return true;
            else
                return false;
        }

        return false;
    }

    public static void StoreToken(String inToken, String username) {
        if(isInitialized) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.putString("TOKEN", inToken);
            editor.putString("USERNAME", username);
            editor.apply();
            editor.commit();

            Log.e("Toked", isInitialized.toString());

            token = inToken;
            isValid = true;
            hasBeenValidated = true;
        }
    }

    public static String ReadToken() {
        String raw_token = "";
        Log.e("MAHTOKE", isInitialized.toString());
        if(isInitialized) {
            raw_token = preferences.getString("TOKEN", "");
            Log.e("MAHTOK2E", "WTF");
            if(raw_token.length() > 0) {
                Log.e("MAHTOKE", raw_token);
                token = raw_token;
                return raw_token;
            }
        }

        return null;
    }

    public static String ReadUsername() {
        String username = "";
        if(isInitialized) {
            username = preferences.getString("USERNAME", "");
            if(username.length() > 0) {
                return username;
            }
        }

        return null;
    }

    public static void DeleteToken() {
        if(TokenExists()) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove("TOKEN");
            editor.remove("USERNAME");
            editor.apply();
            editor.commit();
        }
    }

    public static void ValidateToken(String token, String username, final Activity activity) {
        if(isInitialized) {
            UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
            Call<ValidateTokenResponse> call = uniapi.ValidateToken(token, username);
            call.enqueue(new Callback<ValidateTokenResponse>() {
                @Override
                public void onResponse(Call<ValidateTokenResponse> call, Response<ValidateTokenResponse> response) {
                    hasBeenValidated = true;
                    ValidateTokenResponse resp = response.body();
                    ValidateTokenResult tokenResult = new ValidateTokenResult(resp);
                    if(tokenResult.GetType() == ValidateTokenResult.Type.TOKEN_VALID) {
                        isValid = true;
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent i = new Intent(activity.getApplicationContext(), MainActivity.class);
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
                    hasBeenValidated = false;
                    isValid = false;
                }
            });
        }
    }
}

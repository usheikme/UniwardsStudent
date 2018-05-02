package xyz.uniwards.uniwards_student.TokenValidation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.Login.LoginActivity;
import xyz.uniwards.uniwards_student.MainScreens.MainActivity;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;

/**
 * Created by Umayr on 4/16/2018.
 */

public class TokenHandle {
    private static SharedPreferences preferences;
    public static Boolean isInitialized = false;
    public static Boolean isValid = false;
    public static Boolean hasBeenValidated = false;
    public static String token;
    public static String username;
    public static Integer uni_id;

    public static void InitTokenHandle(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        isInitialized = true;
    }

    public static boolean TokenExists() {
        if(isInitialized) {
            token = ReadToken();
            if(token != "")
                return true;
            else
                return false;
        }

        return false;
    }

    public static void StoreToken(String inToken, String inUsername, Integer inUniID) {
        if(isInitialized) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.putString("TOKEN", inToken);
            editor.putString("USERNAME", inUsername);
            editor.putInt("UNI_ID", inUniID);
            editor.apply();
            editor.commit();

            token = inToken;
            username = inUsername;
            uni_id = inUniID;
            isValid = true;
            hasBeenValidated = true;
        }
    }


    public static String ReadToken() {
        String raw_token = "";
        if(isInitialized) {
            raw_token = preferences.getString("TOKEN", "");
            if(raw_token.length() > 0) {
                token = raw_token;
                return raw_token;
            }
        }

        return "";
    }

    public static String ReadUsername() {
        username = "";
        if(isInitialized) {
            username = preferences.getString("USERNAME", "");
            if(username.length() > 0) {
                return username;
            }
        }

        return null;
    }

    public static Integer ReadUniID() {
        uni_id = -1;
        if(isInitialized) {
            uni_id = preferences.getInt("UNI_ID", 0);
            if(uni_id > -1) {
                return uni_id;
            }
        }

        return -1;
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

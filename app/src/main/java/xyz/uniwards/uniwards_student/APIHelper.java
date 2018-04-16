package xyz.uniwards.uniwards_student;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import retrofit2.*;
import retrofit2.converter.gson.GsonConverterFactory;
import android.util.Log;
import okhttp3.logging.*;
/**
 * Created by Umayr on 4/10/2018.
 */


public class APIHelper {
    private static Retrofit retrofit;
    private static UniwardsAPI uniwardsAPI;
    final private static String API_BASE_URL = "http://api.uniwards.xyz:5000";
    private static boolean isInitialized = false;

    public static void  initAPIHelper() {
        if(!isInitialized) {
            Log.i("Init", "GG");
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);  // <-- this is the important line!
            Retrofit.Builder builder = new Retrofit.Builder().baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create());

            retrofit = builder.client(httpClient.retryOnConnectionFailure(true).build()).build();

            uniwardsAPI = retrofit.create(UniwardsAPI.class);

            isInitialized = true;
        }
    }

    public static UniwardsAPI GetUniwardsAPI() {
        if(isInitialized) {
        }
        else {
            initAPIHelper();
        }
        return uniwardsAPI;
    }

    public void APITestCall() {
        UniwardsAPI uniapi = retrofit.create(UniwardsAPI.class);
        Call<APITest> call = uniapi.API_TEST_CALL();

        call.enqueue(new Callback<APITest>() {
            @Override
            public void onResponse(Call<APITest> call, Response<APITest> response) {
                APITest resp = response.body();
                Log.i("APITest", resp.getResponseMsg());

                //Log.d("Worked", "Worked");
            }

            @Override
            public void onFailure(Call<APITest> call, Throwable t) {
                Log.i("APITest", t.getMessage());
            }
        });
    }


}




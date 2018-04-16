package xyz.uniwards.uniwards_student;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Umayr on 4/10/2018.
 */

public interface UniwardsAPI {
    @GET("/apitest")
    Call<APITest> API_TEST_CALL();

    @GET
    Call<ValidateTokenResponse> ValidateToken(String token);

    @FormUrlEncoded
    //@Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/studentlogin")
    Call<LoginResponse> STUDENT_LOGIN(@Header("Token") String token, @Field("username") String username, @Field("password") String password);
}
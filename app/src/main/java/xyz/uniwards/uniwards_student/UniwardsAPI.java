package xyz.uniwards.uniwards_student;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Umayr on 4/10/2018.
 */

public interface UniwardsAPI {
    @GET("/apitest")
    Call<APITest> API_TEST_CALL();

    @GET("/api/validate_token/{token}/{username}")
    Call<ValidateTokenResponse> ValidateToken(@Path("token") String token, @Path("username") String username);

    @FormUrlEncoded
    @POST("/api/studentlogin")
    Call<LoginResponse> STUDENT_LOGIN(@Header("Token") String token, @Field("username") String username, @Field("password") String password);
}
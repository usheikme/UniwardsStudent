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
    Call<LoginResponse> StudentLogin(@Header("Token") String token, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/registeruser")
    Call<RegisterResponse> RegisterUser(@Header("Token") String token,
                                        @Field("fname") String fname,
                                        @Field("lname") String lname,
                                        @Field("mobile") String mobile,
                                        @Field("birth") String birthdate,
                                        @Field("username") String username,
                                        @Field("email") String email,
                                        @Field("password") String password,
                                        @Field("st_type") Integer st_type,
                                        @Field("auth_status") Integer auth_status,
                                        @Field("uni_id") Integer uni_id);
}
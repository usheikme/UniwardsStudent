package xyz.uniwards.uniwards_student.APIHandling;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import xyz.uniwards.uniwards_student.CouponHandling.CouponResponse;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResponse;
import xyz.uniwards.uniwards_student.Login.LoginResponse;
import xyz.uniwards.uniwards_student.PointHandling.PointsResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResponse;
import xyz.uniwards.uniwards_student.Registration.RegisterResponse;
import xyz.uniwards.uniwards_student.RewardHandling.RewardResponse;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResponse;
import xyz.uniwards.uniwards_student.TokenValidation.ValidateTokenResponse;
import xyz.uniwards.uniwards_student.UniversityHandling.UniversitiesResponse;
import xyz.uniwards.uniwards_student.UniversityHandling.UniversityResponse;

/**
 * Created by Umayr on 4/10/2018.
 */

public interface UniwardsAPI {
    @GET("/apitest")
    Call<APITest> API_TEST_CALL();

    @GET("/api/getuniversities")
    Call<UniversitiesResponse> GetUniversityList(@Header("Token") String token);

    //TODO check if int works > string
    @GET("/api/getuniversity/{id}")
    Call<UniversityResponse> GetUniversity(@Header("Token") String token, @Path("id") int id);

    @GET("/api/getcoupons")
    Call<CouponsResponse> GetCoupons(@Header("Token") String token);

    @GET("/api/getcouponbyid/{id}")
    Call<CouponResponse> GetCouponByID(@Header("Token") String token, @Path("id") int id);

    @GET("/api/getcouponbyvendorid/{vendor_id}")
    Call<CouponResponse> GetCouponByVendorID(@Header("Token") String token, @Path("vendor_id") int vendor_id);

    @GET("/api/getenrolments")
    Call<EnrolmentsResponse> GetEnrolments(@Header("Token") String token);

    @GET("/api/getenrolmentsbystudentid/{student_id}")
    Call<EnrolmentsResponse> GetEnrolmentsByStudentID(@Header("Token") String token, @Path("student_id") int student_id);

    @GET("/api/getenrolmentsbyclassid/{class_id}")
    Call<EnrolmentsResponse> GetEnrolmentsByClassID(@Header("Token") String token, @Path("class_id") int class_id);

    @GET("/api/getpointsbystudentid/{student_id}")
    Call<PointsResponse> GetPointsByStudentID(@Header("Token") String token, @Path("student_id") int student_id);

    @GET("/api/getpointsbytutorid/{tutor_id}")
    Call<PointsResponse> GetPointsByTutorID(@Header("Token") String token, @Path("tutor_id") int tutor_id);

    @GET("/api/getpointsbyrewardid/{reward_id}")
    Call<PointsResponse> GetPointsByRewardID(@Header("Token") String token, @Path("reward_id") int reward_id);

    @GET("/api/getredemptionbyid/{id}")
    Call<RedemptionResponse> GetRedemptionByID(@Header("Token") String token, @Path("id") int id);

    @GET("/api/getredemptions")
    Call<RedemptionsResponse> GetRedemptions(@Header("Token") String token);

    @GET("/api/getredemptionsbystudentid/{student_id}")
    Call<RedemptionsResponse> GetRedemptionsByStudentID(@Header("Token") String token, @Path("student_id") int student_id);

    @GET("/api/getredemptionsbycouponid/{coupon_id}")
    Call<RedemptionsResponse> GetRedemptionsByCouponID(@Header("Token") String token, @Path("coupon_id") int coupon_id);

    @GET("/api/getrewardbyid/{id}")
    Call<RewardResponse> GetRewardByID(@Header("Token") String token, @Path("id") int id);

    @GET("/api/getrewards")
    Call<RewardsResponse> GetRewards(@Header("Token") String token);

    @GET("/api/getrewardsbytier/{tier}")
    Call<RewardsResponse> GetRewardByTier(@Header("Token") String token, @Path("tier") int tier);

    @GET("/api/getuniclassbyname/{name}")
    Call<RewardResponse> GetUniclassByName(@Header("Token") String token, @Path("name") String name);

    @GET("/api/getuniclasses")
    Call<RewardsResponse> GetUniClasses(@Header("Token") String token);

    @GET("/api/getuniclassesbyuniid/{uniclass_id}")
    Call<RewardsResponse> GetUniclassesByUniID(@Header("Token") String token, @Path("uniclass_id") int uniclass_id);

    @GET("/api/getuniclassesbytutorid/{tutor_id}")
    Call<RewardsResponse> GetUniclassesByTutorID(@Header("Token") String token, @Path("tutor_id") int tutor_id);

    @GET("/api/validate_token/{token}/{username}")
    Call<ValidateTokenResponse> ValidateToken(@Path("token") String token, @Path("username") String username);

    @FormUrlEncoded
    @POST("/api/login")
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
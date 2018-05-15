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
import xyz.uniwards.uniwards_student.EnrolmentHandling.GenericEnrolmentResponse;
import xyz.uniwards.uniwards_student.Login.LoginResponse;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode.PasscodeResponse;
import xyz.uniwards.uniwards_student.PointHandling.GenericPointResponse;
import xyz.uniwards.uniwards_student.PointHandling.PointsResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.GenericRedemptionResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResponse;
import xyz.uniwards.uniwards_student.Registration.RegisterResponse;
import xyz.uniwards.uniwards_student.RewardHandling.RewardResponse;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResponse;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResponse;
import xyz.uniwards.uniwards_student.TokenValidation.ValidateTokenResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;
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

    @GET("/api/getcouponbytier/{tier}")
    Call<CouponsResponse> GetCouponsByTier(@Header("Token") String token, @Path("tier") int tier);

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
    Call<UniclassResponse> GetUniclassByName(@Header("Token") String token, @Path("name") String name);

    @GET("/api/getuniclasses")
    Call<UniclassesResponse> GetUniClasses(@Header("Token") String token);

    @GET("/api/getuniclassesbyuniid/{uniclass_id}")
    Call<UniclassesResponse> GetUniclassesByUniID(@Header("Token") String token, @Path("uniclass_id") int uniclass_id);

    @GET("/api/getuniclassesbytutorid/{tutor_id}")
    Call<UniclassesResponse> GetUniclassesByTutorID(@Header("Token") String token, @Path("tutor_id") int tutor_id);

    @GET("/api/getstudent")
    Call<StudentResponse> GetStudent(@Header("token") String token);

    @GET("/api/validate_token/{token}/{username}")
    Call<ValidateTokenResponse> ValidateToken(@Path("token") String token, @Path("username") String username);

    @GET("/api/validate_studentpasscode/{student_passcode}")
    Call<PasscodeResponse> ValidateStudentPasscode(@Header("Token") String token, @Path("student_passcode") String student_passcode);

    @GET("/api/validate_vendorpasscode/{vendor_passcode}/{vendor_id}")
    Call<PasscodeResponse> ValidateVendorPasscode(@Header("Token") String token,
                                                  @Path("vendor_passcode") String vendor_passcode,
                                                  @Path("vendor_id") Integer vendor_id);

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> StudentLogin(@Header("Token") String token, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("/api/newenrolment")
    Call<GenericEnrolmentResponse> NewEnrolment(@Header("Token") String token, @Field("uniclass_id") Integer uniclass_id,
                                                @Field("student_id") Integer student_id,
                                                @Field("date") String date);

    @FormUrlEncoded
    @POST("/api/deleteenrolment")
    Call<GenericEnrolmentResponse> DeleteEnrolment(@Header("Token") String token, @Field("uniclass_id") Integer uniclass_id,
                                            @Field("student_id") Integer student_id,
                                            @Field("date") String date);

    @FormUrlEncoded
    @POST("/api/newredemption")
    Call<GenericRedemptionResponse> NewRedemption(@Header("Token") String token, @Field("coupon_id") Integer coupon_id,
                                                  @Field("student_id") Integer student_id,
                                                  @Field("date") String date);

    @FormUrlEncoded
    @POST("/api/newpoint")
    Call<GenericPointResponse> NewPoint(@Header("Token") String token, @Field("student_id") Integer student_id,
                                        @Field("tutor_id") Integer tutor_id,
                                        @Field("reward_id") Integer reward_id,
                                        @Field("tutor_passcode") Integer tutor_passcode,
                                        @Field("date") String date);

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
                                        @Field("passcode") Integer passcode,
                                        @Field("uni_id") Integer uni_id);
}
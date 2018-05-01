package xyz.uniwards.uniwards_student.DashCardHandle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.CustomAdapter;
import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.PointHandling.PointsResponse;
import xyz.uniwards.uniwards_student.PointHandling.PointsResult;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResult;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResponse;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;

/**
 * Created by Umayr on 4/15/2018.
 */

public class AsyncDashCard extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private CustomAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private PointsResponse pointsResp;
    private RedemptionsResponse redemptionsResp;
    private RewardsResponse rewardsResp;
    private EnrolmentsResponse enrolmentsResp;
    private APIHelper helper;
    private ProgressDialog pDialog;
    private List<DashCard> dashCards;

    public AsyncDashCard(Activity activity, CustomAdapter mAdapter, RecyclerView mRecyclerView) {
        this.activity = activity;
        this.mAdapter = mAdapter;
        this.mRecyclerView = mRecyclerView;
        this.helper = new APIHelper();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        Globals globals = new Globals();
        globals.setInstance(globals);

        GetPointsStub();
        GetRedemptionsStub();
        GetEnrolmentsStub();
        return null;
    }

    private void GetPointsStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Globals.getInstance().SetID(1);
        Log.wtf("TokenHandle.token", TokenHandle.token.toString());
        Log.wtf("GLOBALSSTUFF", Globals.getInstance().GetID().toString());
        Call<PointsResponse> call = uniapi.GetPointsByStudentID(TokenHandle.token, Globals.getInstance().GetID());
        call.enqueue(new Callback<PointsResponse>() {
            @Override
            public void onResponse(Call<PointsResponse> call, Response<PointsResponse> response) {
                pointsResp = response.body();
                HandlePointsResponse(new PointsResult(pointsResp));
            }

            @Override
            public void onFailure(Call<PointsResponse> call, Throwable t) {
                HandlePointsResponse(t);
            }
        });
    }

    private void HandlePointsResponse(PointsResult pointsResult) {
        if (pointsResult.GetType() == PointsResult.Type.POINT_GET_SUCCESS) {
            Globals.getInstance().SetPointsResult(pointsResult);
        } else {
            Toast.makeText(activity, pointsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandlePointsResponse(Throwable t) { }

    private void GetRedemptionsStub() {
        Log.wtf("RedemptionsStub", "LOL");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<RedemptionsResponse> call = uniapi.GetRedemptionsByStudentID(TokenHandle.token, Globals.getInstance().GetID());
        call.enqueue(new Callback<RedemptionsResponse>() {
            @Override
            public void onResponse(Call<RedemptionsResponse> call, Response<RedemptionsResponse> response) {
                redemptionsResp = response.body();
                HandleRedemptionsResponse(new RedemptionsResult(redemptionsResp));
            }

            @Override
            public void onFailure(Call<RedemptionsResponse> call, Throwable t) {
                HandleRedemptionsResponse(t);
            }
        });
    }

    private void HandleRedemptionsResponse(RedemptionsResult redemptionsResult) {
        if (redemptionsResult.GetType() == RedemptionsResult.Type.REDEMPTION_GET_SUCCESS) {
            Globals.getInstance().SetRedemptionsResult(redemptionsResult);
        } else {
            Toast.makeText(activity, redemptionsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleRedemptionsResponse(Throwable t) { }

    private void GetEnrolmentsStub() {
        Log.wtf("GetEnrolmentsStub", "LOL");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<EnrolmentsResponse> call = uniapi.GetEnrolmentsByStudentID(TokenHandle.token, Globals.getInstance().GetID());
        call.enqueue(new Callback<EnrolmentsResponse>() {
            @Override
            public void onResponse(Call<EnrolmentsResponse> call, Response<EnrolmentsResponse> response) {
                enrolmentsResp = response.body();
                HandleEnrolmentsResponse(new EnrolmentsResult(enrolmentsResp));
            }

            @Override
            public void onFailure(Call<EnrolmentsResponse> call, Throwable t) {
                HandleEnrolmentsResponse(t);
            }
        });
    }

    private void HandleEnrolmentsResponse(EnrolmentsResult enrolmentsResult) {
        if (enrolmentsResult.GetType() == EnrolmentsResult.Type.ENROLMENT_GET_SUCCESS) {
            Log.wtf(enrolmentsResult.GetResult().GetResponseMessage(), "aiwa");
            Globals.getInstance().SetEnrolmentsResult(enrolmentsResult);
            AddDashCards();
        } else {
            Log.wtf(enrolmentsResult.GetResult().GetResponseMessage(), "nein");
            Toast.makeText(activity, enrolmentsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleEnrolmentsResponse(Throwable t) { Log.wtf(t.toString(), "xxc");}

    private void AddDashCards() {
        dashCards = new ArrayList<>();

        dashCards.add(new DashCard(R.mipmap.classroom_detected,"Class detected!",
                "We've detected that you're in CSIT113, click this to confirm"));
        dashCards.add(new DashCard(R.mipmap.coupon_redeemed,"Coupon Redeemed!",
                "Thank you for redeeming your 15% off at SinX Digital Solutions"));
        dashCards.add(new DashCard(R.mipmap.reward_given,"Reward Received!",
                "250 points have added to your account for attending your last 3 classes in a row"));
        dashCards.add(new DashCard(R.mipmap.reward_given,Globals.getInstance().GetEnrolmentsResult().GetResult().GetResponseMessage(),
                Integer.toString(Globals.getInstance().GetEnrolmentsResult().GetResult().GetList().get(0).GetStudentID())));

        this.mAdapter = new CustomAdapter(dashCards);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }
}
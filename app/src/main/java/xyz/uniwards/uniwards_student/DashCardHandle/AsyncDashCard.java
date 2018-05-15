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

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResponse;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResult;
import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentEntity;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.ListResultEntity;
import xyz.uniwards.uniwards_student.MainScreens.Fragments.Dashboard.DashboardAdaptor;
import xyz.uniwards.uniwards_student.PointHandling.PointEntity;
import xyz.uniwards.uniwards_student.PointHandling.PointResponse;
import xyz.uniwards.uniwards_student.PointHandling.PointsResponse;
import xyz.uniwards.uniwards_student.PointHandling.PointsResult;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionEntity;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResponse;
import xyz.uniwards.uniwards_student.RedemptionHandling.RedemptionsResult;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResult;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;
import xyz.uniwards.uniwards_student.RewardHandling.RewardsResponse;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;

/**
 * Created by Umayr on 4/15/2018.
 */

public class AsyncDashCard extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private DashboardAdaptor mAdapter;
    private RecyclerView mRecyclerView;
    private PointsResponse pointsResp;
    private RedemptionsResponse redemptionsResp;
    private RewardsResponse rewardsResp;
    private EnrolmentsResponse enrolmentsResp;
    private UniclassesResponse uniclassesResponse;
    private CouponsResponse couponsResp;
    private APIHelper helper;
    private ProgressDialog pDialog;
    private List<DashCard> dashCards;

    public AsyncDashCard(Activity activity, DashboardAdaptor mAdapter, RecyclerView mRecyclerView) {
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
        GetCouponsStub();
        GetRewardsStub();
        GetUniclassesStub();
        GetPointsStub();
        GetRedemptionsStub();
        GetEnrolmentsStub();
        return null;
    }

    private void GetCouponsStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<CouponsResponse> call = uniapi.GetCoupons(TokenHandle.token);
        call.enqueue(new Callback<CouponsResponse>() {
            @Override
            public void onResponse(Call<CouponsResponse> call, Response<CouponsResponse> response) {
                couponsResp = response.body();
                HandleCouponsResponse(new CouponsResult(couponsResp));
            }

            @Override
            public void onFailure(Call<CouponsResponse> call, Throwable t) {
                HandleCouponsResponse(t);
            }
        });
    }

    private void HandleCouponsResponse(CouponsResult couponsResult) {
        if (couponsResult.GetType() == CouponsResult.Type.COUPON_GET_SUCCESS) {
            Globals.getInstance().SetCouponsResult(couponsResult);
        } else {
            Toast.makeText(activity, couponsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleCouponsResponse(Throwable t) { Log.wtf(t.toString(), "coupon_stub");}

    private void GetRewardsStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<RewardsResponse> call = uniapi.GetRewards(TokenHandle.token);
        call.enqueue(new Callback<RewardsResponse>() {
            @Override
            public void onResponse(Call<RewardsResponse> call, Response<RewardsResponse> response) {
                rewardsResp = response.body();
                HandleRewardsResponse(new RewardsResult(rewardsResp));
            }

            @Override
            public void onFailure(Call<RewardsResponse> call, Throwable t) {
                HandleRewardsResponse(t);
            }
        });
    }

    private void HandleRewardsResponse(RewardsResult rewardsResult) {
        if (rewardsResult.GetType() == RewardsResult.Type.REWARD_GET_SUCCESS) {
            Globals.getInstance().SetRewardsResult(rewardsResult);
        } else {
            Toast.makeText(activity, rewardsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleRewardsResponse(Throwable t) { Log.wtf(t.toString(), "reward_stub");}

    private void GetPointsStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Log.wtf("TokenHandle.token", TokenHandle.token.toString());
        Globals.getInstance().SetID(1);
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

    private void HandlePointsResponse(Throwable t) { Log.wtf(t.toString(), "point_stub");}

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

    private void HandleRedemptionsResponse(Throwable t) { Log.wtf(t.toString(), "redemption_stub");}

    private void GetUniclassesStub() {
        Log.wtf("Uniclasses", "LOL");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        //TODO ADD UNI ID FOR STUDENT... 2 days later I'm a legend. The Real m9
        Call<UniclassesResponse> call = uniapi.GetUniclassesByUniID(TokenHandle.token, TokenHandle.uni_id);
        call.enqueue(new Callback<UniclassesResponse>() {
            @Override
            public void onResponse(Call<UniclassesResponse> call, Response<UniclassesResponse> response) {
                uniclassesResponse = response.body();
                HandleUniclassesResponse(new UniclassesResult(uniclassesResponse));
            }

            @Override
            public void onFailure(Call<UniclassesResponse> call, Throwable t) {
                HandleUniclassesResponse(t);
            }
        });
    }

    private void HandleUniclassesResponse(UniclassesResult uniclassesResult) {
        if (uniclassesResult.GetType() == UniclassesResult.Type.UNICLASS_GET_SUCCESS) {
            Globals.getInstance().SetUniclassesResult(uniclassesResult);
        } else {
            Toast.makeText(activity, uniclassesResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleUniclassesResponse(Throwable t) { Log.wtf(t.toString(), "uniclasses_stub");}

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

    private void HandleEnrolmentsResponse(Throwable t) { Log.wtf(t.toString(), "enrolment_stub");}

    private void AddDashCards() {
        dashCards = new ArrayList<>();
        ListResultEntity<EnrolmentEntity> enrolmentsEnt = null;
        ListResultEntity<PointEntity> pointsEnt = null;
        ListResultEntity<RedemptionEntity> redemptionsEnt = null;

        try {
        enrolmentsEnt =  Globals.getInstance().GetEnrolmentsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }

        try {
        pointsEnt =  Globals.getInstance().GetPointsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }

        try {
        redemptionsEnt =  Globals.getInstance().GetRedemptionsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }


        Integer i = 0;
        try {
            if (enrolmentsEnt != null) {
                for (EnrolmentEntity enrolment : enrolmentsEnt.GetList()) {
                    Date tempDate = null;
                    try {
                        tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(enrolment.GetDate());
                    } catch (Exception e) {
                        Log.wtf(e.toString(), "enrolmentdate");
                    }

                    dashCards.add(new DashCard(enrolmentsEnt.GetIcon(),
                            enrolmentsEnt.GetTitle(),
                            enrolmentsEnt.GetDesc(enrolmentsEnt.GetFormatData(i)),
                            tempDate));
                    Log.i("NEW CARD", "ENROLMENT");
                    i++;
                }
            }
        } catch (Exception e) { Log.wtf(e.toString(), "enrolment"); }

        i = 0;
        try {
            for (PointEntity point : pointsEnt.GetList()) {
                Date tempDate = null;
                try {
                    tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(point.GetDate());
                } catch (Exception e) {
                    Log.wtf(e.toString(), "pointdate");
                }

                dashCards.add(new DashCard(pointsEnt.GetIcon(),
                        pointsEnt.GetTitle(),
                        pointsEnt.GetDesc(pointsEnt.GetFormatData(i)),
                        tempDate));
                Log.i("NEW CARD", "POINT");
                i++;
            }
        } catch (Exception e) { Log.wtf(e.toString(), "point"); }

        i = 0;
        try {
            for (RedemptionEntity redemption : redemptionsEnt.GetList()) {
                Date tempDate = null;
                try {
                    tempDate = new SimpleDateFormat("dd/MM/yyyy").parse(redemption.GetDate());
                } catch (Exception e) {
                    Log.wtf(e.toString(), "redemptiondate");
                }

                dashCards.add(new DashCard(redemptionsEnt.GetIcon(),
                        redemptionsEnt.GetTitle(),
                        redemptionsEnt.GetDesc(redemptionsEnt.GetFormatData(i)),
                        tempDate));
                Log.i("NEW CARD", "REDEMPTION");
                i++;
            }
        } catch (Exception e) { Log.wtf(e.toString(), "redemption"); }

        Collections.sort(dashCards, new Comparator<DashCard>() {
            public int compare(DashCard o1, DashCard o2) {
                if (o1.GetCardDate() == null || o2.GetCardDate() == null)
                    return 0;
                return o1.GetCardDate().compareTo(o2.GetCardDate());
            }
        });

        this.mAdapter = new DashboardAdaptor(dashCards);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }
}
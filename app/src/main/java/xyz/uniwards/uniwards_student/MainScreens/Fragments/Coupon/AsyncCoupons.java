package xyz.uniwards.uniwards_student.MainScreens.Fragments.Coupon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.CouponHandling.CouponEntity;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResponse;
import xyz.uniwards.uniwards_student.CouponHandling.CouponsResult;
import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.ListResultEntity;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResponse;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResult;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;

/**
 * Created by Umayr on 5/7/2018.
 */

public class AsyncCoupons extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private CouponAdaptor mAdapter;
    private RecyclerView mRecyclerView;
    private StudentResponse studentResp;
    private CouponsResponse couponsResp;
    private APIHelper helper;
    private ProgressDialog pDialog;
    private List<DashCard> dashCards;

    public AsyncCoupons(Activity activity, CouponAdaptor mAdapter, RecyclerView mRecyclerView) {
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
        GetStudentStub();
        return null;
    }

    private void GetCouponsStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        //TODO BYTIER
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

    private void GetStudentStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<StudentResponse> call = uniapi.GetStudent(TokenHandle.token);
        call.enqueue(new Callback<StudentResponse>() {
            @Override
            public void onResponse(Call<StudentResponse> call, Response<StudentResponse> response) {
                studentResp = response.body();
                HandleStudentsResponse(new StudentResult(studentResp));
            }

            @Override
            public void onFailure(Call<StudentResponse> call, Throwable t) {
                HandleStudentsResponse(t);
            }
        });
    }

    private void HandleStudentsResponse(StudentResult studentResult) {
        if (studentResult.GetType() == StudentResult.Type.STUDENT_GET_SUCCESS) {
            Globals.getInstance().SetStudentResult(studentResult);
            AddDashCards();
        } else {
            Toast.makeText(activity, studentResult.GetResult().GetResponse(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleStudentsResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void AddDashCards() {
        dashCards = new ArrayList<>();
        ListResultEntity<CouponEntity> couponsEnt = null;

        try {
            couponsEnt =  Globals.getInstance().GetCouponsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }


        Integer i = 0;
        try {
            if (couponsEnt != null) {
                for (CouponEntity coupon : couponsEnt.GetList()) {
                    dashCards.add(new DashCard(couponsEnt.GetIcon(),
                            couponsEnt.GetTitle(couponsEnt.GetFormatData(i, 0), couponsEnt.GetFormatData(i, 2)),
                            couponsEnt.GetDesc(couponsEnt.GetFormatData(i, 1)),
                            null));
                    i++;
                }
            }
        } catch (Exception e) { Log.wtf(e.toString(), "coupons"); }

        Collections.sort(dashCards, new Comparator<DashCard>() {
            public int compare(DashCard o1, DashCard o2) {
                if (o1.GetCardDate() == null || o2.GetCardDate() == null)
                    return 0;
                return o1.GetCardDate().compareTo(o2.GetCardDate());
            }
        });

        //TODO move to function

        try {
            TextView text_ctitle_points = activity.findViewById(R.id.text_ctitle_points);
            text_ctitle_points.setText("Current points: " + Globals.getInstance().GetStudentResult().GetStudent().GetTotalPoints().toString());

            TextView toolbar_cusername = activity.findViewById(R.id.toolbar_cusername);
            toolbar_cusername.setText(Globals.getInstance().GetStudentResult().GetStudent().GetUsername().toString());
        } catch (Exception e) {}
        this.mAdapter = new CouponAdaptor(dashCards, this.activity);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }
}
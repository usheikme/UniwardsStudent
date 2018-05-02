package xyz.uniwards.uniwards_student.MainScreens.Popups.Enrol;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentResponse;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.ListResultEntity;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;

/**
 * Created by Umayr on 5/2/2018.
 */

public class AsyncEnrol extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private EnrolAdaptor mAdapter;
    private RecyclerView mRecyclerView;
    private UniclassesResponse uniclassesResponse;
    private APIHelper helper;
    private List<DashCard> dashCards;

    public AsyncEnrol(Activity activity, EnrolAdaptor mAdapter, RecyclerView mRecyclerView) {
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
        GetUniclassesStub();
        return null;
    }

    private void GetUniclassesStub() {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<UniclassesResponse> call = uniapi.GetUniClasses(TokenHandle.token);
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
            AddDashCards();
        } else {
            Toast.makeText(activity, uniclassesResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleUniclassesResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }


    private void AddDashCards() {
        dashCards = new ArrayList<>();
        ListResultEntity<UniclassResponse> uniclassesEnt = null;
        ListResultEntity<EnrolmentResponse> enrolmentsEnt = null;
        try {
            uniclassesEnt =  Globals.getInstance().GetUniclassesResult().GetResult();
            enrolmentsEnt =  Globals.getInstance().GetEnrolmentsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }

        Integer i = 0;
        try {
            if (uniclassesEnt != null && enrolmentsEnt != null) {
                for (UniclassResponse uniclass : uniclassesEnt.GetList()) {
                    Boolean notEnrolled = true;
                    for (EnrolmentResponse enrolment : enrolmentsEnt.GetList()) {
                        if (enrolment.GetUniclassID() == uniclass.GetID()) {
                            notEnrolled = false;
                        }
                    }

                    if(notEnrolled) {
                        //so wrong... Swapped desc and title
                        dashCards.add(new DashCard(uniclassesEnt.GetIcon(),
                                uniclassesEnt.GetDesc(uniclassesEnt.GetFormatData(i)),
                                uniclassesEnt.GetTitle(),
                                null));
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            Log.wtf(e.toString(), "LOL");
        }

        this.mAdapter = new EnrolAdaptor(dashCards);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }
}
package xyz.uniwards.uniwards_student.MainScreens.Popups.DropEnrolment;

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
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentEntity;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResponse;
import xyz.uniwards.uniwards_student.EnrolmentHandling.EnrolmentsResult;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.ListResultEntity;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Enrol.EnrolAdaptor;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassEntity;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;

/**
 * Created by Umayr on 5/2/2018.
 */

public class AsyncDrop extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private DropAdaptor mAdapter;
    private RecyclerView mRecyclerView;
    private EnrolmentsResponse enrolmentsResp;
    private APIHelper helper;
    private List<DashCard> dashCards;

    public AsyncDrop(Activity activity, DropAdaptor mAdapter, RecyclerView mRecyclerView) {
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
        GetEnrolmentsStub();
        return null;
    }

    private void GetEnrolmentsStub() {
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
            Globals.getInstance().SetEnrolmentsResult(enrolmentsResult);
            AddDashCards();
        } else {
            Toast.makeText(activity, enrolmentsResult.GetResult().GetResponseMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleEnrolmentsResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }


    private void AddDashCards() {
        dashCards = new ArrayList<>();
        ListResultEntity<UniclassEntity> uniclassesEnt = null;
        ListResultEntity<EnrolmentEntity> enrolmentsEnt = null;
        try {
            uniclassesEnt =  Globals.getInstance().GetUniclassesResult().GetResult();
            enrolmentsEnt =  Globals.getInstance().GetEnrolmentsResult().GetResult();
        } catch (Exception e) { Log.wtf(e.toString(), "LOL"); }

        Integer i = 0;
        try {
            if (uniclassesEnt != null && enrolmentsEnt != null) {
                for (UniclassEntity uniclass : uniclassesEnt.GetList()) {
                    Boolean enrolled = false;
                    for (EnrolmentEntity enrolment : enrolmentsEnt.GetList()) {
                        if (enrolment.GetUniclassID() == uniclass.GetID()) {
                            Log.wtf(uniclass.GetName(), "FALSE");
                            enrolled = true;
                        }
                    }

                    if(enrolled == true) {
                        Log.wtf("WTFISTHIS", uniclassesEnt.GetTitle());
                        //so wrong... Swapped desc and title
                        dashCards.add(new DashCard(uniclassesEnt.GetIcon(),
                                uniclassesEnt.GetTitle(uniclass.GetName()),
                                uniclassesEnt.GetDesc(uniclassesEnt.GetFormatData(i)),
                                null));
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            Log.wtf(e.toString(), "LOL");
        }

        this.mAdapter = new DropAdaptor(activity, dashCards);
        this.mRecyclerView.setAdapter(this.mAdapter);
    }
}
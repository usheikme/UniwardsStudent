package xyz.uniwards.uniwards_student.EnrolmentHandling;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassEntity;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;

/**
 * Created by Umayr on 5/2/2018.
 */

public class AsyncNewEnrolment extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private RecyclerView mRecyclerView;
    private APIHelper helper;
    private GenericEnrolmentResponse newEnrolmentResp;
    private UniclassEntity newUniclass;

    public AsyncNewEnrolment(Activity activity, String uniclassName) {
        this.activity = activity;
        this.newUniclass = GetUniclassByName(uniclassName);
        this.helper = new APIHelper();
    }

    private UniclassEntity GetUniclassByName(String uniclassName) {
        UniclassEntity theClass = null;
        for (UniclassEntity uniclass : Globals.getInstance().GetUniclassesResult().GetUniclasses()) {
            Log.d(uniclassName, uniclass.GetName());
            if (uniclassName.equals(uniclass.GetName())) {
                Log.d("XXXXXXXXXXXXXXXXXXXX", uniclass.GetName());
                theClass = uniclass;
            }
        }
        return theClass;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        if(newUniclass != null)
            NewEnrolmentStub(newUniclass.GetID(), Globals.getInstance().GetID(), Globals.GetCurrentDate());
        else
            Toast.makeText(activity, "Failed to enrol!", Toast.LENGTH_LONG).show();
        return null;
    }

    private void NewEnrolmentStub(Integer uniclass_id, Integer student_id, String date) {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<GenericEnrolmentResponse> call = uniapi.NewEnrolment(TokenHandle.token, uniclass_id, student_id, date);
        call.enqueue(new Callback<GenericEnrolmentResponse>() {
            @Override
            public void onResponse(Call<GenericEnrolmentResponse> call, Response<GenericEnrolmentResponse> response) {
                newEnrolmentResp = response.body();
                HandleNewEnrolmentResponse(new NewEnrolmentResult(newEnrolmentResp));
            }

            @Override
            public void onFailure(Call<GenericEnrolmentResponse> call, Throwable t) {
                HandleNewEnrolmentResponse(t);
            }
        });
    }

    private void HandleNewEnrolmentResponse(NewEnrolmentResult newEnrolmentResult) {
        if (newEnrolmentResult.GetType() == NewEnrolmentResult.Type.ENROLMENT_REGISTER_SUCCESS) {
            EnrolmentComplete();
        } else {
            Toast.makeText(activity, newEnrolmentResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleNewEnrolmentResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void EnrolmentComplete() {
        Toast.makeText(activity, "Successfully Enrolled!", Toast.LENGTH_LONG).show();
        activity.finish();
    }
}
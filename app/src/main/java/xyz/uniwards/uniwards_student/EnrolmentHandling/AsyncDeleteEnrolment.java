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
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResponse;
import xyz.uniwards.uniwards_student.UniclassHandling.UniclassesResult;

/**
 * Created by Umayr on 5/2/2018.
 */

public class AsyncDeleteEnrolment extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private RecyclerView mRecyclerView;
    private APIHelper helper;
    private GenericEnrolmentResponse deleteEnrolmentResp;
    private EnrolmentResponse enrolmentToDelete;
    private Integer uniclassID;
    public AsyncDeleteEnrolment(Activity activity, String uniclassName) {
        this.activity = activity;
        this.uniclassID = GetUniclassIDByName(uniclassName);
        this.enrolmentToDelete = GetEnrolmentByUniclassID(this.uniclassID);
        this.helper = new APIHelper();
    }

    private EnrolmentResponse GetEnrolmentByUniclassID(Integer uniClassID) {
        EnrolmentResponse theEnrolment = null;
        for (EnrolmentResponse enrolment : Globals.getInstance().GetEnrolmentsResult().GetEnrolments()) {
            if (uniClassID.equals(enrolment.GetUniclassID())) {
                theEnrolment = enrolment;
            }
        }
        return theEnrolment;
    }

    private Integer GetUniclassIDByName(String uniclassName) {
        Integer uniclass_id = -1;
        for (UniclassResponse uniclass : Globals.getInstance().GetUniclassesResult().GetUniclasses()) {
            if (uniclassName.equals(uniclass.GetName())) {
                uniclass_id = uniclass.GetID();
            }
        }

        return uniclass_id;
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        if(enrolmentToDelete != null)
            DeleteEnrolmentSub(enrolmentToDelete.GetUniclassID(), Globals.getInstance().GetID(), enrolmentToDelete.GetDate());
        else
            Toast.makeText(activity, "Failed to enrol!", Toast.LENGTH_LONG).show();
        return null;
    }

    private void DeleteEnrolmentSub(Integer uniclass_id, Integer student_id, String date) {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<GenericEnrolmentResponse> call = uniapi.DeleteEnrolment(TokenHandle.token, uniclass_id, student_id, date);
        call.enqueue(new Callback<GenericEnrolmentResponse>() {
            @Override
            public void onResponse(Call<GenericEnrolmentResponse> call, Response<GenericEnrolmentResponse> response) {
                deleteEnrolmentResp = response.body();
                HandleDeleteEnrolmentResponse(new DeleteEnrolmentResult(deleteEnrolmentResp));
            }

            @Override
            public void onFailure(Call<GenericEnrolmentResponse> call, Throwable t) {
                HandleDeleteEnrolmentResponse(t);
            }
        });
    }

    private void HandleDeleteEnrolmentResponse(DeleteEnrolmentResult deleteEnrolmentResult) {
        if (deleteEnrolmentResult.GetType() == NewEnrolmentResult.Type.ENROLMENT_DELETE_SUCCESS
                && this.uniclassID != -1) {
            DeleteEnrolmentComplete();
        } else {
            Toast.makeText(activity, deleteEnrolmentResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleDeleteEnrolmentResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void DeleteEnrolmentComplete() {
        Toast.makeText(activity, "Successfully dropped enrolment!", Toast.LENGTH_LONG).show();
        activity.finish();
    }
}
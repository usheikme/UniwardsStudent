package xyz.uniwards.uniwards_student.PointHandling;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Redemption.FailedRedemption;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.Globals;
/**
 * Created by Umayr on 5/15/2018.
 */

public class AsyncNewPoint extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private APIHelper helper;
    private GenericPointResponse newPointResp;
    private PointEntity newPoint;
    private Integer tutorPasscode;

    public AsyncNewPoint(Activity activity, PointEntity point, Integer tutorPasscode) {
        this.activity = activity;
        this.newPoint = point;
        this.tutorPasscode = tutorPasscode;
        this.helper = new APIHelper();
    }


    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        if (newPoint != null)
            NewPointStub(newPoint, tutorPasscode);
        else
            Toast.makeText(activity, "Failed to redeem!", Toast.LENGTH_LONG).show();
        return null;
    }

    private void NewPointStub(PointEntity point, Integer tutorPasscode) {
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();

        //TODO make generic point response
        Integer idd = point.GetTutorID();
        //Toast.makeText(activity, "TEST", Toast.LENGTH_LONG).show();
        //Toast.makeText(activity, idd.toString(), Toast.LENGTH_LONG).show();
        Call<GenericPointResponse> call = uniapi.NewPoint(TokenHandle.token, point.GetStudentID(),
                point.GetTutorID(), point.GetRewardID(), tutorPasscode, point.GetDate());
        call.enqueue(new Callback<GenericPointResponse>() {
            @Override
            public void onResponse(Call<GenericPointResponse> call, Response<GenericPointResponse> response) {
                newPointResp = response.body();
                HandleNewPointResponse(new NewPointResult(newPointResp));
            }

            @Override
            public void onFailure(Call<GenericPointResponse> call, Throwable t) {
                HandleNewPointResponse(t);
            }
        });
    }

    private void HandleNewPointResponse(NewPointResult newPointResult) {
        if (newPointResult.GetType() == NewPointResult.Type.POINT_REGISTER_SUCCESS) {
            PointComplete();
        } else {
            //Toast.makeText(activity, newPointResult.GetResult(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleNewPointResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void PointComplete() {
        Intent popup_failedredemption = new Intent(activity, FailedRedemption.class);
        popup_failedredemption.putExtra("message", "Awarded Point!");
        activity.startActivity(popup_failedredemption);
    }
}

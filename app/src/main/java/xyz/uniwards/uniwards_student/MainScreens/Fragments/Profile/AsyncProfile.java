package xyz.uniwards.uniwards_student.MainScreens.Fragments.Profile;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.StudentHandle.StudentEntity;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResponse;
import xyz.uniwards.uniwards_student.StudentHandle.StudentResult;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;

/**
 * Created by Umayr on 5/3/2018.
 */

public class AsyncProfile extends AsyncTask<Void, Void, Void> {
    private Activity activity;
    private StudentResponse studentResp;
    private APIHelper helper;

    public AsyncProfile(Activity activity) {
        this.activity = activity;
        this.helper = new APIHelper();
    }

    @Override
    protected void onPreExecute() {
    }

    @Override
    protected Void doInBackground(Void... theVoid) {
        GetStudentStub();
        return null;
    }

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
            SetupProfile(studentResult);
        } else {
            Toast.makeText(activity, studentResult.GetResult().GetResponse(), Toast.LENGTH_LONG).show();
        }
    }

    private void HandleStudentsResponse(Throwable t) {
        Log.wtf(t.toString(), "xxc");
    }

    private void SetupProfile(StudentResult studentResult) {
        StudentEntity student = studentResult.GetStudent();
        TextView text_total_points_value = activity.findViewById(R.id.text_total_points_value);
        TextView text_tier_value = activity.findViewById(R.id.text_tier_value);
        TextView text_next_tier_value = activity.findViewById(R.id.text_next_tier_value);
        TextView profile_name = activity.findViewById(R.id.profile_name);

        Log.wtf("WTF", student.toString());

        String fullName = student.GetFname() + " " + student.GetLname();
        fullName = "Umayr S";
        profile_name.setText(fullName);

        text_total_points_value.setText(student.GetTotalPoints().toString());
        text_tier_value.setText(Globals.getInstance().GetCurrentTier(student.GetTotalPoints()).toString());
        text_next_tier_value.setText(Globals.getInstance().GetNextTierValue(student.GetTotalPoints()).toString() + " pts");
    }
}
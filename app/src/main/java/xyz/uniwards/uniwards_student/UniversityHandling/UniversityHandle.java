package xyz.uniwards.uniwards_student.UniversityHandling;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import xyz.uniwards.uniwards_student.APIHandling.APIHelper;
import xyz.uniwards.uniwards_student.APIHandling.UniwardsAPI;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.Registration.RegisterEntity;
import xyz.uniwards.uniwards_student.Registration.RegisterResponse;
import xyz.uniwards.uniwards_student.Registration.RegisterResult;

/**
 * Created by Umayr on 4/18/2018.
 */

public class UniversityHandle {
    public static void GetUniversityListStub(final Spinner spinner_runi, final Activity activity) {
        Log.wtf("GG", "GG");
        UniwardsAPI uniapi = APIHelper.GetUniwardsAPI();
        Call<UniversitiesResponse> call = uniapi.GetUniversityList("");
        call.enqueue(new Callback<UniversitiesResponse>() {
            @Override
            public void onResponse(Call<UniversitiesResponse> call, Response<UniversitiesResponse> response) {
                UniversitiesResponse universitiesResponse = response.body();
                AddToUniversitySpinner(universitiesResponse.GetUniverisityCodes(), spinner_runi, activity);

            }

            @Override
            public void onFailure(Call<UniversitiesResponse> call, Throwable t) {
                //HandleRegister(t);
            }
        });
    }

    public static void AddToUniversitySpinner(List<String> items, Spinner spinner_runi, Activity activity) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity.getBaseContext(), R.layout.support_simple_spinner_dropdown_item, items);
        dataAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_runi.setAdapter(dataAdapter);
    }
}

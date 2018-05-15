package xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.goodiebag.pinview.Pinview;

import xyz.uniwards.uniwards_student.APIHandling.ReqThreadEntity;
import xyz.uniwards.uniwards_student.EnrolmentHandling.AsyncNewEnrolment;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.RedemptionHandling.AsyncNewRedemption;

public class StudentCode extends AppCompatActivity {
    private Pinview studentPinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_code);
        InitFinishedButtonAsClickable(this);
        this.studentPinView = findViewById(R.id.pinview_student);
        studentPinView.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
                //Toast.makeText(StudentCode.this, pinview.getValue().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitFinishedButtonAsClickable(final Activity thisAct) {
        final Button button_scodefinish =findViewById(R.id.button_scodefinish);
        button_scodefinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReqThreadEntity request = new ReqThreadEntity(thisAct, new AsyncStudentPasscode(thisAct, getIntent().getStringExtra("couponName"), studentPinView.getValue().toString()));
                Globals.getInstance().GetReqThread().AddRequest(request);
                finish();
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}

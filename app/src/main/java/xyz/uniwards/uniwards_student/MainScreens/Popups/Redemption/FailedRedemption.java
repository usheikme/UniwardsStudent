package xyz.uniwards.uniwards_student.MainScreens.Popups.Redemption;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xyz.uniwards.uniwards_student.MainScreens.DashboardActivity;
import xyz.uniwards.uniwards_student.R;

public class FailedRedemption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed_redemption);
        InitExitButtonAsClickable(this);

        TextView text_failed_redemption = findViewById(R.id.text_failed_redemption);
        text_failed_redemption.setText(getIntent().getStringExtra("message"));
    }

    private void InitExitButtonAsClickable(final Activity thisAct) {
        final Button button_fredemtption =findViewById(R.id.button_fredemtption);
        button_fredemtption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}

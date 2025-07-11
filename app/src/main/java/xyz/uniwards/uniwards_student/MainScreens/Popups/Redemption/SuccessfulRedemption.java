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

public class SuccessfulRedemption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successful_redemption);
        InitExitButtonAsClickable(this);

        TextView text_sucessful_redemption = findViewById(R.id.text_sucessful_redemption);
        text_sucessful_redemption.setText("Successfully redeemed: " + getIntent().getStringExtra("couponName"));
    }

    private void InitExitButtonAsClickable(final Activity thisAct) {
        final Button button_sredemtption =findViewById(R.id.button_sredemtption);
        button_sredemtption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(thisAct, DashboardActivity.class);
                startActivity(i);
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}

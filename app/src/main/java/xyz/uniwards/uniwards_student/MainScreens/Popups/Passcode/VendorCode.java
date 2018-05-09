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

public class VendorCode extends AppCompatActivity {
    private Pinview vendorPinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_code);
        InitExitButtonAsClickable(this);

        this.vendorPinView = findViewById(R.id.pinview_vendor);
        vendorPinView.setPinViewEventListener(new Pinview.PinViewEventListener() {
            @Override
            public void onDataEntered(Pinview pinview, boolean b) {
               // Toast.makeText(VendorCode.this, pinview.getValue().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void InitExitButtonAsClickable(final Activity thisAct) {
        final Button button_vcodefinish =findViewById(R.id.button_vcodefinish);
        button_vcodefinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReqThreadEntity request = new ReqThreadEntity(thisAct, new AsyncVendorPasscode(thisAct, getIntent().getStringExtra("couponName"), vendorPinView.getValue().toString()));
                Globals.getInstance().GetReqThread().AddRequest(request);
            }
        });
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }
}

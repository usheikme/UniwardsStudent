package xyz.uniwards.uniwards_student.MainScreens.Popups.QR;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import xyz.uniwards.uniwards_student.APIHandling.ReqThreadEntity;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.MainScreens.Fragments.Profile.AsyncProfile;
import xyz.uniwards.uniwards_student.PointHandling.AsyncNewPoint;
import xyz.uniwards.uniwards_student.PointHandling.PointEntity;
import xyz.uniwards.uniwards_student.R;

public class QRScanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscanner);

        ActivityCompat.requestPermissions(QRScanner.this,
                new String[]{Manifest.permission.CAMERA},
                100);

        mScannerView = new ZXingScannerView(this);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
        setContentView(mScannerView);
        //fakeResult();
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void handleResult(Result scanResult) {

        PointEntity point = ParseQRToPoint(scanResult.getText());
        if(point == null)
            return;

        Integer tutorPasscode = ParseQRPasscode(scanResult.getText());
        //Toast.makeText(this,scanResult.getText(), Toast.LENGTH_SHORT).show();
        ReqThreadEntity request = new ReqThreadEntity(this, new AsyncNewPoint(this, point, tutorPasscode));
        Globals.getInstance().GetReqThread().AddRequest(request);
    }

    public void fakeResult() {
        String fake = "1;:;1;:;1234";
        PointEntity point = ParseQRToPoint(fake);
        if(point == null)
            return;
        Integer tutorPasscode = ParseQRPasscode(fake);
        if(tutorPasscode != -1) {
            ReqThreadEntity request = new ReqThreadEntity(this, new AsyncNewPoint(this, point, tutorPasscode));
            Globals.getInstance().GetReqThread().AddRequest(request);
        }
        else {
            Toast.makeText(this,"Invalid QR Code", Toast.LENGTH_SHORT).show();
        }
    }

    //TODO Combine
    public PointEntity ParseQRToPoint(String scanResult) {
        PointEntity newPoint = null;

        try {
            String[] split = scanResult.split(";:;");
            newPoint = new PointEntity();

            newPoint.SetStudentID(Globals.getInstance().GetID());
            newPoint.SetTutorID(Integer.parseInt(split[0]));
            newPoint.SetRewardID(Integer.parseInt(split[1]));
            newPoint.SetDate(Globals.GetCurrentDate());
        } catch (Exception e) { Log.wtf("ParseQRException: ", e.toString()); }
        return newPoint;
    }

    public Integer ParseQRPasscode(String scanResult) {
        try {
            String[] split = scanResult.split(";:;");
            return Integer.parseInt(split[2]);
        } catch (Exception ArrayIndexOutOfBoundsException) { return -1; }
    }
}

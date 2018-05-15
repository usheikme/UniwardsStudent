package xyz.uniwards.uniwards_student.MainScreens.Popups.QR;

import android.Manifest;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
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
        Toast.makeText(this,scanResult.getText().toString(), Toast.LENGTH_LONG).show();
    }
}

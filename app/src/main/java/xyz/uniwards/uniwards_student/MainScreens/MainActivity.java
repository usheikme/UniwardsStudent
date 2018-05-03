package xyz.uniwards.uniwards_student.MainScreens;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import xyz.uniwards.uniwards_student.APIHandling.RequestThread;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.Login.LoginActivity;
import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.TokenValidation.TokenHandle;
import xyz.uniwards.uniwards_student.NotifcationHandle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Globals global = new Globals();
        Globals.setInstance(global);
        Globals.getInstance().SetReqThread(new RequestThread());
        new Thread(Globals.getInstance().GetReqThread()).start();

        TokenHandle.InitTokenHandle(this.getBaseContext());
        if(TokenHandle.TokenExists()) {
            Log.e("Token", "Exists");
            String token = TokenHandle.ReadToken();
            String username = TokenHandle.ReadUsername();
            if(token != "") {
                Log.e("Token-ME", token);
                TokenHandle.ValidateToken(token, username, this);
                TokenHandle.DeleteToken();
            }
        }
        else {
            Intent intent = new Intent(this, DashboardActivity.class);
            NotifcationHandle.MakeNotifcation(this, "Test Noti", "Test", R.drawable.login_pass, intent);

            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

}

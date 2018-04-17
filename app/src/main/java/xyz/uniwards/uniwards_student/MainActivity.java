package xyz.uniwards.uniwards_student;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Token", "fresj");
        TokenHandle.InitTokenHandle(this.getBaseContext());
        Log.e("Token", "xxx");
        if(TokenHandle.TokenExists()) {
            Log.e("Token", "Exists");
            String token = TokenHandle.ReadToken();
            String username = TokenHandle.ReadUsername();
            if(token != null) {
                Log.e("Token-ME", token);
                Log.e("Token", "Not Null");
                TokenHandle.ValidateToken(token, username, this);
                //TokenHandle.DeleteToken();
            }
        }
        else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

}

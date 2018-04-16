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

        TokenHandle.InitTokenHandle(this.getBaseContext());
        if(TokenHandle.TokenExists()) {
            String token = TokenHandle.ReadToken();
            if(token != null)
                TokenHandle.ValidateToken(token, this);
        }
        else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
    }

}

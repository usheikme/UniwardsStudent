package xyz.uniwards.uniwards_student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitLoginButton();
    }

    //Initialize the onclicklistener for the login button and make the button send the login request on click
    protected void InitLoginButton() {
        Toast.makeText(getBaseContext(), "FFF", Toast.LENGTH_LONG).show();;
        Button loginButton = findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "GG", Toast.LENGTH_LONG).show();;
                new AsyncLogin(LoginActivity.this).execute();
            }
        });
    }
}


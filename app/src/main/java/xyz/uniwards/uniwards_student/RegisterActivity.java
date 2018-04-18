package xyz.uniwards.uniwards_student;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitRegisterButton();
    }

    //Initialize the onclicklistener for the register button and make the button send the registration request on click
    protected void InitRegisterButton() {
        Button registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AsyncRegister(RegisterActivity.this).execute();
            }
        });
    }
}

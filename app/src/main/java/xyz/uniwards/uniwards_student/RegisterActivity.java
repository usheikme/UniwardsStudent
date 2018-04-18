package xyz.uniwards.uniwards_student;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {
    private Calendar birthdayCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitRegisterButton();
        InitBirthdayPicker();
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

    protected void InitBirthdayPicker() {
        this.birthdayCalender = Calendar.getInstance();
        final EditText text_rdate = findViewById(R.id.text_rdate);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                birthdayCalender.set(Calendar.YEAR, year);
                birthdayCalender.set(Calendar.MONTH, month);
                birthdayCalender.set(Calendar.DAY_OF_MONTH, day);
                updateDateText(text_rdate, birthdayCalender);
            }
        };

        text_rdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(RegisterActivity.this, date, birthdayCalender.get(Calendar.YEAR),
                                                                            birthdayCalender.get(Calendar.MONTH),
                                                                            birthdayCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateDateText(EditText text_rdate, Calendar birthdayCalender) {
        String dateFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        text_rdate.setText(simpleDateFormat.format(birthdayCalender.getTime()));
    }
}

package xyz.uniwards.uniwards_student.Registration;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import xyz.uniwards.uniwards_student.R;
import xyz.uniwards.uniwards_student.UniversityHandling.UniversityHandle;
import xyz.uniwards.uniwards_student.UniversityHandling.UniversityResponse;

public class RegisterActivity extends AppCompatActivity {
    private Calendar birthdayCalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        InitRegisterButton();
        InitBirthdayPicker();
        InitStudentRadioButton();
        InitTutorRadioButton();
        InitUniversitySpinner();
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

    private void InitStudentRadioButton() {
        final RadioButton radio_rstudent = findViewById(R.id.radio_rstudent);
        final RadioButton radio_rtutor = findViewById(R.id.radio_rtutor);
        radio_rstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_rtutor.setChecked(false);
                radio_rstudent.setChecked(true);
            }
        });

    }

    private void InitTutorRadioButton() {
        final RadioButton radio_rstudent = findViewById(R.id.radio_rstudent);
        final RadioButton radio_rtutor = findViewById(R.id.radio_rtutor);
        radio_rtutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radio_rstudent.setChecked(false);
                radio_rtutor.setChecked(true);
            }
        });

    }

    private void updateDateText(EditText text_rdate, Calendar birthdayCalender) {
        String dateFormat = "dd/MM/yy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        text_rdate.setText(simpleDateFormat.format(birthdayCalender.getTime()));
    }

    private void InitUniversitySpinner() {
        Spinner spinner_runi = findViewById(R.id.spinner_uni);
        //UniversityHandle.GetUniversityListStub(spinner_runi, this);
        List<String> tempList = new ArrayList<String>();
        tempList.add("UOWD");
        tempList.add("MDX");
        UniversityHandle.AddToUniversitySpinner(tempList, spinner_runi, this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }
}

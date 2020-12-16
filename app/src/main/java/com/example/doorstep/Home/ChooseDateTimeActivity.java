package com.example.doorstep.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.doorstep.R;

import java.text.DateFormat;
import java.util.Calendar;

public class ChooseDateTimeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    EditText eddate, edstarttime, edendtime;
    int isStartTime, isEndTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_date_time);
        eddate = findViewById(R.id.ed_date);
        edstarttime = findViewById(R.id.ed_startTime);
        edendtime = findViewById(R.id.ed_endTime);

    }

    public void showDatePickerDialog(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);


        String currDate = DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime());
        eddate.setText(currDate);


    }

    public void showTimePickerDialog(View view) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");

        switch (view.getId()) {
            case R.id.ed_startTime:
                isStartTime = 0;
                isEndTime = 1;
                break;
            default:
                isStartTime = 1;
                isEndTime = 0;
                break;
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String am;

        if (hourOfDay < 12) {
            am = "AM";
        } else {

            am = "PM";
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, hourOfDay);


        String time = calendar.get(Calendar.HOUR) + ":" + minute + ":" + am;

        if (isStartTime == 0) {
            edstarttime.setText(time);

        } else {
            edendtime.setText(time);

        }
    }

    public void openAddressActivity(View view) {
        Intent intent = new Intent(ChooseDateTimeActivity.this, AddressActivity.class);
        startActivity(intent);
    }
}
package com.example.doorstep.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.example.doorstep.R;

public class ServiceDetailsActivity extends AppCompatActivity {

    NumberPicker numberPicker;
    RadioButton rbyes,rbno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        numberPicker = findViewById(R.id.qty_number_picker);
        rbyes = findViewById(R.id.rb_yes);
        rbno = findViewById(R.id.rb_no);



        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(5);




    }
}
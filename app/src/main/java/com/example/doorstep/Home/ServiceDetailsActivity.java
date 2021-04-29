package com.example.doorstep.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;

import com.example.doorstep.R;

public class ServiceDetailsActivity extends AppCompatActivity {

    NumberPicker numberPicker;
    RadioButton rbyes,rbno;
    Button btnnext;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        numberPicker = findViewById(R.id.qty_number_picker);
        rbyes = findViewById(R.id.rb_yes);
        rbno = findViewById(R.id.rb_no);
        btnnext = findViewById(R.id.btn_next_date);

        bundle = getIntent().getExtras();

        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(10);
        numberPicker.setValue(5);


        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ServiceDetailsActivity.this, ChooseDateTimeActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
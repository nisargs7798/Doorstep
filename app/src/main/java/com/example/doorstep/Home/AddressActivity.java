package com.example.doorstep.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doorstep.R;

public class AddressActivity extends AppCompatActivity {

    Button btnPlaceService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        btnPlaceService = findViewById(R.id.btn_place_service);
        btnPlaceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(AddressActivity.this, "Service Request Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
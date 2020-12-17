package com.example.doorstep.loginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.chaos.view.PinView;
import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;

public class VerifyOtpActivity extends AppCompatActivity {

    Button btnVerify;
    PinView pinView;
    private String mVerificationId;
    String fromact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        btnVerify = findViewById(R.id.btn_verify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(VerifyOtpActivity.this, HomeActivity.class);
                    startActivity(intent);

            }
        });
    }
}
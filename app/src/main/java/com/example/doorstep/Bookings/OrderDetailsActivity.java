package com.example.doorstep.Bookings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;

public class OrderDetailsActivity extends AppCompatActivity {

    ImageView btnback;
    Button btnHome;

    TextView date, service_name,status;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnback = findViewById(R.id.btn_arrow_back);
        btnHome = findViewById(R.id.btn_go_to_home);
        date = findViewById(R.id.tv_order_Date_data);
        service_name = findViewById(R.id.tv_service_name);
        status = findViewById(R.id.tv_order_status);


        bundle = getIntent().getExtras();
        service_name.setText(bundle.getString("service_name"));
        date.setText(bundle.getString("date"));
        status.setText(bundle.getString("status"));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderDetailsActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
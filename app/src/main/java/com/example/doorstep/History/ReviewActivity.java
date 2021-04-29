package com.example.doorstep.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;
import com.example.doorstep.ThankYouActivity;

public class ReviewActivity extends AppCompatActivity {

    Button btnsubmit;
    ImageView btnback;
    TextView date, service_name;
    Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);


        btnsubmit = findViewById(R.id.btn_submit);
        btnback = findViewById(R.id.btn_arrow_back);
        date = findViewById(R.id.tv_service_date_review);
        service_name = findViewById(R.id.tv_service_name_review);


        bundle = getIntent().getExtras();
        service_name.setText(bundle.getString("service_name"));
        date.setText(bundle.getString("date"));
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(ReviewActivity.this, ThankYouActivity.class);
                startActivity(intent);
            }
        });
    }
}
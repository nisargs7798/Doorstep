package com.example.doorstep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doorstep.loginSignup.LoginSignupMainActivity;
import com.example.doorstep.loginSignup.RegisterActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView logo;
    TextView appname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logo=findViewById(R.id.logo);
        appname = findViewById(R.id.txt_app_name);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, LoginSignupMainActivity.class);

                //Add Shared Animation
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair(logo, "transition_splash_logo");
                pairs[1] = new Pair(appname, "transition_splash_title");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashScreenActivity.this, pairs);
                    startActivity(mainIntent, options.toBundle());
                    finish();
                } else {
                    startActivity(mainIntent);
                    finish();
                }
//
//                startActivity(mainIntent);
//                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
package com.example.doorstep.loginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;

import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginSignupMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_signup_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            finish();
            startActivity(new Intent(LoginSignupMainActivity.this, HomeActivity.class));
        }

    }

    public void callLoginScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair(findViewById(R.id.login_btn), "transition_login");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignupMainActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void callSignUpScreen(View view) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);


        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair(findViewById(R.id.signup_btn), "transition_signup");


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginSignupMainActivity.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
        }
    }
}
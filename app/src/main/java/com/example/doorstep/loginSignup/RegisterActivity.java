package com.example.doorstep.loginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.doorstep.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    FirebaseFirestore firebaseFirestore;
    TextInputLayout name, email, username, phone, password;

    //Variables
    ImageView backBtn;
    Button next, login;
    TextView titleText, slideText;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_register);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        bundle = new Bundle();

        //Hooks for animation
        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

        name = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_password);
        phone = findViewById(R.id.signup_phone);


    }

    public void callNextSigupScreen(View view) {

        if(IsDataValid()){

            bundle.putString("name", name.getEditText().getText().toString());
            bundle.putString("email", email.getEditText().getText().toString());
            bundle.putString("phone", phone.getEditText().getText().toString());
            bundle.putString("password", password.getEditText().getText().toString());
            bundle.putString("username", username.getEditText().getText().toString());

            Intent intent = new Intent(getApplicationContext(), VerifyOtpActivity.class);
            intent.putExtras(bundle);

            //Add Shared Animation
            Pair[] pairs = new Pair[3];
            pairs[0] = new Pair(backBtn, "transition_back_arrow_btn");
            pairs[1] = new Pair(next, "transition_next_btn");
            pairs[2] = new Pair(titleText, "transition_title_text");
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this, pairs);
                startActivity(intent, options.toBundle());
            } else {
                startActivity(intent);
            }
        }

    }



    private boolean IsDataValid() {
        if (name.getEditText().getText().toString().equals("")) {
            name.setError("Name is required");
            name.getEditText().requestFocus();
            return false;
        }
        else
            name.setError(null);

        if (username.getEditText().getText().toString().equals("")) {
            username.setError("username is required");
            username.getEditText().requestFocus();
            return false;
        }
        else
            username.setError(null);

        if (email.getEditText().getText().toString().equals("")) {
            email.setError("Email is required");
            email.getEditText().requestFocus();
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email.getEditText().getText().toString()).matches()){
            email.setError("Email is invalid");
            email.getEditText().requestFocus();
            return false;
        }
        else
            email.setError(null);

        if (phone.getEditText().getText().toString().equals("")) {
            phone.setError("Phone is required");
            phone.getEditText().requestFocus();
            return false;
        }
        else if(!Patterns.PHONE.matcher(phone.getEditText().getText().toString()).matches() && phone.getEditText().getText().toString().length() == 10){
            phone.setError("Phone is invalid");
            phone.getEditText().requestFocus();
            return false;
        }
        else
            phone.setError(null);
        if (password.getEditText().getText().toString().equals("")) {
            password.setError("Password is required");
            password.getEditText().requestFocus();
            return false;
        }
        else
            password.setError(null);

        if (password.getEditText().getText().toString().length() < 6) {
            password.setError("password should be more than 6 character");
            password.getEditText().requestFocus();
            return false;
        }
        else
            password.setError(null);
        return true;
    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
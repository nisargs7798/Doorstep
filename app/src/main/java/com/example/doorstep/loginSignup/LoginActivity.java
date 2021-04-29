package com.example.doorstep.loginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    TextInputLayout email, password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);

        btnlogin = findViewById(R.id.letTheUserLogIn);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithEmailPassword();
            }
        });

    }

    public void callForgetPassword(View view) {
    }

    public void letTheUserLoggedIn(View view) {
    }

    public void callSignUpFromLogin(View view) {

    }

    private void loginWithEmailPassword() {
        String stremail = email.getEditText().getText().toString().trim();
        String strpassword = password.getEditText().getText().toString().trim();

        if (!checkEmail()) {
            return;
        }
        if (!checkPassword()) {
            return;
        }
        email.setErrorEnabled(false);
        password.setErrorEnabled(false);

        //authenticate user
        auth.signInWithEmailAndPassword(stremail, strpassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                        }
                    }
//                            }
                });
    }

    private boolean checkEmail() {
        String stremail = email.getEditText().getText().toString().trim();

        if (stremail.isEmpty() || !isEmailValid(stremail)) {
            email.setErrorEnabled(true);
            email.setError(getString(R.string.err_msg_email));
            email.requestFocus();
            return false;
        }

        email.setErrorEnabled(false);
        return true;
    }

    private boolean isEmailValid(String stremail) {
        return !TextUtils.isEmpty(stremail) && android.util.Patterns.EMAIL_ADDRESS.matcher(stremail).matches();

    }

    private boolean checkPassword() {
        String strpassword = password.getEditText().getText().toString().trim();

        if (strpassword.isEmpty() || !isPasswordValid(strpassword)) {
            password.setError(getString(R.string.err_msg_password));
            password.requestFocus();
            return false;
        }
        password.setErrorEnabled(false);
        return true;
    }

    private boolean isPasswordValid(String strpassword) {
        return (password.getEditText().length() >= 6);
    }
}
package com.example.doorstep.loginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.chaos.view.PinView;
import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class VerifyOtpActivity extends AppCompatActivity {

    Button btnVerify;
    PinView pinView;
    ImageView close;
    String userId;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;


    private String mVerificationId;
    String verificationCodeBySystem;

    String phoneNo;
    private FirebaseAuth auth;
//    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    String name, email, phone, username, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_verify_otp);

        btnVerify = findViewById(R.id.btn_verify);
        close = findViewById(R.id.img_close);
        progressBar = findViewById(R.id.progress_bar);
        pinView = findViewById(R.id.OTPpinView);
        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();


        phoneNo = getIntent().getStringExtra("phoneNo");

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        email = bundle.getString("email");
        phone = bundle.getString("phone");
        username = bundle.getString("username");
        password = bundle.getString("password");

        sendVerificationCodeToUser(phone);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String pin = pinView.getText().toString();
                if(pin.isEmpty() || pin.length() < 6){
                    pinView.setError("Invalid OTP");
                    pinView.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(pin);

//                saveProfile();


//                Intent intent = new Intent(VerifyOtpActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();

            }
        });
    }

    private void verifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCodeBySystem, code);
        signInUserByCredentials(credential);
    }
    private void signInUserByCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(VerifyOtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            saveProfile();
                            Toast.makeText(VerifyOtpActivity.this, "verified", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(VerifyOtpActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void sendVerificationCodeToUser(String phNo) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+1" + phNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                progressBar.setVisibility(View.GONE);

                verifyCode(code);
            }

        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            progressBar.setVisibility(View.GONE);
            Toast.makeText(VerifyOtpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            progressBar.setVisibility(View.GONE);

            Toast.makeText(VerifyOtpActivity.this, "Code sent:  " + s, Toast.LENGTH_SHORT).show();

            verificationCodeBySystem = s;

        }
    };
    private void saveProfile() {

        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(VerifyOtpActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful())
                        {
                            progressBar.setVisibility(View.VISIBLE);

                            userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                            System.out.println("userid is :" + userId);

                            DocumentReference documentReference  = firebaseFirestore.collection("users").document(userId);

                            Map<String,Object> user = new HashMap<>();
//                            System.out.println(userDob+"+"+userEmail+"+"+userName+"+");

                            user.put("name",name);
                            user.put("email",email);
                            user.put("username", username);
                            user.put("password", password);
                            user.put("phone", phone);

                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d("firebasestore_pass", userId);
                                }
                            })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.d("firebase_fail", Objects.requireNonNull(e.getMessage()));

                                        }
                                    });

                            Toast.makeText(VerifyOtpActivity.this, "You are successfully registered", Toast.LENGTH_SHORT).show();
                            finish();
                            Intent passDataIntent = new Intent(VerifyOtpActivity.this, HomeActivity.class);
//                            Intent passDataIntent = new Intent(SignupActivity.this, HomeActivity.class);
//
                            passDataIntent.putExtra("name",name);
                            passDataIntent.putExtra("email",email);
                            startActivity(passDataIntent);
                        }
                        else
                        {
                            if(task.getException() instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(VerifyOtpActivity.this, "You are already registered", Toast.LENGTH_SHORT).show();
                            }else
                            {
                                Toast.makeText(VerifyOtpActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }


}
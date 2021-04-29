package com.example.doorstep;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.doorstep.Home.HomeActivity;
import com.example.doorstep.loginSignup.VerifyOtpActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileADisputeActivity extends Fragment {

    Button btnSubmit;
    EditText reason, description;

    String userId;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    Task<DocumentReference> documentReference;
     FirebaseAuth auth;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_file_a_dispute, container, false);

        btnSubmit = root.findViewById(R.id.btn_submit_dispute);
        reason = root.findViewById(R.id.et_dispute_reason);
        description = root.findViewById(R.id.et_dispute_description);

        auth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDispute();
//                goToHome();
                Toast.makeText(getActivity(), "Thank you for letting us know!!", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    private void saveDispute() {
        userId = Objects.requireNonNull(auth.getCurrentUser()).getUid();
        System.out.println("userid is :" + userId);

        Map<String,Object> data = new HashMap<>();
//                            System.out.println(userDob+"+"+userEmail+"+"+userName+"+");

        data.put("uid",userId);
        data.put("reason", reason.getText().toString());
        data.put("description", description.getText().toString());
        documentReference  = firebaseFirestore.collection("disputes")
                .add(data)
                .addOnSuccessListener(getActivity(), new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        goToHome();
                    }
                }).addOnFailureListener(getActivity(), new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });







    }

    public void goToHome(){
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}
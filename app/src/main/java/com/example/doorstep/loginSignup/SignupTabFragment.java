package com.example.doorstep.loginSignup;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.fragment.app.Fragment;

import com.example.doorstep.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {
    EditText firstName, lastName, email, phone, password, confpass;
    Button signup;

    FirebaseDatabase rootNode;
    DatabaseReference reference;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment, container, false);

        email = root.findViewById(R.id.et_email);
        firstName = root.findViewById(R.id.et_firstName);
        lastName = root.findViewById(R.id.et_LastName);
        password = root.findViewById(R.id.et_password);
        phone = root.findViewById(R.id.et_phone_number);
        confpass = root.findViewById(R.id.et_Confirm_password);
        signup = root.findViewById(R.id.btn_sign_up);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                rootNode = FirebaseDatabase.getInstance();
//                reference = rootNode.getReference("users");
//
//                String fname = firstName.getText().toString();
//                String lname = lastName.getText().toString();
//                String phoneno = phone.getText().toString();
//                String email_id = email.getText().toString();
//                String pwd = password.getText().toString();

//
//                userHelper helperClass = new userHelper(fname,lname,email_id,phoneno,pwd);
//                reference.child(phoneno).setValue(helperClass);


                Intent intent = new Intent(getActivity(), VerifyOtpActivity.class);
                intent.putExtra("phoneNo", phone.getText().toString());
                startActivity(intent);
            }
        });

        return root;
    }
}

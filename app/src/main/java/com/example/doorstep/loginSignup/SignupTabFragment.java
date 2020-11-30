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

public class SignupTabFragment extends Fragment {
    EditText email, phone, password, confpass;
    Button signup;

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_tab_fragment,container,false);

        email = root.findViewById(R.id.et_email);
        password = root.findViewById(R.id.et_password);
        phone = root.findViewById(R.id.et_phone_number);
        confpass = root.findViewById(R.id.et_Confirm_password);
        signup = root.findViewById(R.id.btn_sign_up);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VerifyOtpActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return root;
    }
}

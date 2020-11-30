package com.example.doorstep.loginSignup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.doorstep.R;

public class LoginTabFragment extends Fragment {

    EditText email, password;
    TextView forgotpass;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.et_email);
        password = root.findViewById(R.id.et_password);
        forgotpass = root.findViewById(R.id.tv_fgpass);
        login = root.findViewById(R.id.btnLogin);

        return root;
    }
}

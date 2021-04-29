package com.example.doorstep.profile;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.example.doorstep.Bookings.BookingsFragment;
import com.example.doorstep.History.HistoryFragment;
import com.example.doorstep.Home.FragmentChangeListener;
import com.example.doorstep.Home.HomeFragment;
import com.example.doorstep.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileTabFragment extends Fragment {

    ImageView profile;
    RelativeLayout bookings, history;
    TextInputLayout name, email,phone,password;
    TextView txtname, txtemail;
    FirebaseUser user;
    DocumentReference documentReference;
    FirebaseFirestore fstore;
    String uid;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        profile = root.findViewById(R.id.img_profile_image);
        history = root.findViewById(R.id.layout_btn_history);
        bookings = root.findViewById(R.id.layout_btn_bookings);


        name = root.findViewById(R.id.TIL_fullname);
        email = root.findViewById(R.id.TIL_email);
        phone = root.findViewById(R.id.TIL_phone);
        password = root.findViewById(R.id.TIL_password);
        txtname = root.findViewById(R.id.txt_user_name);
        txtemail = root.findViewById(R.id.txt_user_email);




        user = FirebaseAuth.getInstance().getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        uid = user.getUid();

        getUserData(uid);

        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBookingFragment();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHistoryFragment();
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            2000);
                } else {
                    startGallery();
                }
            }
        });


        return root;
    }

    private void getUserData(String uid) {
        documentReference = fstore.collection("users").document(uid);
        documentReference.addSnapshotListener(getActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                name.getEditText().setText(value.getString("name"));
                txtname.setText(value.getString("name"));
                txtemail.setText(value.getString("email"));
                email.getEditText().setText(value.getString("email"));
                phone.getEditText().setText(value.getString("phone"));
                password.getEditText().setText(value.getString("password"));
            }
        });
    }

    private void showBookingFragment() {
        Fragment fr=new BookingsFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr, "bookings");
    }

    private void showHistoryFragment() {
        Fragment fr=new HistoryFragment();
        FragmentChangeListener fc=(FragmentChangeListener)getActivity();
        fc.replaceFragment(fr, "history");
    }

    private void startGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK && data != null) {

                profile.setImageURI(data.getData());
            }
        }
    }
}

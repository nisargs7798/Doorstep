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

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

public class ProfileTabFragment extends Fragment {

    ImageView profile;
    RelativeLayout bookings, history;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);
        profile = root.findViewById(R.id.img_profile_image);
        history = root.findViewById(R.id.layout_btn_history);
        bookings = root.findViewById(R.id.layout_btn_bookings);

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

package com.example.doorstep.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.doorstep.R;
import com.example.doorstep.profile.ProfileTabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {



    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right,R.anim.exittoright,R.anim.exit_from_right,R.anim.exittoright).replace(R.id.fragment_container, new HomeFragment()).commit();


    }
    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()){
                case R.id.nav_home : selectedFragment = new HomeFragment();
                break;
                case R.id.nav_profile : selectedFragment = new ProfileTabFragment();
                break;
                default: selectedFragment = new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right,R.anim.exittoright,R.anim.exit_from_right,R.anim.exittoright).replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };
}
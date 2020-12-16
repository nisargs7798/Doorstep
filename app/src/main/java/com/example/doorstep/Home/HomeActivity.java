package com.example.doorstep.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.doorstep.History.HistoryFragment;
import com.example.doorstep.R;
import com.example.doorstep.profile.ProfileTabFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements FragmentChangeListener {


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right, R.anim.exittoright, R.anim.exit_from_right, R.anim.exittoright).replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileTabFragment();
                    break;
                case R.id.nav_history:
                    selectedFragment = new HistoryFragment();
                    break;
                default:
                    selectedFragment = new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right, R.anim.exittoright, R.anim.exit_from_right, R.anim.exittoright).replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    public void replaceFragment(Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        ;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
        if (name.equals("history"))
            bottomNavigationView.setSelectedItemId(R.id.nav_history);
        if (name.equals("bookings"))
            bottomNavigationView.setSelectedItemId(R.id.nav_booking);
    }
}
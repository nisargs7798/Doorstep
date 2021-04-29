package com.example.doorstep.Home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.doorstep.Bookings.BookingsFragment;
import com.example.doorstep.FileADisputeActivity;
import com.example.doorstep.History.HistoryFragment;
import com.example.doorstep.R;
import com.example.doorstep.loginSignup.LoginSignupMainActivity;
import com.example.doorstep.profile.ProfileTabFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class HomeActivity extends AppCompatActivity implements FragmentChangeListener, NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar;
    Menu menu;
    TextView textView;
    TextView navUsername, navemail;

    FirebaseUser user;
    DocumentReference documentReference;
    FirebaseFirestore fstore;
    String uid;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_home);

        /*---------------------Hooks------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
//        textView=findViewById(R.id.textView);
        toolbar = findViewById(R.id.topAppBar);
        /*---------------------Toolbar------------------------*/
        setSupportActionBar(toolbar);

        /*---------------------Navigation drawer menu------------------------*/
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_services);

        View headerView = navigationView.getHeaderView(0);
        navUsername = (TextView) headerView.findViewById(R.id.txt_header_user_name);
        navemail = (TextView) headerView.findViewById(R.id.txt_header_user_email);


        user = FirebaseAuth.getInstance().getCurrentUser();
        fstore = FirebaseFirestore.getInstance();
        uid = user.getUid();

        getUserData(uid);

        bottomNavigationView = findViewById(R.id.bottom_navi);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right, R.anim.exittoright, R.anim.exit_from_right, R.anim.exittoright).replace(R.id.fragment_container, new HomeFragment()).commit();


    }

    private void getUserData(String uid) {
        documentReference = fstore.collection("users").document(uid);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                navUsername.setText(value.getString("name"));
                navemail.setText(value.getString("email"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
//            finishAffinity(); // or finish();
        }
    }

    BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_services:
                    selectedFragment = new HomeFragment();
                    toolbar.setTitle("Services");
                    break;
                case R.id.nav_profile:
                    selectedFragment = new ProfileTabFragment();
                    toolbar.setTitle("Your Profile");
                    break;
                case R.id.nav_history:
                    selectedFragment = new HistoryFragment();
                    toolbar.setTitle("Order History");
                    break;
                case R.id.nav_booking:
                    selectedFragment = new BookingsFragment();
                    toolbar.setTitle("Current Bookings");
                    break;
                default:
                    selectedFragment = new HomeFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right, R.anim.exittoright, R.anim.exit_from_right, R.anim.exittoright).replace(R.id.fragment_container, selectedFragment).addToBackStack("back").commit();
            return true;
        }
    };

    @Override
    public void replaceFragment(Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(fragment.toString());
        fragmentTransaction.commit();
        if (name.equals("history"))
            bottomNavigationView.setSelectedItemId(R.id.nav_history);
        if (name.equals("bookings"))
            bottomNavigationView.setSelectedItemId(R.id.nav_booking);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_services:
                selectedFragment = new HomeFragment();
                toolbar.setTitle("Services");
                break;
            case R.id.nav_profile:
                selectedFragment = new ProfileTabFragment();
                toolbar.setTitle("Your Profile");

                break;
            case R.id.nav_logout:
                selectedFragment = null;
                FirebaseAuth.getInstance().signOut();
//                LoginManager.getInstance().logOut();
                Intent intent = new Intent(this, LoginSignupMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.nav_current_orders:
                selectedFragment = new BookingsFragment();
                toolbar.setTitle("Current Bookings");

                break;
            case R.id.nav_fileadispute:
                selectedFragment = new FileADisputeActivity();
                toolbar.setTitle("Contact Us");

                break;

            case R.id.nav_History:
                selectedFragment = new HistoryFragment();
                toolbar.setTitle("Order History");

                break;
            default:
                selectedFragment = new HomeFragment();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        if (selectedFragment != null)
            getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.exit_from_right, R.anim.exittoright, R.anim.exit_from_right, R.anim.exittoright).replace(R.id.fragment_container, selectedFragment).addToBackStack("").commit();

        return true;
    }
}
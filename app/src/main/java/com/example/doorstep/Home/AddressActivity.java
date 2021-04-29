package com.example.doorstep.Home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.doorstep.R;
import com.example.doorstep.ThankYouActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.List;

public class AddressActivity extends AppCompatActivity {


    private GoogleMap mMap;

    Button btnPlaceService;
    TextInputEditText address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        address = findViewById(R.id.txt_address);

        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));

        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize Place field list
                List<Place.Field> fieldList = Arrays.asList(Place.Field.ADDRESS,
                        Place.Field.LAT_LNG, Place.Field.NAME);

                // Create intent
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY
                        , fieldList).build(AddressActivity.this);

                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        btnPlaceService = findViewById(R.id.btn_place_service);
        btnPlaceService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddressActivity.this, ThankYouActivity.class);
                startActivity(intent);
                Toast.makeText(AddressActivity.this, "Service Request Successful", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // when success
            // initialize places
            Place place = Autocomplete.getPlaceFromIntent(data);
            address.setText(place.getAddress());

        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            // when error
            // Initialize Status
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }
}
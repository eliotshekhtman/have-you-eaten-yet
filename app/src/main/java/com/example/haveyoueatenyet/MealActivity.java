package com.example.haveyoueatenyet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MealActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        meal = (Meal) intent.getParcelableExtra("meal");
        setContentView((R.layout.activity_meal));

        TextView name = (TextView) findViewById(R.id.mealName);
        TextView id = (TextView) findViewById(R.id.mealId);
        TextView desc = (TextView) findViewById(R.id.mealDesc);
        TextView tags = (TextView) findViewById(R.id.mealTags);
        SupportMapFragment map = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        name.setText(meal.getMealName());
        id.setText("" + meal.getMealId());
        desc.setText(meal.getMealDesc());
        tags.setText("#food");
        map.getMapAsync(this);

    }

    public void register(View view) {
        //TODO: online
        Toast.makeText(this, "No online connection", Toast.LENGTH_SHORT).show();
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng(meal.getLatitude(), meal.getLongitude());
        LatLng you = new LatLng(PersonalActivity.account.getLatitude(), PersonalActivity.account.getLongtiude());
        mMap.addMarker(new MarkerOptions().position(loc).title("Where you'll eat!"));
        mMap.addMarker(new MarkerOptions().position(you).title("Your current location!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
    }
}

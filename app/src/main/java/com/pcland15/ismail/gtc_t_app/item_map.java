package com.pcland15.ismail.gtc_t_app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class item_map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    String t="";
    String p="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        p = getIntent().getStringExtra("positon");
        t= getIntent().getStringExtra("title");
    }





    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        String[] d=  p.split(",");
        LatLng sydney = new LatLng  (Double.parseDouble(d[0]), Double.parseDouble(d[1]));
        mMap.addMarker(new MarkerOptions().position(sydney).title(t));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 12.0f));

    }
}

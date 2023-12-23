package com.bankthanapat.dtcexaminationjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_map);
        } catch (Exception e) {
            Log.e("MapActivity", "เกิดข้อผิดพลาดในบล็อก ", e);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        } else {
            Log.e("MapActivity", "Cannot find SupportMapFragment in the layout");
        }

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Object tag = marker.getTag();
                if (tag == "DTC"){
                    Intent intent = new Intent(MapActivity.this, DataActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
        LatLng DTCEnterprise = new LatLng(13.676823293845516, 100.60351980000002);
        MarkerOptions markerOptions = new MarkerOptions()
                .position(DTCEnterprise)
                .title("DTC Enterprise.")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.van));
        Marker marker = googleMap.addMarker(markerOptions);
        marker.setTag("DTC");
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DTCEnterprise,16));
    }
}
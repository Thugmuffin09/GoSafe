package com.example.thugm.gosafe_v1;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.directions.route.AbstractRouting;
import com.directions.route.Route;
import com.directions.route.RouteException;
import com.directions.route.Routing;
import com.directions.route.RoutingListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

/**
 * Author: Saimir Sulaj
 * Date: December 27, 2016
 * Purpose: Activity to run the map fragment.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_layout);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

        LatLng vancouver = new LatLng(49.2827, -123.1207);
        mMap.addMarker(new MarkerOptions().position(vancouver).title("Vancouver"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(49.288691,-122.782057)).title("Coquitlam"));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(vancouver, 10);
        mMap.animateCamera(cameraUpdate);
        LatLng start = new LatLng(49.2827, -123.1207);
        LatLng end = new LatLng(49.288691,-122.782057);
        final PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.width(10);
        polylineOptions.color(Color.RED);
        RoutingListener routingListener = new RoutingListener() {
            @Override
            public void onRoutingFailure(RouteException e) {

            }

            @Override
            public void onRoutingStart() {

            }

            @Override
            public void onRoutingSuccess(ArrayList<Route> arrayList, int i) {
                for (LatLng b : arrayList.get(i).getPoints()) {
                    polylineOptions.add(b);
                }
                mMap.addPolyline(polylineOptions);
            }

            @Override
            public void onRoutingCancelled() {

            }
        };
        Routing routing = new Routing.Builder()
                .travelMode(Routing.TravelMode.DRIVING)
                .withListener(routingListener)
                .waypoints(start, end)
                .build();
        routing.execute();
    }
}

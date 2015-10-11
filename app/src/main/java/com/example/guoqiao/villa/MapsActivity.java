package com.example.guoqiao.villa;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.guoqiao.villa.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;

import helper.MapHelper;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener
{

    private GoogleMap mMap;
    private LatLng latlng;
    private CameraUpdate cameraUpdate;
    private float Zoom;
    private MapHelper mapHelper;
    private GoogleApiClient client;
    private Location currLocation;
    private LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        try {
            intializeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void intializeAll() throws IOException {
        intializeMap();
        intializeZoom();
        initializeLocation();
        setUpMap();
    }

    private void intializeMap(){
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapHelper = new MapHelper(this);
        client = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        client.connect();
        locationRequest = LocationRequest.create()
                        .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                        .setInterval(20000);
    }

    private void initializeLocation() throws IOException {
        latlng = mapHelper.geoLocation("Illinois");
    }

    private void intializeZoom(){
        Zoom = 20;
    }

    private void setUpMap() {
        cameraUpdate = CameraUpdateFactory.newLatLng(latlng);
        mMap.moveCamera(cameraUpdate);
    }

    /* interface function for find device location */
    @Override
    public void onConnected(Bundle bundle) {
        LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.e("info","current location called");
        latlng = new LatLng(location.getLatitude(), location.getLongitude());
        setUpMap();
    }
}

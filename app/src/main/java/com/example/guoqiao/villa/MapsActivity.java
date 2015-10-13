package com.example.guoqiao.villa;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.backup.SharedPreferencesBackupHelper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import adapter.MyInfoWindowAdapter;
import beans.Apartment;
import helper.MapHelper;
import helper.SharePreferenceHelper;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener,
        GoogleMap.OnMarkerClickListener
{

    private GoogleMap mMap;
    private LatLng latlng;
    private CameraUpdate cameraUpdate;
    private float zoom;
    private MapHelper mapHelper;
    private GoogleApiClient client;
    private Location currLocation;
    private LocationRequest locationRequest;
    private List<Apartment> apartments;
    private SharedPreferences sp;
    private MyInfoWindowAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        try {
            intializeAll();
        } catch (IOException e) {
            Log.e("initialAll error", e.toString());
            e.printStackTrace();
        }

    }

    private void intializeAll() throws IOException {
//        Toast.makeText(this, "map set up", Toast.LENGTH_LONG).show();
        intializeMap();
        initializeLocation();
        initializeApts();
        setUpMap();
    }

    private void intializeMap(){
        mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mMap.setOnMarkerClickListener(this);
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

    /* get dpts from sharePreference */
    public void initializeApts(){
        Apartment apartment = new Apartment();
        apartment.setLatitude(latlng.latitude);
        apartment.setLongitude(latlng.longitude);
        apartment.setPrice("$1000 per month");
        apartment.setLocality("Champign");
        apartment.setRating("4");
        apartment.setBathroom("3");
        apartment.setBedroom("2");
        apartment.setKitchen("1");
        apartment.setLandlord("TSM");

        apartments = new ArrayList<Apartment>();
        apartments.add(apartment);

        for(int i = 0; i < apartments.size(); i++){
            Apartment temp = apartments.get(i);
            mMap.addMarker(new MarkerOptions().title(String.valueOf(i)).position(new LatLng(temp.getLatitude(), temp.getLongitude())));
        }

    }

    private void initializeLocation() throws IOException {
        latlng = mapHelper.geoLocation("Champaign");
        setZoom(14);
    }

    private void setZoom(float zoom){
        this.zoom = zoom;
    }

    private void setUpMap() {
        cameraUpdate = CameraUpdateFactory.newLatLngZoom(latlng, zoom);
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        int id = Integer.valueOf(marker.getTitle());
        Apartment apartment = apartments.get(id);

        SharePreferenceHelper helper = new SharePreferenceHelper(this);
        helper.storeApt(apartment);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Apartment Info")
                .setMessage("Address: " + apartment.getLocality() + "\n" +
                            "Landlord: " + apartment.getLandlord() + "\n" +
                            "Price: " + apartment.getPrice() + "\n" +
                            "Rating: " + apartment.getRating() + "\n")
                .setPositiveButton("More Detail", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialogInterface, int id){
                        Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancle", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        dialog.cancel();
                    }
                }).show();

        return true;
    }

}

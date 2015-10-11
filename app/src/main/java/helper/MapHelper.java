package helper;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import beans.Department;

/**
 * Created by Guoqiao on 10/11/15.
 */
public class MapHelper {
    private Geocoder gc;
    private LatLng ll;

    public MapHelper(Context context){
        gc = new Geocoder(context);
    }

    /* get lat and lon with given address name */
    public LatLng geoLocation(String s) throws IOException {
        List<Address> list = gc.getFromLocationName(s, 1);
        Address address = list.get(0);
        ll = new LatLng(address.getLatitude(),address.getLongitude());
        return  ll;
    }

    /* give user address, find all possible renting nearby */
    public List<Department> findNearbyApt(LatLng ll){


        return null;
    }

}

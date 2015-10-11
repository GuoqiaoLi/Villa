package helper;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Guoqiao on 10/11/15.
 */
public class DistanceHelper {
    public boolean inRange(LatLng ll1, LatLng ll2, double range){
        double distance = countDistance(ll1, ll2);
        if(distance < range) return true;
        return false;
    }

    public double countDistance(LatLng ll1, LatLng ll2){
        double latA = ll1.latitude;
        double lonA = ll1.longitude;

        double latB = ll2.latitude;
        double lonB = ll2.longitude;

        return Math.sqrt((latA-latB)*(latA-latB) + (lonA-lonB)*(lonA-lonB));
    }
}

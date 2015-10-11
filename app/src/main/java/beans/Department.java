package beans;

import java.util.List;

/**
 * Created by Guoqiao on 10/11/15.
 */
public class Department {
    private String type;
    private String locality;
    private List<String> amenities;
    private String price;
    private String bedroom;
    private String bathroom;
    private double longitude;
    private double latitude;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

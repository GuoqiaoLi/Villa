package adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guoqiao.villa.MainActivity;
import com.example.guoqiao.villa.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.google.gson.Gson;

import java.util.List;

import beans.Apartment;

/**
 * Created by Guoqiao on 10/12/15.
 */
public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private List<Apartment> apartments = null;
    private Apartment apartment;
    private LayoutInflater layoutInflater;
    Context context;


    public MyInfoWindowAdapter(List<Apartment> apartments, Context context) {
        this.apartments = apartments;
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View infoWindow = layoutInflater.inflate(R.layout.window_info, null);

        TextView addressView = (TextView) infoWindow.findViewById(R.id.address);
        TextView regionView = (TextView) infoWindow.findViewById(R.id.region);
        TextView priceView = (TextView) infoWindow.findViewById(R.id.price);
        TextView ratingView = (TextView) infoWindow.findViewById(R.id.rating);
        TextView more = (TextView) infoWindow.findViewById(R.id.more);


        int id = Integer.valueOf(marker.getTitle());
        apartment = apartments.get(id);

        addressView.setText("Address: " + apartment.getLocality());
//        regionView.setText("Region: " + region);
        priceView.setText("Price: " + apartment.getPrice());
        ratingView.setText("Rating: " + apartment.getRating());

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "see more clicked", Toast.LENGTH_LONG).show();

                SharedPreferences sp = context.getSharedPreferences("apartment", 0);
                SharedPreferences.Editor editor = sp.edit();
                Gson gson = new Gson();
                String json = gson.toJson(apartment);
                editor.putString("apartment", json);
                editor.commit();

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
        });

        return infoWindow;
    }
}

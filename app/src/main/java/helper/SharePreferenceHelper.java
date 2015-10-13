package helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import beans.Apartment;

/**
 * Created by Guoqiao on 10/12/15.
 */
public class SharePreferenceHelper {
    Context context;
    Gson gson;

    public SharePreferenceHelper(Context context){
        this.context = context;
        gson = new Gson();
    }

    public void storeApt(Apartment apartment){
        SharedPreferences sp = context.getSharedPreferences("apartment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        String json = gson.toJson(apartment);
        editor.putString("apartment", json);
        editor.commit();
    }

    public Apartment fetchApt(){
        SharedPreferences sp = context.getSharedPreferences("apartment", Context.MODE_PRIVATE);
        String json = sp.getString("apartment","");
        return gson.fromJson(json, Apartment.class);
    }
}

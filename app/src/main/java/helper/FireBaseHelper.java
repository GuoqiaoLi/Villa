package helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.guoqiao.villa.LoginActivity;
import com.example.guoqiao.villa.R;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

import beans.User;

/**
 * Created by Guoqiao on 10/24/15.
 */
public class FireBaseHelper {
    private Firebase ref;
    private Context context;

    public FireBaseHelper(Context context){
        ref = new Firebase("https://villabyluohan.firebaseio.com");
        this.context = context;
    }

    /* User implement */
    public void createUser(User user){
        Log.e("MSG", "Try to create user...");

        Firebase.ValueResultHandler handler = new Firebase.ValueResultHandler<Map<String,Object>>() {

            @Override
            public void onSuccess(Map<String, Object> result) {
                Log.e("MSG", "Created!");
                // log user in

                // return to login activity
                Intent intent = new Intent(context, LoginActivity.class);
                ((Activity) context).startActivity(intent);
            }

            @Override
            public void onError(FirebaseError firebaseError) {
                Log.e("MSG", firebaseError.getMessage());

                // set error message


                ((Activity) context).findViewById(R.id.progressBar).setVisibility(View.GONE);
            }
        };

        ref.createUser(user.getEmail(), user.getPassword(), handler);
    }

    public void resetPassword(){

    }

    public void forgetPassword(){

    }


    /* Apartment implement */
    public static void addApartment(){

    }


}

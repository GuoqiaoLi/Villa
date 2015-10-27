package tasks;

import android.util.Log;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import beans.User;

/**
 * Created by Guoqiao on 10/25/15.
 */
public class userTasks {
    private Firebase ref;
    private String errMsg;

    public userTasks(){
        ref = new Firebase("https://villabyluohan.firebaseio.com/");
    }


    public String addUserTask(final User user){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Firebase userRef = ref.child("users");
                userRef.push().setValue(user, new Firebase.CompletionListener() {
                    @Override
                    public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                        if(firebaseError == null){
                            Log.e("INFO", "save success");
                            errMsg = "Success";
                        }
                        else{
                            Log.e("INFO", "save failed");
                            errMsg = firebaseError.toString();
                        }
                    }
                });

                while(errMsg == null){
                }
                Log.e("INFO", errMsg);
                return errMsg;
            }
        });
        executor.shutdown();

        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
}

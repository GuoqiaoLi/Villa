package controller;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import beans.RequestPackage;

/**
 * Created by Guoqiao on 10/12/15.
 */
public class DBController {

    public String Get(RequestPackage pkg){
        BufferedReader bufferedReader = null;
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String uri = pkg.getUri() + pkg.encodedParam();

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while( (line = bufferedReader.readLine()) != null){
                stringBuilder.append(line+"\n");
            }

            return stringBuilder.toString();

        } catch (MalformedURLException e) {
            Log.e("url error", e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("connection error", e.toString());
            e.printStackTrace();
        } finally {
            if(bufferedReader != null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.e("Buffer close error", e.toString());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public String Post(RequestPackage requestPackage){
        BufferedReader bufferedReader = null;
        String uri = requestPackage.getUri();
        String line;

        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            //prepare parameters
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(requestPackage.encodedParam());
            writer.flush();

            StringBuilder stringBuilder = new StringBuilder();
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }catch (Exception e){
            Log.e("http error", e.getMessage());
        }finally {  //finally
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

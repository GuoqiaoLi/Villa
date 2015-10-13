package beans;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Guoqiao on 10/12/15.
 */
public class RequestPackage {
    private String uri;
    private String method;
    private HashMap<String, String> data;

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String encodedParam(){
        if (data.size() == 0) return "";

        StringBuilder builder = new StringBuilder();
        builder.append("?");
        for(String key: data.keySet()){
            try {
                String value = URLEncoder.encode(data.get(key),"UTF-8");
                builder.append(key + "=" + value + "&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        builder.deleteCharAt(builder.length()-1);
        Log.e("Get Params", builder.toString());
        return builder.toString();
    }




}

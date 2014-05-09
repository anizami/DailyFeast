package com.macalester.DailyFeast;

/**
 * Created by Asra Nizami on 3/18/14.
 */

import java.io.InputStream;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import android.util.Log;

public class ServerConnector {
    /*
    This class is responsible for making all the http requests for the Android client
     */

    public JSONArray makeHttpRequest(String url, String method,
                                     List<NameValuePair> params) {
        String json = null;
        JSONArray jArray = null;
        InputStream inputStream = null;
        // Making HTTP request
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            Object httpObject;
            // check for request method
            if(method == "POST"){
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));
                httpObject = httpPost;
            }else { // request method is GET
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);
                httpObject = httpGet;
            }
            inputStream = httpClient.execute((HttpUriRequest) httpObject).getEntity().getContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            json = IOUtils.toString(inputStream);
            if (method=="POST"){
                json = "[" + json + "]";
            }
            jArray = new JSONArray(json);

        } catch (Exception e) {
            Log.e("Error", e.toString());
        }
        return jArray;
    }
}
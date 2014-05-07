package com.macalester.DailyFeast;

/**
 * Created by Asra Nizami on 3/18/14.
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.commons.io.IOUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import android.util.Log;

public class ServerConnector {

    static InputStream inputStream = null;
    static JSONArray jArray = null;
    static String json = "";

    // constructor
    public ServerConnector() {

    }

    // function get json from url
    // by making HTTP POST or GET method
    public JSONArray makeHttpRequest(String url, String method,
                                     List<NameValuePair> params) {

        // Making HTTP request
        try {

            // check for request method
            if(method == "POST"){
                // request method is POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params));

                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            }else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                String paramString = URLEncodedUtils.format(params, "utf-8");
                url += "?" + paramString;
                HttpGet httpGet = new HttpGet(url);

                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            json = IOUtils.toString(inputStream);
            if (method=="POST"){
                json = "[" + json + "]";
            }
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        // try to parse the string to a JSON object
        try {
            Log.e("JSON PARSER", json);
            jArray = new JSONArray(json);
        } catch (Exception e) {
            Log.e("JSON Parser", "Error parsing date" + e.getCause());
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // return JSON String
        return jArray;

    }
}
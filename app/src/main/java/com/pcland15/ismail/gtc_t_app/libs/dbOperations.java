package com.pcland15.ismail.gtc_t_app.libs;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

//import org.apache.http.entity.mime.content.ByteArrayBody;

/**
 * Created by empcl_000 on 19/10/2015.
 */
public class dbOperations {


    public String table = "";
    public String type = "";
    public String where = "";

    public static HashMap<String, String> userData;
    public HashMap<String, String> addData;
    public HashMap<String, String> addImageData;

    public dbOperations(String table, String type) {
        this.table = table;
        this.type = type;
        this.addData = new HashMap<>();
        this.addImageData = new HashMap<>();
    }

    public HashMap<String, HashMap<String, String>> commit() {
        return getFromserver();
    }

    MultipartEntity geturldata() {
        MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        try {
            entity.addPart("set", new StringBody(this.type));
            entity.addPart("where", new StringBody(this.where));
            entity.addPart("table", new StringBody(this.table));
            if (this.addData != null) {
                for (String k : this.addData.keySet()) {
                    entity.addPart(k, new StringBody(this.addData.get(k)));

                }

            }


            if (this.addImageData != null) {
                for (String k : this.addImageData.keySet()) {
                    File f = new File(this.addImageData.get(k));
                    if ( f.exists()) {
                        entity.addPart(k, new FileBody(f));
                    }
                }
            }

        } catch (UnsupportedEncodingException e) {
        } catch (Exception e) {

        }
        return entity;
    }


    public HashMap<String, HashMap<String, String>> getFromserver() {

        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(config.webServiecURL);
        try {
            httppost.setEntity(geturldata());

            HttpResponse response = httpClient.execute(httppost);

            StatusLine statusLine = response.getStatusLine();

            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (ClientProtocolException e) {
            Log.d("ClientProtocolException", e.getMessage());
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        String c = stringBuilder.toString();

        return this.jsonStringToArray(c);
    }


    HashMap<String, HashMap<String, String>> jsonStringToArray(String jsonString) {
        //  ArrayList<String> stringArray = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<String, String>();
        HashMap<String, HashMap<String, String>> r = new HashMap<String, HashMap<String, String>>();
        try {
            JSONObject c = new JSONObject(jsonString);


            r.put("log", jsoneTomap(c));


            if (c.has("row")) {
                r.put("0", jsoneTomap(c.getJSONObject("row")));
            }
            if (c.has("data")) {
                JSONArray data = c.getJSONArray("data");
                for (int i = 0; i < data.length(); i++) {
                    r.put(i + "", jsoneTomap(data.getJSONObject(i)));
                }
            }

        } catch (JSONException e) {

        }




        return r;
    }






    HashMap<String, String> jsoneTomap(JSONObject thisdata) {
        HashMap<String, String> mapdata = new HashMap<String, String>();

        Iterator<String> ir = thisdata.keys();

        try {
            while (ir.hasNext()) {
                String currentKey = ir.next();
                mapdata.put(currentKey, thisdata.getString(currentKey));
            }

        } catch (JSONException e) {


        }


        return mapdata;

    }

    HashMap<String, String> jsonStringToArrayM(String jsonString) {
        ArrayList<String> stringArray = new ArrayList<String>();
        HashMap<String, String> map = new HashMap<String, String>();

        try {

            JSONObject c = new JSONObject(jsonString);


            Iterator<String> iter = c.keys();
            while (iter.hasNext()) {
                String currentKey = iter.next();
                map.put(currentKey, c.getString(currentKey));
            }


        } catch (JSONException e) {

            String aaaaaa = e.getMessage();
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        return map;
    }





    public double getDistanceInfo(double lat1, double lng1, double lat2, double lng2) {
        StringBuilder stringBuilder = new StringBuilder();
        Double dist = 0.0;
        try {

            String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + lat1 + "," + lng1 + "&destination="  + lat2 + "," + lng2 +  "&mode=driving&sensor=false";



            HttpPost httppost = new HttpPost(url);

            HttpClient client = new DefaultHttpClient();
            HttpResponse response;
            stringBuilder = new StringBuilder();


            response = client.execute(httppost);
            HttpEntity entity = response.getEntity();
            InputStream stream = entity.getContent();
            int b;
            while ((b = stream.read()) != -1) {
                stringBuilder.append((char) b);
            }
        } catch (ClientProtocolException e) {
        } catch (IOException e) {
        }

        JSONObject jsonObject = new JSONObject();
        try {

            jsonObject = new JSONObject(stringBuilder.toString());

            JSONArray array = jsonObject.getJSONArray("routes");

            JSONObject routes = array.getJSONObject(0);

            JSONArray legs = routes.getJSONArray("legs");

            JSONObject steps = legs.getJSONObject(0);

            JSONObject distance = steps.getJSONObject("distance");

            Log.i("Distance", distance.toString());
            dist = Double.parseDouble(distance.getString("text").replaceAll("[^\\.0123456789]","") );

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dist;
    }

}



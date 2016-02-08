package com.pcland15.ismail.gtc_t_app;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.pcland15.ismail.gtc_t_app.libs.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class items_categories extends AppCompatActivity {
    String myID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_categories);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        myID = getIntent().getStringExtra("id");
        getData();
    }


    ItemslistArrayAdapte adapter;


    public void goBack(View view) {
        Intent t = new Intent(this, home.class);
        startActivity(t);
    }

    public void geHome(View view) {

        Intent t = new Intent(this, home.class);
        startActivity(t);
    }

    public void goOut(View view) {
        Intent t = new Intent(this, splesh.class);
        startActivity(t);
    }



    void getData() {

        dbOperations dbi = new dbOperations(xmlDataModel.itemsCatTable, "get_data");
        dbi.where = "id=" + this.myID;
        HashMap<String, HashMap<String, String>> allData = dbi.commit();
        HashMap<String, String> datai = allData.get("0");
        TextView t = (TextView) findViewById(R.id.ic_title);
        t.setText(datai.get("title"));


        dbOperations db = new dbOperations(xmlDataModel.itemsTables, "get_data");


        db.where = "`group`='" + this.myID.trim()+"'";

        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

 Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();


        HashMap<String, HashMap<String, String>> data = db.commit();
        if (data.size() > 0) {
            final List<cat_list> mydata = new ArrayList<>();
            for (String k : data.keySet()) {
                if (!k.equalsIgnoreCase("log")) {
                    cat_list c = new cat_list();
                    c.setTitle(data.get(k).get("title"));
                    c.setImage(data.get(k).get("image"));
                    c.setID(data.get(k).get("id"));


                    String p =data.get(k).get("positions");
                    String[] ps = p.split(",");


                   double dis=    db.getDistanceInfo(latitude,longitude,Double.parseDouble(ps[0]), Double.parseDouble(ps[1]));

                    c.setDes("Distance : "+dis+"km");


                    mydata.add(c);
                }
            }


            adapter = new ItemslistArrayAdapte(this, 0, mydata);
            ListView l = (ListView) findViewById(R.id.ic_list);


            l.setAdapter(adapter);


            l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    viewItemsList(mydata.get(position).getID());
                }
            });

        }
    }

    void viewItemsList(String id) {


        Intent t = null;


        t = new Intent(this, new_res_item.class);


        t.putExtra("id", id);

        startActivity(t);
    }

}

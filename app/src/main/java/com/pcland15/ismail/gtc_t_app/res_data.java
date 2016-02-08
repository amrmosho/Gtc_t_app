package com.pcland15.ismail.gtc_t_app;

import android.content.Intent;
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

public class res_data extends AppCompatActivity {
    String myID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_data);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        myID = dbOperations.userData.get("id");
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

        TextView t = (TextView) findViewById(R.id.mc_title);
        t.setText(dbOperations.userData.get("title"));


        dbOperations db = new dbOperations(xmlDataModel.resTable, "get_data");

        db.where = "user_id='" + this.myID.trim()+"'";






        HashMap<String, HashMap<String, String>> data = db.commit();
        if (data.size() > 0) {
            final List<cat_list> mydata = new ArrayList<>();
            for (String k : data.keySet()) {
                if (!k.equalsIgnoreCase("log")) {



                    dbOperations dbi = new dbOperations(xmlDataModel.itemsTables, "get_data");
                    dbi.where = "id='" +data.get(k).get("item_id")+"'";
                    HashMap<String, HashMap<String, String>> allData = dbi.commit();
                    HashMap<String, String> datai = allData.get("0");





                    cat_list c = new cat_list();
                    c.setTitle(datai.get("title"));
                    c.setImage(datai.get("image"));


                    c.setID(data.get(k).get("id"));


                    String p =datai.get("positions");
                    String[] ps = p.split(",");

                    c.setDes("Date :" + data.get(k).get("arrv_time"));

                    mydata.add(c);
                }
            }


            adapter = new ItemslistArrayAdapte(this, 0, mydata);
            ListView l = (ListView) findViewById(R.id.mc_list);


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


        t = new Intent(this, res_item.class);


        t.putExtra("id", id);

        startActivity(t);
    }


}

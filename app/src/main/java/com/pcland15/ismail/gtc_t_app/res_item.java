package com.pcland15.ismail.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcland15.ismail.gtc_t_app.libs.*;


import java.util.HashMap;

public class res_item extends AppCompatActivity {
    String myID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_res_item);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        myID = getIntent().getStringExtra("id");


        updateData();
    }


    public void goBack(View view) {
        Intent t = new Intent(this, res_data.class);
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
    HashMap<String, String> datares;
    String itemID = "";





    void updateData() {

        dbOperations dbres = new dbOperations(xmlDataModel.resTable, "get_data");
        dbres.where = "id=" + this.myID;
        HashMap<String, HashMap<String, String>> allDatares = dbres.commit();
        datares = allDatares.get("0");

        itemID = datares.get("item_id");
        dbOperations dbi = new dbOperations(xmlDataModel.itemsTables, "get_data");
        dbi.where = "id='" + itemID + "'";
        HashMap<String, HashMap<String, String>> allData = dbi.commit();
        HashMap<String, String> datai = allData.get("0");

        TextView t = (TextView) findViewById(R.id.v_ri_item_title);
        t.setText(datai.get("title"));
        ImageView i = (ImageView) findViewById(R.id.v_ri_item_image);
        ui.loadImage(this, i, datai.get("image"));

        gettxt(R.id.v_ri_name,R.string.name + " : title");
        gettxt(R.id.v_ri_phone, R.string.phone + " : phonenumber");
        gettxt(R.id.v_ri_numod_room,R.string.numroom + " : plc_number");
        gettxt(R.id.v_ri_numod_person,R.string.num_per+ " : gst_umber");
        gettxt(R.id.v_ri_arr_time, R.string.arr_time + " : arrv_time");
        gettxt(R.id.v_ri_lev_time, R.string.lev_time+ " : leave_time");


        chtxt(R.id.v_ri_childern, "childern");
        chtxt(R.id.v_ri_teen, "teen");

    }

    void gettxt(int id, String valuename) {

        try {
            TextView a = (TextView) findViewById(id);
            a.setText(datares.get(valuename));


        } catch (Exception e) {

        }
    }



    void chtxt(int id, String valuename) {

        try {
            CheckBox a = (CheckBox) findViewById(id);

            if (datares.get(valuename).trim().equalsIgnoreCase("1")) {


                a.setChecked(true);
            }

        } catch (Exception e) {

        }
    }

    public void gotoItem(View view) {

        Intent t = new Intent(this, item_data.class);

        t.putExtra("id", itemID);


        startActivity(t);


    }
}

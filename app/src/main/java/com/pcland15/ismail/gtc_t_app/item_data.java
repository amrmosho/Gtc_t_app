package com.pcland15.ismail.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcland15.ismail.gtc_t_app.libs.*;

import java.util.HashMap;

public class item_data extends AppCompatActivity {




    String myID="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        myID = getIntent().getStringExtra("id");
        updateData();
    }


    void updateData(){

        dbOperations dbi = new dbOperations(xmlDataModel.itemsTables, "get_data");
        dbi.where = "id=" + this.myID;
        HashMap<String, HashMap<String, String>> allData = dbi.commit();
        HashMap<String, String> datai = allData.get("0");
        TextView t = (TextView) findViewById(R.id.i_item_title);

        title=datai.get("title");

        t.setText(datai.get("title"));
        ImageView i = (ImageView) findViewById(R.id.i_item_image);
        ui.loadImage(this, i, datai.get("image"));

        TextView i_des = (TextView) findViewById(R.id.i_des);
        i_des.setText(datai.get("des"));

        p=datai.get("positions");


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




    String p ="";
    String title="";
    public void gotomap(View view) {


        Intent t = new Intent(this, item_map.class);
        t.putExtra("title",title);
        t.putExtra("positon",p);
        startActivity(t);


    }
}

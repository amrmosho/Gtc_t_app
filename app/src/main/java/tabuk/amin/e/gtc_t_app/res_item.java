package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.pcland15.ismail.gtc_t_app.R;


import java.util.HashMap;

import tabuk.amin.e.gtc_t_app.libs.dbOperations;
import tabuk.amin.e.gtc_t_app.libs.ui;
import tabuk.amin.e.gtc_t_app.libs.xmlDataModel;

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







        updayettxt(R.id.v_ri_name, "Name  : ", "title");
        updayettxt(R.id.v_ri_phone,  "Phone : ", "phonenumber");
        updayettxt(R.id.v_ri_numod_room,   "#Room : ", "plc_number");
        updayettxt(R.id.v_ri_numod_person,  "#person : ", "gst_umber");
        updayettxt(R.id.v_ri_arr_time, "Arrive Time : ", "arrv_time");
        updayettxt(R.id.v_ri_lev_time, "Leave Time : ", "leave_time");


        chtxt(R.id.v_ri_childern, "childern");
        chtxt(R.id.v_ri_teen, "teen");

    }

    void updayettxt(int id,String mylable, String valuename) {

        try {
            TextView a = (TextView) findViewById(id);
            a.setText(mylable+datares.get(valuename));


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

package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pcland15.ismail.gtc_t_app.R;

import java.util.ArrayList;
import java.util.HashMap;

import tabuk.amin.e.gtc_t_app.libs.dbOperations;
import tabuk.amin.e.gtc_t_app.libs.ui;
import tabuk.amin.e.gtc_t_app.libs.xmlDataModel;

public class new_res_item extends AppCompatActivity {
    String myID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_res_item);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        myID = getIntent().getStringExtra("id");


        settext(R.id.ri_name ,  dbOperations.userData.get("title"));


        settext(R.id.ri_phone ,  dbOperations.userData.get("phone"));


        updateData();
    }


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


    void updateData() {


        dbOperations dbi = new dbOperations(xmlDataModel.itemsTables, "get_data");
        dbi.where = "id=" + this.myID;


        HashMap<String, HashMap<String, String>> allData = dbi.commit();

        HashMap<String, String> datai = allData.get("0");

        TextView t = (TextView) findViewById(R.id.ri_item_title);
        t.setText(datai.get("title"));
        ImageView i = (ImageView) findViewById(R.id.ri_item_image);
        ui.loadImage(this, i, datai.get("image"));






        hide(R.id.ri_name);
        hide(R.id.ri_phone);
        hide(R.id.ri_numod_room);
        hide(R.id.ri_numod_person);
        hide(R.id.ri_arr_time);
        hide(R.id.ri_lev_time);
        chhide(R.id.ri_childern);
        chhide(R.id.ri_teen);
        hidebt(R.id.new_res_bt);

        switch (datai.get("group")){
            //Hotels
            case "1":
            case "2":

                showbt(R.id.new_res_bt);
                show(R.id.ri_name);
                show(R.id.ri_phone);
                show(R.id.ri_numod_room);
                show(R.id.ri_numod_person);
                showch(R.id.ri_childern);
                showch(R.id.ri_teen);
                show(R.id.ri_arr_time);
                show(R.id.ri_lev_time);
                break;

            case "3":
                break;
            default:
                break;




        }

    }
    void settext(int id,String value) {

        EditText a = (EditText) findViewById(id);
         a.setText(value);

    }



    String gettxt(int id) {

        EditText a = (EditText) findViewById(id);
        return a.getText().toString();

    }
    void hide(int id) {

        EditText a = (EditText) findViewById(id);
       a.setVisibility(View.INVISIBLE);

    }

    void showch(int id) {

        CheckBox a = (CheckBox) findViewById(id);
        a.setVisibility(View.VISIBLE);

    }



    void hidebt(int id) {

        Button a = (Button) findViewById(id);
        a.setVisibility(View.INVISIBLE);

    }

    void showbt(int id) {

        Button a = (Button) findViewById(id);
        a.setVisibility(View.VISIBLE);

    }




    void show(int id) {

        EditText a = (EditText) findViewById(id);
        a.setVisibility(View.VISIBLE);

    }
    void chhide(int id) {

        CheckBox a = (CheckBox) findViewById(id);
        a.setVisibility(View.INVISIBLE);

    }

    String chtxt(int id) {
        String r = "0";
        CheckBox a = (CheckBox) findViewById(id);

        if (a.isChecked()) {

            r = "1";
        }
        return r;

    }



    void clearcData(ArrayList<Integer> data){
        try {
            for( int id :data) {
                TextView a = (TextView) findViewById(id);
                a.setText("");

            }
        } catch (Exception e) {

        }


    }

    public void naw_ResSend(View view) {

        dbOperations db = new dbOperations(xmlDataModel.resTable, "insert");


        db.addData.put("item_id", myID);
        db.addData.put("user_id", dbOperations.userData.get("id"));


        db.addData.put("title", gettxt(R.id.ri_name));
        db.addData.put("phonenumber", gettxt(R.id.ri_phone));



        db.addData.put("plc_number", gettxt(R.id.ri_numod_room));
        db.addData.put("gst_umber", gettxt(R.id.ri_numod_person));


        db.addData.put("childern", chtxt(R.id.ri_childern));
        db.addData.put("teen", chtxt(R.id.ri_teen));


        db.addData.put("arrv_time", gettxt(R.id.ri_arr_time));
        db.addData.put("leave_time", gettxt(R.id.ri_lev_time));
        ArrayList<Integer> a=new ArrayList<>() ;




        HashMap<String, HashMap<String, String>> userdata = db.commit();

        if (userdata.size() > 1) {
            Toast.makeText(this, this.getString(R.string.msg_succ), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.getString(R.string.msg_succ), Toast.LENGTH_SHORT).show();
        }

        a.add(R.id.ri_name);
        a.add(R.id.ri_phone);
        a.add(R.id.ri_numod_room);
        a.add(R.id.ri_numod_person);
        a.add(R.id.ri_arr_time);
        a.add(R.id.ri_lev_time);

        clearcData(a);

    }


    public void gotoItem(View view) {

        Intent t = new Intent(this, item_data.class);

        t.putExtra("id", myID);


        startActivity(t);


    }
}

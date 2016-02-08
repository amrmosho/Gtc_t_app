package com.pcland15.ismail.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.pcland15.ismail.gtc_t_app.libs.*;

import java.util.HashMap;

public class users_add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_add);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }




    }

    public void goBack(View view) {
        Intent t = new Intent(this, long.class);
        startActivity(t);
    }



    public void goOut(View view) {
        Intent t = new Intent(this, splesh.class);
        startActivity(t);
    }



   String  gettxt(int id){
       EditText a= (EditText) findViewById(id);
       return a.getText().toString();

   }



    public void naw_userRegSend(View view) {


        dbOperations db = new dbOperations(xmlDataModel.userTable, "insert");





        db.addData.put("new_uesr_name", gettxt(R.id.new_uesr_name));
        db.addData.put("new_uesr_email", gettxt(R.id.new_uesr_email));
        db.addData.put("new_uesr_password", gettxt(R.id.new_uesr_password));
        db.addData.put("new_uesr_phone", gettxt(R.id.new_uesr_phone));
        db.addData.put("new_uesr_address", gettxt(R.id.new_uesr_address));





        HashMap<String, HashMap<String, String>> userdata = db.commit();

        if (userdata.size() > 1) {
            Toast.makeText(this, this.getString(R.string.msg_succ), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.getString(R.string.msg_errorlogin), Toast.LENGTH_SHORT).show();
        }



    }
}

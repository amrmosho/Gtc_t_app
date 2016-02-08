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

public class login extends AppCompatActivity {

    EditText email;
    EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }



        email = (EditText) findViewById(R.id.login_email);
        password = (EditText) findViewById(R.id.login_password);


        email.setText("admin");
        password.setText("admin");

    }



    public void login(View view) {
        dbOperations db = new dbOperations(xmlDataModel.userTable, "login");

        db.addData.put("email", email.getText().toString());
        db.addData.put("password", password.getText().toString());

        HashMap<String, HashMap<String, String>> userdata = db.commit();

        if (userdata.size() > 1) {
            dbOperations.userData = userdata.get("0");
            Intent t = new Intent(this, home.class);
            startActivity(t);

        } else {

            Toast.makeText(this, this.getString(R.string.msg_errorlogin), Toast.LENGTH_SHORT).show();

        }


    }


    public void togoNewUser(View view) {
        Intent t = new Intent(this, users_add.class);
        startActivity(t);
    }

    public void gotoAboutUS(View view) {

        Intent t = new Intent(this, about.class);
        startActivity(t);
    }
}

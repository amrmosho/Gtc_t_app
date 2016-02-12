package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pcland15.ismail.gtc_t_app.R;

import java.util.ArrayList;
import java.util.HashMap;

import tabuk.amin.e.gtc_t_app.libs.dbOperations;
import tabuk.amin.e.gtc_t_app.libs.xmlDataModel;

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


    void clearcData(ArrayList<Integer> data){
        try {
            for( int id :data) {
                TextView a = (TextView) findViewById(id);
                a.setText("");

            }
        } catch (Exception e) {

        }


    }

    public void naw_userRegSend(View view) {


        dbOperations db = new dbOperations(xmlDataModel.userTable, "insert");



        String  name =gettxt(R.id.new_uesr_name).trim();
        String  email =gettxt(R.id.new_uesr_email).trim();
        String  passwored =gettxt(R.id.new_uesr_password).trim();

if (email.equalsIgnoreCase("")||name.equalsIgnoreCase("")||passwored.equalsIgnoreCase("")){

    Toast.makeText(this,"inputs Empty", Toast.LENGTH_SHORT).show();


}else{

        db.addData.put("title", name);
        db.addData.put("username",name);
        db.addData.put("email", email);
        db.addData.put("password", passwored);


        db.addData.put("phone", gettxt(R.id.new_uesr_phone));
        db.addData.put("adress", gettxt(R.id.new_uesr_address));






        HashMap<String, HashMap<String, String>> userdata = db.commit();




        if (userdata.size() > 1) {
            Toast.makeText(this, this.getString(R.string.msg_succ), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.getString(R.string.msg_succ), Toast.LENGTH_SHORT).show();
        }


        ArrayList<Integer> a=new ArrayList<>() ;
        a.add(R.id.new_uesr_name);
        a.add(R.id.new_uesr_email);
        a.add(R.id.new_uesr_password);
        a.add(R.id.new_uesr_phone);
        a.add(R.id.new_uesr_address);

        clearcData(a);
}

    }
}

package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pcland15.ismail.gtc_t_app.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import tabuk.amin.e.gtc_t_app.libs.cat_list;
import tabuk.amin.e.gtc_t_app.libs.dbOperations;
import tabuk.amin.e.gtc_t_app.libs.xmlDataModel;

public class home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        getData();

    }

    public void goBack(View view) {
        Intent t = new Intent(this, splesh.class);
        startActivity(t);
    }


    public void goOut(View view) {
        Intent t = new Intent(this, splesh.class);
        startActivity(t);
    }





    public static void printMap(HashMap<String, String> map) {
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey()
                    + " Value : " + entry.getValue());
        }
    }






    home_ItemslistArrayAdapte adapter;

    void getData() {



        dbOperations db = new dbOperations(xmlDataModel.itemsCatTable, "get_data");

        db.where = " 1=1 order by `order`";

        HashMap<String, HashMap<String, String>> data = db.commit();






        ArrayList<String> list = new ArrayList<String>(data.keySet());


        Collections.sort(list);




        if (list.size() > 0) {
            final List<cat_list> mydata = new ArrayList<>();


            for (String k : list) {
                if (!k.equalsIgnoreCase("log")) {


                    cat_list c = new cat_list();



                    c.setTitle(data.get(k).get("title"));
                    c.setImage(data.get(k).get("image"));
                    c.setID(data.get(k).get("id"));




                    mydata.add(c);



                }
            }





            adapter = new home_ItemslistArrayAdapte(this, 0, mydata);


            ListView l = (ListView) findViewById(R.id.heom_cats_List);


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

        if (id.equalsIgnoreCase("1000")) {


            t = new Intent(this, res_data.class);
        } else {


            t = new Intent(this, items_categories.class);
        }


        t.putExtra("id", id);


        startActivity(t);
    }

    public void gotoabout(View view) {
        Intent t = null;
        t = new Intent(this, about.class);

        startActivity(t);


    }    public void gotoAboutUS(View view) {

        Intent t = new Intent(this, about.class);
        startActivity(t);
    }
}

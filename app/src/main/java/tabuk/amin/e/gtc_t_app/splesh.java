package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pcland15.ismail.gtc_t_app.R;

public class splesh extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splesh);
    }





    public void gologin(View view) {

        Intent t = new Intent(this, login.class);
        startActivity(t);




    }





}

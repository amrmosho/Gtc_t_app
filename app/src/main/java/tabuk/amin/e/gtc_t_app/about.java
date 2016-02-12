package tabuk.amin.e.gtc_t_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pcland15.ismail.gtc_t_app.R;

public class about extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }



    public void geHome(View view) {

        Intent t = new Intent(this, home.class);
        startActivity(t);
    }

    public void goOut(View view) {
        Intent t = new Intent(this, splesh.class);
        startActivity(t);
    }
    public void goBack(View view) {
        onBackPressed();
    }

}

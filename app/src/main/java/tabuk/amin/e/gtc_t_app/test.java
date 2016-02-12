package tabuk.amin.e.gtc_t_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.pcland15.ismail.gtc_t_app.R;

public class test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_test);


        EditText t =(EditText) findViewById(R.id.login_email);
t.getText();
    }
}

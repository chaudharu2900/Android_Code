package home.example.com.sangham_content;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner mSpinner,nSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSpinner=(Spinner)findViewById(R.id.spinner1);
        nSpinner=(Spinner)findViewById(R.id.spinner2);

        ArrayList<String> options=new ArrayList<String>();

        options.add("--Category--");
        options.add("option 1");
        options.add("option 2");
        options.add("option 3");
        options.add("option 4");


// use default spinner item to show options in spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options);
        mSpinner.setAdapter(adapter);

        ArrayList<String> options1=new ArrayList<String>();

        options1.add("--Sub_Category--");
        options1.add("option 1");
        options1.add("option 2");
        options1.add("option 3");
        options1.add("option 4");

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,options1);
        nSpinner.setAdapter(adapter1);



    }
}

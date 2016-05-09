package home.example.com.rss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ayush on 29/2/16.
 */
public class ClickButton extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.click_button);
        TextView textView=(TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();

     //   String id = intent.getStringExtra("tips");
        String name = intent.getStringExtra("contentsss");


textView.setText(name);
    }

}
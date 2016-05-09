package home.example.com.sangham_content;

/**
 * Created by ayush on 4/5/16.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyActivity extends Activity implements OnItemSelectedListener{

    String Category,subcategory,comment;
    Spinner spinner,spinner1;
    int nobutton;
    Button btn;
    EditText editText;
    String name;
    JSONArray project, content;
    private String JSON_URL = "http://www.trinityapplab.in/RSS/submenu.php?menu=Spiritual";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
      //  JSON_URL = "http://www.trinityapplab.in/RSS/submenu.php";


        new LongOperation().execute(JSON_URL);

        // Spinner element
       spinner = (Spinner) findViewById(R.id.spinner);
         spinner1 = (Spinner) findViewById(R.id.spinner1);
      btn=(Button)findViewById(R.id.button_123);
        editText=(EditText)findViewById(R.id.edit_text);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        spinner1.setOnItemSelectedListener(this);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Category = spinner.getSelectedItem().toString();
                subcategory = spinner1.getSelectedItem().toString();
                comment = editText.getText().toString();


                new SendtoServer().execute();


                editText.setText(null);

            }
        });


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
for(int i=0;i<nobutton;i++) {




      //  jsoncategoty = project.toString();
      //  JSONArray newJArray = new JSONArray(jsoncategoty);
        categories.add(project.toString());

    }



//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");

        List<String> categories1 = new ArrayList<String>();
        categories1.add("Computers");
        categories1.add("Mobile Phones");
        categories1.add("key Board");
        categories1.add("mouse");
        categories1.add("Dslr Cemera");
        categories1.add("Digital Camera");
        // Creating adapter for spinner

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories1);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        spinner1.setAdapter(dataAdapter1);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
      //  String item1 = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
       // Toast.makeText(parent.getContext(), "Selected: " + item1, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
    private class LongOperation extends AsyncTask<String, Void, Void> {

        private final HttpClient Client = new DefaultHttpClient();
        private String Content;

        private String Error = null;
        private ProgressDialog Dialog = new ProgressDialog(MyActivity.this);


        protected void onPreExecute() {

            Dialog.setMessage("Please wait...");
            Dialog.show();
        }

        // Call after onPreExecute method
        protected Void doInBackground(String... urls) {
            try {


                HttpGet httpget = new HttpGet(urls[0]);
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                Content = Client.execute(httpget, responseHandler);
                try {


                    JSONObject jsnobject = new JSONObject(Content);
                    project = jsnobject.getJSONArray("SubMenu");
                  //  project = jsnobject.getJSONArray("Menu");
                    content = jsnobject.getJSONArray("content");
                    nobutton = project.length();


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (ClientProtocolException e) {
                Error = e.getMessage();
                cancel(true);
            } catch (IOException e) {
                e.printStackTrace();
                Error = e.getMessage();
                cancel(true);
            }

            return null;
        }

        protected void onPostExecute(Void unused) {


            Dialog.dismiss();

            if (Error != null) {


            } else {

                try {
                    JSONObject obj = project.getJSONObject(0);
                    name = obj.getString("label");

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }


    }

    class SendtoServer extends AsyncTask<String, Void, String> {


        ProgressDialog dlg;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dlg = new ProgressDialog(MyActivity.this);
            dlg.setMessage("Saving...");
            dlg.show();
        }


        @Override
        protected String doInBackground(String... params) {


            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
            nameValuePairList.add(new BasicNameValuePair("F5",Category));
            nameValuePairList.add(new BasicNameValuePair("F6", subcategory));
            nameValuePairList.add(new BasicNameValuePair("F7", comment));







            String result = new ServiceHandler().makeServiceCall("http://trinityapplab.in/slump/registration.php",2, nameValuePairList);
            //   Log.d("result =", result.toString());

            return result;
        }



        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            dlg.dismiss();
        }


    }



}
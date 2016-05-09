package home.example.com.rss;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReligionFragment extends Fragment {
	  private String JSON_URL =null;
	View view3;
	int N;
Context con;
	JSONArray project1,content1,conte;
	JSONObject obj1,obj2;
	String name,tips,cont,cont2,listCont;
	static Button btn0,btn1,btn2;
static ListView showlist;
	Context context;

	ArrayList<String> array;



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_01, container, false);



		return rootView;
}

	public void onViewCreated(View v, Bundle savedInstanceState) {
		JSON_URL ="http://www.trinityapplab.in/RSS/submenu.php?menu=Hinduism";
		super.onViewCreated(v,savedInstanceState);
		view3= v;
		btn0 = (Button)view3.findViewById(R.id.hindu1);
		showlist = (ListView)view3.findViewById(R.id.list_view_sanatan_content);
		//onBackPressed();


		btn0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Show_Tip_list.x=1;
				btn0.setVisibility(View.GONE);
				showlist.setVisibility(View.VISIBLE);

				new LongOperation().execute(JSON_URL);



			}
		});

	}






	private class LongOperation  extends AsyncTask<String, Void, Void> {

		private final HttpClient Client = new DefaultHttpClient();
		private String Content;

		private String Error = null;
		private ProgressDialog Dialog = new ProgressDialog(getActivity());



		protected void onPreExecute() {
			// NOTE: You can call UI Element here.

			//UI Element
			// tvisit1.setText("Output : ");
			Dialog.setMessage("Please wait...");
			Dialog.show();
		}

		// Call after onPreExecute method
		protected Void doInBackground(String... urls) {
			try {

				// Call long running operations here (perform background computation)
				// NOTE: Don't call UI Element here.

				// Server url call by GET method
				HttpGet httpget = new HttpGet(urls[0]);
				ResponseHandler<String> responseHandler = new BasicResponseHandler();
				Content = Client.execute(httpget, responseHandler);


				try {

					array = new ArrayList<String>();


			JSONObject jsnobject = new JSONObject(Content);
			project1 = jsnobject.getJSONArray("SubMenu");
			content1 = jsnobject.getJSONArray("content");

		  	obj1 = content1.getJSONObject(0);

		  	conte = obj1.getJSONArray("Sanatan Dharma");
		 	for(int i=0;i<conte.length();i++) {
			 obj2 = conte.getJSONObject(i);

			listCont = obj2.getString("contents");


			 array.add(listCont);



		 }




				} catch (JSONException e) {
					e.printStackTrace();
				}
			} catch (ClientProtocolException e) {
				Error = e.getMessage();
				cancel(true);
			} catch (IOException e) {
				Error = e.getMessage();
				cancel(true);
			}

			return null;
		}



		protected void onPostExecute(Void unused) {


			Dialog.dismiss();

			if (Error != null) {



			}

			else {
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.adapter, array);


 		ListView tv=(ListView)view3.findViewById(R.id.list_view_sanatan_content);
//			LinearLayout linearLayout =(LinearLayout)view3.findViewById(R.id.adapter_id);
//
//
//				tv.setPadding(0,10,0,10);


				//tv.setAdapter();

				tv.setAdapter(new CustomAdapter(getActivity(), array));

			//	showlist.setAdapter(adapter);







				}

			}
		}




public static   int allshow()
{

	showlist.setVisibility(View.INVISIBLE);
	btn0.setVisibility(View.VISIBLE);
	return 1;
}


}




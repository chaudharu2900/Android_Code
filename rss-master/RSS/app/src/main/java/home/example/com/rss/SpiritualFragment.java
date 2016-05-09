package home.example.com.rss;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class SpiritualFragment extends Fragment   {


	JSONArray jsonArray;
	JSONObject jsonobject;
	JSONArray project,content;
	String name,tips,cont;
	private static String  iemi=null;  private  String JSON_URL =null;
	View view;
	int nobutton;
	//Fragment fragmentTwo;
	//FragmentManager fragmentManager = getFragmentManager();
//FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_02, container, false);

		JSON_URL ="http://trinityapplab.in/RSS/submenu.php?menu=Spiritual";
		new LongOperation().execute(JSON_URL);

		return rootView;
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
			Dialog.setMessage("Please wait Spiritual...");
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

					JSONObject jsonObj = new JSONObject(Content);



					JSONObject jsnobject = new JSONObject(Content);
					project = jsnobject.getJSONArray("SubMenu");
					content = jsnobject.getJSONArray("content");
					nobutton=project.length();





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



			} else {
				LinearLayout linear=(LinearLayout)getActivity().findViewById(R.id.linear2);

				for (int i = 0; i < nobutton; i++) {
					LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
							LinearLayout.LayoutParams.MATCH_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
					Button btn = new Button(getActivity());
					try{
						JSONObject obj=   project.getJSONObject(i);
						name	=obj.getString("label");

					}
					catch (JSONException e) {
						e.printStackTrace();
					}


					btn.setId(i);
					final int id_ = btn.getId();

					btn.setText(name);

					//btn.setId(i);
					//final int id_ = btn.getId();
					//btn.setText("button " + id_);
					params.setMargins(30, 20, 30, 0);
					//	btn.setBackgroundColor(Color.rgb(70, 80, 90));
					linear.addView(btn, params);
					btn.setBackground(getActivity().getResources().getDrawable(R.drawable.button_selector));
					btn = ((Button) getActivity().findViewById(id_));

					btn .setOnClickListener(new View.OnClickListener() {
 					public void onClick(View view) {
						try{
							JSONObject obj=		  content.getJSONObject(id_);
						//	tips	=obj.getString("tips");
							cont=obj.getString("contents");
							Intent intent=new Intent(getActivity(),ClickButton.class);
							//intent.putExtra("tips",tips);
							intent.putExtra("contentsss",cont);
							startActivity(intent);


						}
						catch (JSONException e) {
							e.printStackTrace();
						}



//							Toast.makeText(view.getContext(),
//									"Button clicked index = " + id_, Toast.LENGTH_SHORT)
//									.show();
						}
					});
				}




			}
		}

	}
}
package home.example.com.rss;


import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

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
import java.util.ArrayList;

public class HealthFragment extends Fragment {



	JSONArray project2,content2,conte2;
	String name,tips,cont;
	private String JSON_URL =null;
	View view2;
	static Button btn0,btn1,btn2,btn3;
	JSONObject obj1,obj2;
	int nobutton;
	String listContent;
	static ListView showlist;
	int flag=0;
	ArrayList<String> array;
	JSONObject book;
	JSONObject obj1json;

	JSONArray books;
	@Override

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_03, container, false);
		array = new ArrayList<String>();
		//btn1 =(Button) view2.findViewById(R.id.Gods);
		//	btn2 =(Button) view2.findViewById(R.id.Festivals);
		//btn3 =(Button) view2.findViewById(R.id.books);



		return rootView;
	}
	public void onViewCreated(View v, Bundle savedInstanceState) {
		JSON_URL ="http://www.trinityapplab.in/RSS/submenu.php?menu=Health";
		super.onViewCreated(v,savedInstanceState);
		view2= v;

		btn0 = (Button)view2.findViewById(R.id.Yoga);
		btn1 = (Button)view2.findViewById(R.id.Ayurveda);
		btn2 = (Button)view2.findViewById(R.id.Vastu);
		btn3 = (Button)view2.findViewById(R.id.Meditation);
		showlist = (ListView)view2.findViewById(R.id.list_view_Yoga);
		//	showlist1= (ListView)view2.findViewById(R.id.list_view_gods);
		//	showlist2 = (ListView)view2.findViewById(R.id.list_view_festival);
		//showlist3 = (ListView)view2.findViewById(R.id.list_view_books);


		btn0.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Show_Tip_list.x=3;
				flag=3;
				btn1.setVisibility(View.GONE);
				btn0.setVisibility(View.GONE);
				btn2.setVisibility(View.GONE);
				btn3.setVisibility(View.GONE);

				showlist.setVisibility(View.VISIBLE);


				new LongOperation().execute(JSON_URL);


			}
		});

		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Show_Tip_list.x=3;
				flag=0;
				btn1.setVisibility(View.GONE);
				btn0.setVisibility(View.GONE);
				btn2.setVisibility(View.GONE);
				btn3.setVisibility(View.GONE);
				showlist.setVisibility(View.VISIBLE);

				new LongOperation().execute(JSON_URL);





			}
		});

		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Show_Tip_list.x=3;
				flag=2;
				btn1.setVisibility(View.GONE);
				btn0.setVisibility(View.GONE);
				btn2.setVisibility(View.GONE);
				btn3.setVisibility(View.GONE);
				showlist.setVisibility(View.VISIBLE);


				new LongOperation().execute(JSON_URL);





			}
		});

		btn3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Show_Tip_list.x=3;
				flag=1;
				btn1.setVisibility(View.GONE);
				btn0.setVisibility(View.GONE);
				btn2.setVisibility(View.GONE);
				btn3.setVisibility(View.GONE);
				showlist.setVisibility(View.VISIBLE);


				new LongOperation().execute(JSON_URL);




			}
		});



		//new LongOperation().execute(JSON_URL);
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


					JSONObject jsnobject = new JSONObject(Content);
					project2 = jsnobject.getJSONArray("SubMenu");
					content2 = jsnobject.getJSONArray("content");

					if(flag==0)

					{
						obj1json=content2.getJSONObject(0);
						books=obj1json.getJSONArray("Ayurveda");





					}
					else if(flag==1)
					{
						obj1json=content2.getJSONObject(1);
						books=obj1json.getJSONArray("Meditation");


					}

					else if(flag==2)
					{
						obj1json=content2.getJSONObject(2);
						books=obj1json.getJSONArray("Vastu");


					}

					else if(flag==3)
					{
						obj1json=content2.getJSONObject(3);
						books=obj1json.getJSONArray("Yoga");


					}

					for(int i=0;i<books.length();i++) {
						JSONObject	obj2 = books.getJSONObject(i);

						listContent = obj2.getString("contents");


						array.add(listContent);



					}

				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			catch (ClientProtocolException e) {
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



				ListView tv=(ListView)view2.findViewById(R.id.list_view_Yoga);


				tv.setAdapter(new CustomAdapter(getActivity(),array));

			}




		}
	}
	public static void allshow2()
	{

		showlist.setVisibility(View.GONE);
		btn0.setVisibility(View.VISIBLE);
		btn1.setVisibility(View.VISIBLE);
		btn2.setVisibility(View.VISIBLE);
		btn3.setVisibility(View.VISIBLE);
	}

}

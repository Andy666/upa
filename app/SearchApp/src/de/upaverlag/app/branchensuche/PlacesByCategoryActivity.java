package de.upaverlag.app.branchensuche;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.upaverlag.app.R;
import android.app.ProgressDialog;
import de.upaverlag.app.connection.json.JSONManager;
import android.os.AsyncTask;
import android.os.Bundle;

import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class PlacesByCategoryActivity extends ListActivity {

	String ctg = "auto";
	// Progress Dialog
	private ProgressDialog fDialog;

	// Creating JSON Parser object
	JSONManager jParser = new JSONManager();

	ArrayList<HashMap<String, String>> firmenList;

	// url to get all products list
	private static String UrlPlacesByCategory = "http://127.0.0.1/android/app/PlacesByCategory.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PLACES = "places";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";

	private static final String TAG_PLZ = "plz";

	private static final String TAG_CATEGORY = "category";
	private static final String TAG_CITY = "stadt";
	private static final String TAG_LONG = "long";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LOGO = "icon";
	// private static final String TAG_PHONE_MOBILE = "mobile";
	// private static final String TAG_PHONE_HOME = "home";
	// private static final String TAG_PHONE_OFFICE = "office";

	JSONArray places = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_places_by_category);

		firmenList = new ArrayList<HashMap<String, String>>();
//		getCategory();
		new LoadAllProducts().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.places_by_category, menu);
		return true;
	}

	/*String getCategory() {

		Intent i = getIntent();
		// Receiving the Data
		this.ctg = i.getStringExtra("category");
		Log.e("Second Screen", ctg);
		return ctg;

	}*/

	/**
	 * Background Async Task to Load all product by making HTTP Request
	 * */
	class LoadAllProducts extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			fDialog = new ProgressDialog(PlacesByCategoryActivity.this);
			fDialog.setMessage("Loading products. Please wait...");
			fDialog.setIndeterminate(false);
			fDialog.setCancelable(false);
			fDialog.show();
		}

		/**
		 * getting All places from url
		 * */
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("category", ctg));
			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(UrlPlacesByCategory,"POST", params);
			System.out.println(json);

			// Check your log cat for JSON reponse
			Log.d("All Products: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					places = json.getJSONArray(TAG_PLACES);

					// looping through All Products
					for (int i = 0; i < places.length(); i++) {
						JSONObject c = places.getJSONObject(i);

						// Storing each json item in variable
						String id = c.getString(TAG_ID);
						String name = c.getString(TAG_NAME);

						String postcode = c.getString(TAG_PLZ);

						String cat = c.getString(TAG_CATEGORY);
						String city = c.getString(TAG_CITY);
						int laenge = new Integer(c.getString(TAG_LONG));
						int breite = new Integer(c.getString(TAG_LAT));
						
						String icon = c.getString(TAG_LOGO);

						// creating new HashMap
						HashMap<String, String> map = new HashMap<String, String>();

						// adding each child node to HashMap key => value
						map.put(TAG_ID, id);
						map.put(TAG_NAME, name);
						map.put(TAG_PLZ, postcode);
						map.put(TAG_CATEGORY, cat);
						map.put(TAG_CITY, city);
						map.put(TAG_NAME, name);

						// adding HashList to ArrayList
						firmenList.add(map);
					}
				}

				/*
				 * else { // no products found // Launch Add New product
				 * Activity Intent i = new Intent(getApplicationContext(),
				 * NewProductActivity.class); // Closing all previous activities
				 * i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); startActivity(i);
				 * }
				 */
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog after getting all products
			fDialog.dismiss();
			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					/**
					 * Updating parsed JSON data into ListView
					 * */
					ListAdapter adapter = new SimpleAdapter(
							PlacesByCategoryActivity.this, firmenList,
							R.layout.list_item, new String[] { TAG_NAME },
							new int[] { R.id.name });
					// updating listview
					setListAdapter(adapter);
				}
			});

		}

	}

}

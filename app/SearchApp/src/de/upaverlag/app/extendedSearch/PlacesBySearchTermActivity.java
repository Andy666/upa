package de.upaverlag.app.extendedSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.upaverlag.app.R;

import de.upaverlag.app.connection.json.JSONManager;

import android.os.AsyncTask;
import android.os.Bundle;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

public class PlacesBySearchTermActivity extends ListActivity {

	String zipCode;
	String term;
	
	// Progress Dialog
	private ProgressDialog fDialog;
	private boolean somethingFound = false;

	// Creating JSON Parser object
	JSONManager jParser = new JSONManager();

	ArrayList<HashMap<String, String>> firmenList;

	// url to get all products list
	private static String UrlPlacesByTerm = "http://10.0.2.2:80/android/app/erweiterteSuche.php";

	// JSON Node names
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_PLACES = "places";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";

	private static final String TAG_PLZ = "plz";
	
	private static final String TAG_DESCRIPT = "desc";

	private static final String TAG_CATEGORY = "category";
	private static final String TAG_CITY = "stadt";
	private static final String TAG_LONG = "long";
	private static final String TAG_LAT = "lat";
	private static final String TAG_LOGO = "icon";


	JSONArray places = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places_by_search_term_activity);
		firmenList = new ArrayList<HashMap<String, String>>();
		getValuesFromOtherActivity();
		new LoadAllResults().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.places_by_search_term, menu);
		return true;
	}
	
		void getValuesFromOtherActivity() {

			Intent i = getIntent();
			// Receiving the Data
			this.zipCode = i.getStringExtra("zip");
			this.term = i.getStringExtra("searchTerm");
			Log.e("Second Screen", zipCode + " "+term);
			
		}

	/*	void addActionOnItemClick (ListView lv){
			
			lv.setOnItemClickListener(new )
			
		}*/
		

		/**INNER CLASS
		 * Background Async Task to Load all product by making HTTP Request
		 * */
		class LoadAllResults  extends AsyncTask<String, String, String> {

			/**
			 * Before starting background thread Show Progress Dialog
			 * */
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				fDialog = new ProgressDialog(PlacesBySearchTermActivity.this);
				fDialog.setMessage("Verbindung wird hergestellt. Bitte warten...");
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

				params.add(new BasicNameValuePair("plz", zipCode));
				params.add(new BasicNameValuePair("desc", term));
				// getting JSON string from URL
				JSONObject json = jParser.makeHttpRequest(UrlPlacesByTerm,
						"GET", params);

				System.out.println(json);

				// Check your log cat for JSON reponse
				Log.d("All Products: ", json.toString());

				try {
					// Checking for SUCCESS TAG
					int success = json.getInt(TAG_SUCCESS);

					if (success == 1) {
						// products found
						somethingFound = true;
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
							 String  laenge = c.getString(TAG_LONG);
							 String  breite =c.getString(TAG_LAT);
							 String details = c.getString(TAG_DESCRIPT);

							 String logo = c.getString(TAG_LOGO);

							// creating new HashMap
							HashMap<String, String> map = new HashMap<String, String>();

							// adding each child node to HashMap key => value
							map.put(TAG_ID, id);
							map.put(TAG_NAME, name);
							map.put(TAG_PLZ, postcode);
							map.put(TAG_CATEGORY, cat);
							 map.put(TAG_CITY, city);
							 map.put(TAG_LOGO, logo);
							 map.put(TAG_LONG, laenge);
							 map.put(TAG_LAT, breite);
							 map.put(TAG_DESCRIPT, details);
							 
							// adding HashList to ArrayList
							firmenList.add(map);
						}
					}
					else {
						
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
				if(somethingFound){
					runOnUiThread(new Runnable() {
					public void run() {
						/**
						 * Updating parsed JSON data into ListView
						 * */
						ListAdapter adapter = new SimpleAdapter(
								PlacesBySearchTermActivity.this, firmenList,
										R.layout.list_item, new String[] { TAG_NAME, TAG_CATEGORY, TAG_PLZ, TAG_CITY, TAG_LONG, TAG_LAT },
										new int[] { R.id.name , R.id.catg , R.id.plz , R.id.stadt , R.id.lgt , R.id.lat});
						// updating listview
						setListAdapter(adapter);
					}
				});
				}
				else {
					
				}
				

			}

		}

	}



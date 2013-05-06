package de.upaverlag.app.extendedSearch;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import de.upaverlag.app.ActionBarMenu;
import de.upaverlag.app.R;
import de.upaverlag.app.navi.AbMenuItemAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ExtendedSearchActivity extends ActionBarMenu {

	private Spinner selectCountry;
	private Button sbmButton;
	String plz;
	String city;
	String suchbegriff;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extended_search);
		useAutoComplete();
		addListenerOnButton();
		startActionBar();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extended_search_menu, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (actionBarItems[itemPosition].equals("Erweiterte Suche")) {
			startExtendedSearch();
		}
		if (actionBarItems[itemPosition].equals("Branchensuche")) { //
		 categorySearching();
		}
		return true;
	}

	void startActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getAbMenuContext();
		getActionBarItems();
		currentPos("Erweiterte Suche");
		
		AbMenuItemAdapter abItem = new AbMenuItemAdapter(actBarContext,
				android.R.layout.simple_list_item_1, actionBarItems);
		actionBar.setListNavigationCallbacks(abItem, this);
		
	}

	public void addListenerOnButton() {

		// selectCountry = (Spinner) findViewById(R.id.spinner1);

		sbmButton = (Button) findViewById(R.id.subbmitBtn);
		sbmButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startNextActivity();

			}
		});
	}

	void useAutoComplete() {

		String[] adresse = getResources().getStringArray(
				R.array.plzUndOrt_array);
		

		// Adapter + starting AutoComplet
		ArrayAdapter<String> autoCmpltAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, adresse);
		AutoCompleteTextView autoCmpltTextView = (AutoCompleteTextView) findViewById(R.id.ortTextField);
		autoCmpltTextView.setAdapter(autoCmpltAdapter);
		autoCmpltTextView.setThreshold(2);

		// get selected Item
		autoCmpltTextView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String values = ((TextView) arg1).getText().toString();
				String [] toSplit = values.split(" ");
				plz =  toSplit [0];
			}
		});
		

		// plz = autoCmpltTextView.getText().toString();

		// Landes String zerlegen in Array von String
		/*
		 * String[] toSplit = values.split("\\s"); for( int i = 0
		 * ;i<toSplit.length; i++){ System.out.println(toSplit[i]); } plz =
		 * toSplit[0];
		 */

		// TODO bei Bedarf Stadt in die Datenbank Abfragen einbeziehen
		/*
		 * String city = toSplit [1]; int wortZaehler = 2; if (toSplit.length >
		 * wortZaehler ) { for (int i=1; i<toSplit.length; i++ ){ String tmp =
		 * toSplit [i]; if (i>=wortZaehler) { city+=tmp; } } }
		 */

	}

	void getValueFromTextField() {

		TextView searchTxt = (TextView) findViewById(R.id.suchbegriff);
		suchbegriff = searchTxt.getText().toString();

	}

	void startNextActivity() {

		getValueFromTextField();
		Intent myIntent = new Intent(ExtendedSearchActivity.this,
				PlacesBySearchTermActivity.class);

		myIntent.putExtra("searchTerm", suchbegriff);
		myIntent.putExtra("zip", plz);
		Log.e("adresse", "ich suche: " + suchbegriff + " in " + plz);
		startActivity(myIntent);
	}

}

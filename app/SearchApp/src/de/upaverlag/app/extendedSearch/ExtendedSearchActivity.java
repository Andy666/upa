package de.upaverlag.app.extendedSearch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import de.upaverlag.app.R;
import android.widget.Button;
import android.widget.Spinner;


public class ExtendedSearchActivity extends Activity {
	
	private Spinner selectCountry;
	private Button sbmButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.extended_search);
		// addListenerOnSpinnerItemSelection();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.extended_search_menu, menu);
		return true;
	}
	
	public void addListenerOnSpinnerItemSelection() {
		selectCountry = (Spinner) findViewById(R.id.spinner1);
		
	  }
	 
	  // get the selected dropdown list value
	  public void addListenerOnButton() {
	 
		selectCountry = (Spinner) findViewById(R.id.spinner1);

		sbmButton = (Button) findViewById(R.id.subbmitBtn);
	  }
	
}

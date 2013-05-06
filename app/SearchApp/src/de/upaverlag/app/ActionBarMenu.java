package de.upaverlag.app;

import de.upaverlag.app.R;
import de.upaverlag.app.extendedSearch.ExtendedSearchActivity;
import de.upaverlag.app.navi.AbMenuItemAdapter;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class ActionBarMenu extends Activity implements
		ActionBar.OnNavigationListener {

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected item";
	protected String[] actionBarItems;
	protected Context actBarContext;
	ActionBar acb;
	protected int pos;

	protected void getActionBarItems() {
		String[] arrString = { getString(R.string.Branche),
				getString(R.string.directSearche), getString(R.string.favListe) };
		actionBarItems = arrString;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	private Context getActionBarThemedContextCompat() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return getActionBar().getThemedContext();
		} else {
			return this;
		}
	}

	protected Context getAbMenuContext() {
		actBarContext = getActionBarThemedContextCompat();
		return actBarContext;

	}


	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (actionBarItems[itemPosition].equals("Erweiterte Suche")) {
			startExtendedSearch();

		}

		if (actionBarItems[itemPosition].equals("Branchensuche")) {
			categorySearching();

		}

		return true;
	}

	protected void categorySearching() {
		Intent myIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(myIntent);

	}

	protected void startExtendedSearch() {
		Intent myIntent = new Intent(getApplicationContext(),
				ExtendedSearchActivity.class);
		myIntent.putExtra("menuItem", actionBarItems);
		startActivity(myIntent);

	}

	public ActionBar getActionBarMenu() {

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getActionBarItems();
		getAbMenuContext();

		return acb;

		// set SpinnerAdapter fuer ActionBar
		/*
		 * AbMenuItemAdapter abAdapter = new AbMenuItemAdapter(actBarContext,
		 * android.R.layout.simple_list_item_1, actionBarItems);
		 * actionBar.setListNavigationCallbacks(abAdapter, this);
		 */

	}

/*	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		// Restore the previously serialized current dropdown position.
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}*/

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// Serialize the current dropdown position.
	/*	String currentActivityName = this.getTitle().toString();
		if (!STATE_SELECTED_NAVIGATION_ITEM.equals(currentActivityName)) {
			outState.putInt(currentActivityName, getActionBar()
					.getSelectedNavigationIndex());
			Log.e("current selected ABmenuItem",currentActivityName);
		} else {*/
			outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
					.getSelectedNavigationIndex());
//		}
	}
	
	public void currentPos (String value){
		for (String s:actionBarItems){
			this.pos= s.indexOf(value);
			
		}
		
		
		
	}
}

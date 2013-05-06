package de.upaverlag.app;

import de.upaverlag.app.branchensuche.PlacesByCategoryActivity;
import de.upaverlag.app.extendedSearch.ExtendedSearchActivity;
//import de.upaverlag.app.navi.AbMenuItem;
import de.upaverlag.app.navi.AbMenuItemAdapter;
import android.os.Build;
import android.os.Bundle;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;

import android.widget.ImageButton;

import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarMenu
// Activity implements ActionBar.OnNavigationListener
{

	// final private static String STATE_SELECTED_NAVIGATION_ITEM = "selected";
	private boolean mainWindowIsOpen = true;
	private OnClickListener btnClick;

	ImageButton autoBtn;
	ImageButton foodBtn;
	ImageButton moneyBtn;

/*	private String[] actionBarItems;

	void getActionBarItems() {
		String[] arrString = { getString(R.string.Branche),
				getString(R.string.directSearche), getString(R.string.favListe) };

		this.actionBarItems = arrString;
	}*/

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_app_main);
		addListenerOnButton();
		startActionBar();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (actionBarItems[itemPosition].equals("Erweiterte Suche")) {
			startExtendedSearch();
		}
		/*if (actionBarItems[itemPosition].equals("Branchensuche")) { //
		 categorySearching();
		}*/
		return true;
	}

	void startActionBar() {
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getAbMenuContext();
		getActionBarItems();
		AbMenuItemAdapter abItem = new AbMenuItemAdapter(actBarContext,
				android.R.layout.simple_list_item_1, actionBarItems);
		actionBar.setListNavigationCallbacks(abItem, this);
	}

	/**
	 * offnet menu erweiterte Suche
	 */
	/*
	 * public void startExtendedSearch() {
	 * 
	 * Intent myIntent = new Intent(MainActivity.this,
	 * ExtendedSearchActivity.class); startActivity(myIntent); mainWindowIsOpen
	 * = false; }
	 */

	/**
	 * Back to upper Menu
	 * 
	 * @param keyCode
	 * @param event
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && mainWindowIsOpen == false) {

			mainWindowIsOpen = true;
			setContentView(R.layout.search_app_main);
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}

	public void addListenerOnButton() {

		autoBtn = (ImageButton) findViewById(R.id.button1);
		foodBtn = (ImageButton) findViewById(R.id.button3);
		moneyBtn = (ImageButton) findViewById(R.id.bank);

		btnClick = new OnClickListener() {
			@Override
			public void onClick(View v) {

				switch (v.getId()) {
				case R.id.button3:
					catSearching(foodBtn);
					break;

				case R.id.button1:
					catSearching(autoBtn);
					break;
				case R.id.bank:
					catSearching(moneyBtn);
					break;

				}
			}

		};

		autoBtn.setOnClickListener(btnClick);
		foodBtn.setOnClickListener(btnClick);
		moneyBtn.setOnClickListener(btnClick);
	}


	private void catSearching(ImageButton imgBtn) {
		// b = (Button)v;
		String buttonTxt = imgBtn.getTag().toString();
		Intent branchensuche = new Intent(MainActivity.this,
				PlacesByCategoryActivity.class);
		branchensuche.putExtra("category", buttonTxt);
		Log.e("n", buttonTxt);
		startActivity(branchensuche);
	}

	/*
	 * @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH) private Context
	 * getActionBarThemedContextCompat() { if (Build.VERSION.SDK_INT >=
	 * Build.VERSION_CODES.ICE_CREAM_SANDWICH) { return
	 * getActionBar().getThemedContext(); } else { return this; } }
	 * 
	 * void startActionBar() { final ActionBar actionBar = getActionBar();
	 * actionBar.setDisplayShowTitleEnabled(false);
	 * actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
	 * getActionBarItems(); // set SpinnerAdapter fuer ActionBar AbMenuItem
	 * abAdapter = new AbMenuItem( getActionBarThemedContextCompat(),
	 * android.R.layout.simple_list_item_1, actionBarItems);
	 * actionBar.setListNavigationCallbacks(abAdapter, this ); }
	 * 
	 * @Override public void onRestoreInstanceState(Bundle savedInstanceState) {
	 * // Restore the previously serialized current dropdown position. if
	 * (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
	 * getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(
	 * STATE_SELECTED_NAVIGATION_ITEM)); } }
	 * 
	 * @Override public void onSaveInstanceState(Bundle outState) { // Serialize
	 * the current dropdown position. String currentActivityName =
	 * getClass().getSimpleName();
	 * if(!STATE_SELECTED_NAVIGATION_ITEM.equals(currentActivityName)){
	 * outState.putInt(currentActivityName ,
	 * getActionBar().getSelectedNavigationIndex()); } else {
	 * outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
	 * getActionBar().getSelectedNavigationIndex()); } }
	 */

}

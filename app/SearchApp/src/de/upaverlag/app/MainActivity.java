package de.upaverlag.app;

import de.upaverlag.app.branchensuche.BranchenSucheActivity;
import de.upaverlag.app.extendedSearch.ExtendedSearchActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	
	private boolean mainWindowIsOpen = true;
	private OnClickListener btnClick ;
	
	Button searchBtn;
	Button categoryBtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListenerOnButton ();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * offnet menu Branchensuche
	 * 
	 * @param v Neue View
	 */
	private void categorySearching() {
		Intent branchensuche = new Intent(MainActivity.this, BranchenSucheActivity.class);
		 startActivity(branchensuche);
		mainWindowIsOpen = false;
	}
	
	/**
	 * offnet menu erweiterte Suche 
	 * 
	 * @param v Neue View
	 */
	private void startExtendedSearch(View v) {
		
		 Intent myIntent = new Intent(MainActivity.this, ExtendedSearchActivity.class);
		 startActivity(myIntent);
		 mainWindowIsOpen = false;
	}
	
	/** 
	 * @param keyCode
	 * @param event
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && mainWindowIsOpen == false) {

			mainWindowIsOpen = true;
			setContentView(R.layout.activity_main);
			return true;
		}

		return super.onKeyDown(keyCode, event);

	}
	
	public void addListenerOnButton (){
		
		searchBtn =  (Button) findViewById(R.id.button2);
		categoryBtn =  (Button) findViewById(R.id.button3);
		

		btnClick = new OnClickListener() {
	     @Override
	     public void onClick(View v) {
	         switch(v.getId()){
	             case R.id.button3:
	            	 categorySearching();
	             break;
	             case R.id.button2:
	            	 startExtendedSearch(v);
	             break;
	             case R.id.button1:
	                  //DO something
	             break;
	         }

	   }
	};

	searchBtn.setOnClickListener(btnClick); 
	categoryBtn.setOnClickListener(btnClick); 
	}
	
}

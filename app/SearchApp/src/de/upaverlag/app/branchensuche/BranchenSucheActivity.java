package de.upaverlag.app.branchensuche;


import de.upaverlag.app.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BranchenSucheActivity {

/*	private Button autoBtn;
	private Button foodBtn;
	private OnClickListener btnClick;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.branchensuche);
		addListenerOnButton();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.branchen_suche_menu, menu);
		return true;
	}

	public void addListenerOnButton() {

		autoBtn = (Button) findViewById(R.id.button2);
		foodBtn = (Button) findViewById(R.id.button1);

		btnClick = new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				switch (v.getId()) {
				case R.id.button1:
					catSearching(foodBtn);
					break;
				
				case R.id.button2:
					catSearching(autoBtn);
					break;	
				case R.id.button3:
					catSearching(foodBtn);
					break;
				default:
					
					break;
				}
			}
		};
		autoBtn.setOnClickListener(btnClick);
		foodBtn.setOnClickListener(btnClick);
	}*/

	

/*	private void catSearching(Button b ) {
		//b = (Button)v;
		String buttonTxt = b.getText().toString();
		Intent branchensuche = new Intent(BranchenSucheActivity.this, PlacesByCategoryActivity.class);
		branchensuche.putExtra("category", buttonTxt);
		Log.e ("n", buttonTxt);
		 startActivity(branchensuche);
	}*/

}

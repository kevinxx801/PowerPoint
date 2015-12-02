package com.example.kevin.powerpoint;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;



/**
 * Created by Kevin on 11/30/2015.
 */
public class SettingsActivity extends AppCompatActivity {

	private TextView slideAlert;
	private TextView presentationAlert;
	private TextView current;
	private boolean leaveScreen;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Disable default keys
		this.setDefaultKeyMode(this.DEFAULT_KEYS_DISABLE);
		setContentView(R.layout.settings_layout);
		Resources res = getResources();
		slideAlert = (TextView) findViewById(R.id.slideAlert);
		presentationAlert = (TextView) findViewById(R.id.presentationAlert);
		current = slideAlert;
		leaveScreen = false;


	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStat
		// Handle action bar item clicks here. The actioement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}


	private void incrementAlert() {

		int currentAlertTime = Integer.parseInt(current.getText().toString());
		currentAlertTime++;
		current.setText(currentAlertTime);

	}

	private void decrementAlert() {

		int currentAlertTime = Integer.parseInt(current.getText().toString());
		currentAlertTime--;
		current.setText(currentAlertTime);

	}


	private void next() {
		if(leaveScreen == false){
			current = presentationAlert;
			leaveScreen = true;
		}
		else{
			//Leave Screen
		}

	}

	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {

		// KC 22 S 257  next slide
		// KC 21 S 257  prev slide
		// KC 66 S 257  scroll
		// KC 4 S 257   back
		if (event.getSource() == 257) {
			switch (keyCode) {
				case 22:
					incrementAlert();
					break;
				case 21:
					decrementAlert();
					break;
				case 66:
					next();
					break;
				case 4:
					break;
				default:
					break;
			}
		}
		if(keyCode == KeyEvent.KEYCODE_HOME)
		{
		}
		return true;
	}

}

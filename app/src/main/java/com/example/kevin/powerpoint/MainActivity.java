package com.example.kevin.powerpoint;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Disable default keys
		this.setDefaultKeyMode(this.DEFAULT_KEYS_DISABLE);
		setContentView(R.layout.acitivity_main);

		final Button button = (Button) findViewById(R.id.present);
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startPresentation();
			}
		});
	}



	public void startPresentation(){
		Intent intentPres = new Intent(MainActivity.this,
				PresentationMain.class
		);
		MainActivity.this.startActivity(intentPres);
	}
}

package com.example.kevin.powerpoint;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.application.monitor.VolumeMonitor;
import com.application.presentation.Presentation;
import com.application.timer.Timer;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	private Timer tSlide;
	private Timer tPresentation;
	private TextView totalTimer;
	private TextView slideTimer;
	private ProgressBar volumeBar;
	private VolumeMonitor volumeMonitor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Disable default keys
		this.setDefaultKeyMode(this.DEFAULT_KEYS_DISABLE);
		setContentView(R.layout.activity_main);

		Resources res = getResources();
		SlideShow ppt = null;
		try {
			ppt = new SlideShow(new HSLFSlideShow( res.getAssets().open("slideshow1.ppt") ));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Presentation presentation = new Presentation();
        presentation.loadPowerpoint(ppt);

		tSlide = new Timer();
		tSlide.startTimer();

		tPresentation = new Timer();
		tPresentation.startTimer();

		slideTimer = (TextView) findViewById(R.id.slideTimer);
		totalTimer = (TextView) findViewById(R.id.totalTimer);

		final Handler handler=new Handler();
		handler.post(new Runnable(){

			@Override
			public void run() {
				slideTimer.setText(tSlide.getElapsedTimeString());
				totalTimer.setText(tPresentation.getElapsedTimeString());
				handler.postDelayed(this,100);
			}
		});

		volumeBar = (ProgressBar) findViewById(R.id.volumeBar);

		volumeMonitor = new VolumeMonitor();
		final Handler volumeHandler=new Handler();
		volumeHandler.post(new Runnable(){

			@Override
			public void run() {
				volumeBar.setProgress((int)volumeMonitor.getAmplitude());
				volumeHandler.postDelayed(this,100);
			}
		});

        /*
        final Button goToNotesButton = (Button) findViewById(R.id.goToNotes);
        goToNotesButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondAttemptMenu.this, ShowNotes.class));
            }
        });

        final Button nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                nextButton.setText("next was pressed");
            }
        });
        */
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




	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {


		android.util.Log.i("TESTING BUTTONS", "Key Code: " + keyCode + "Source: " + event.getSource());
		if(keyCode == KeyEvent.KEYCODE_HOME)
		{
		}
		return true;
	}

}

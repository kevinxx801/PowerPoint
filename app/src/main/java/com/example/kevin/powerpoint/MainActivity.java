package com.example.kevin.powerpoint;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Scroller;
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
	private TextView slideCount;
	private ProgressBar volumeBar;
	private VolumeMonitor volumeMonitor;
	private TextView notes;
	private Presentation presentation;
	private Scroller scroller;
	private static final int SCROLL_AMOUNT = 60;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Disable default keys
		this.setDefaultKeyMode(this.DEFAULT_KEYS_DISABLE);
		setContentView(R.layout.activity_main);

		Resources res = getResources();
		SlideShow ppt = null;
		try {
			ppt = new SlideShow(new HSLFSlideShow( res.getAssets().open("Presentation1.ppt") ));
		} catch (IOException e) {
			e.printStackTrace();
		}

		tSlide = new Timer();
		tSlide.startTimer();

		tPresentation = new Timer();
		tPresentation.startTimer();

		slideTimer = (TextView) findViewById(R.id.slideTimer);
		totalTimer = (TextView) findViewById(R.id.totalTimer);
		slideCount = (TextView) findViewById(R.id.slideCount);
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

		presentation = new Presentation();
		presentation.loadPowerpoint(ppt);
		scroller = new Scroller(getApplicationContext());
		notes = (TextView) findViewById(R.id.notes);
		notes.setText(presentation.getCurrentSlide().getNote().getMessage());
		notes.setScroller(scroller);
		slideCount.setText(presentation.getCurrentSlideIndex() + "/" + presentation.getTotalSlideCount());

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


	private void nextSlide() {
		presentation.nextSlide();
		tSlide.startTimer();
		slideCount.setText(presentation.getCurrentSlideIndex() + "/" + presentation.getTotalSlideCount());
		notes.setText(presentation.getCurrentSlide().getNote().getMessage());
		scroller.startScroll(0,0,0,0,100);
	}

	private void prevSlide() {
		presentation.previousSlide();
		tSlide.startTimer();
		slideCount.setText(presentation.getCurrentSlideIndex() + "/" + presentation.getTotalSlideCount());
		notes.setText(presentation.getCurrentSlide().getNote().getMessage());
		scroller.startScroll(0,0,0,0,100);
	}

	private void scrollDown() {

		notes.setText(presentation.getCurrentSlide().getNote().getMessage());
		scroller.startScroll(0,scroller.getCurrY(),0,SCROLL_AMOUNT,500);
	}

	private void goBack() {

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
					nextSlide();
					break;
				case 21:
					prevSlide();
					break;
				case 66:
					scrollDown();
					break;
				case 4:
					goBack();
					break;
				default:
					break;
			}
		}
		android.util.Log.i("TESTING BUTTONS", "Key Code: " + keyCode + "Source: " + event.getSource());
		if(keyCode == KeyEvent.KEYCODE_HOME)
		{
		}
		return true;
	}

}

package com.example.kevin.powerpoint;

import android.media.MediaRecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

	private Thread volumeTest;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Disable default keys
		this.setDefaultKeyMode(this.DEFAULT_KEYS_DISABLE);
		setContentView(R.layout.activity_main);

		volumeTest = new Thread(){
			private MediaRecorder mRecorder = null;

			public void startRecorder() {
				if (mRecorder == null) {
					mRecorder = new MediaRecorder();
					mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mRecorder.setOutputFile("/dev/null");
					try {
						mRecorder.prepare();
					}
					catch(IOException e) {
					}
					mRecorder.start();
				}
			}

			public void done() {
				if (mRecorder != null) {
					mRecorder.stop();
					mRecorder.release();
					mRecorder = null;
				}
			}

			public double getAmplitude() {
				if (mRecorder != null)
					return  mRecorder.getMaxAmplitude();
				else
					return 0;

			}

			public void run()
			{
				try {
					startRecorder();
					while (true) {
						android.util.Log.i("TESTING THREAD",String.valueOf(getAmplitude()));
						Thread.sleep(500);
					}
				}catch (InterruptedException ex) {
					done();
				}
			}

		};

		volumeTest.start();
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
		volumeTest.interrupt();
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

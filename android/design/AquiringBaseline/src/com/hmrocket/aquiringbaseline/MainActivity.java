package com.hmrocket.aquiringbaseline;


import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	private BaselineOverlay baselineOverlay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		baselineOverlay = new BaselineOverlay((TextView) findViewById(R.id.tx_baseline_countdown));
		baselineOverlay.startBaselineAnimation();
		new CountDownTimer(30000, 800) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				baselineOverlay.setSessionTime(millisUntilFinished);
			}
			
			@Override
			public void onFinish() {
				baselineOverlay.stopAndHide();
				
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

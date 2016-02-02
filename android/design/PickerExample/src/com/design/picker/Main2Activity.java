package com.design.picker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.NumberPicker;

public class Main2Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		String[] displayedValues = new String[] { "TPS Reborn", "TPS 2016", "TPS 01013" };
		setPicker(displayedValues, (NumberPicker) findViewById(R.id.nPicker_device));
		displayedValues = new String[] { "5 min", "10 min", "15 min", "20 min", "25 min" };
		setPicker(displayedValues, (NumberPicker) findViewById(R.id.nPicker_time));
		displayedValues = new String[] { "18000 Pts", "21000 Pts", "24500 Pts", "27500 Pts", "31500 Pts" };
		setPicker(displayedValues, (NumberPicker) findViewById(R.id.nPicker_goal));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_next) {
			Intent intent = new Intent(this, Main3Activity.class);
			intent.putExtra("design", "card");
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
	public void setPicker(final String[] displayedValues, NumberPicker picker) {

		picker.setMaxValue(displayedValues.length - 1);
		picker.setMinValue(0);
		picker.setDisplayedValues(displayedValues);
		picker.setWrapSelectorWheel(false);

	}
	
}

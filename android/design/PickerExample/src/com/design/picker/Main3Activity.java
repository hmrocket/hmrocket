package com.design.picker;

import java.util.Arrays;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.NumberPicker;

public class Main3Activity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);

		findViewById(R.id.btn_device).setOnClickListener(this);
		findViewById(R.id.btn_time).setOnClickListener(this);
		findViewById(R.id.btn_goal).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// launch a picker depending on the button clicked
		String[] displayedValues;
		switch (v.getId()) {
		case R.id.btn_device:
			displayedValues = new String[] { "TPS Reborn", "TPS 2016", "TPS 01013" };
			show(getString(R.string.paired_devices), displayedValues, (Button) v);
			break;
		case R.id.btn_goal:
			displayedValues = new String[] { "18000 Pts", "21000 Pts", "24500 Pts", "27500 Pts", "31500 Pts" };
			show(getString(R.string.set_goal), displayedValues, (Button) v);
			break;
		case R.id.btn_time:
			displayedValues = new String[] { "5 min", "10 min", "15 min", "20 min", "25 min" };
			show(getString(R.string.session_time), displayedValues, (Button) v);
			break;
		default:
			break;
		}
	}

	public void show(final String title, final String[] displayedValues, final Button btnValue) {

		final Dialog d = new Dialog(Main3Activity.this);
		d.setTitle(title);
		d.setContentView(R.layout.dialog_picker);
		Button btnCancel = (Button) d.findViewById(R.id.btn_cancel);
		Button btnOk = (Button) d.findViewById(R.id.btn_ok);
		final NumberPicker np = (NumberPicker) d.findViewById(R.id.nPicker);
		np.setMaxValue(displayedValues.length - 1);
		np.setMinValue(0);
		np.setDisplayedValues(displayedValues);
		np.setWrapSelectorWheel(false);
		int indexOf = Arrays.asList(displayedValues).indexOf(btnValue.getText());
		np.setValue(indexOf == -1 ? 0 : indexOf);
		final OnClickListener dialogList = new OnClickListener() {

			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.btn_cancel:
					d.dismiss();
					break;

				case R.id.btn_ok:
					btnValue.setText(displayedValues[((Integer) btnValue.getTag()).intValue()]);
					d.dismiss();
					break;
				}
			}
		};

		np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {

			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				btnValue.setTag(newVal);
			}
		});

		btnCancel.setOnClickListener(dialogList);
		btnOk.setOnClickListener(dialogList);
		d.show();

	}
}

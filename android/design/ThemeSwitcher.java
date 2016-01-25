package com.hmrocket.design;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class ThemeSwitcher {

	private static int sTheme = -1;

	/* Themes */
	public final static int THEME_DEFAULT = 0;
	public final static int THEME_WHITE = 1;
	public final static int THEME_BLUE = 2;
	// Preference
	public final static String PREF_THEME_ID = "APP_THEME";

	/**
	 * Set the theme of the Activity, and restart it by creating a new Activity
	 * of the same type.
	 */
	public static void changeToTheme(Activity activity, int theme) {
		if (theme == sTheme) {
			// do nothing, theme is already set
			return;
		}

		// Save the new theme
		sTheme = theme;
		Editor prefEditor = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext()).edit();
		prefEditor.putInt(PREF_THEME_ID, theme).commit();

		// recreate
		activity.finish();
		activity.startActivity(new Intent(activity, activity.getClass()));
	}

	/**
	 * Set the prefered theme to the Activity
	 */
	public static void applySharedTheme(Activity activity) {
		if (sTheme == -1) {
			SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
			sTheme = pref.getInt(PREF_THEME_ID, THEME_DEFAULT);
		}

		setTheme(activity);
	}

	/** Set the theme of the activity, according to the configuration. */
	private static void setTheme(Activity activity) {
		switch (sTheme) {
		default:
		case THEME_DEFAULT:
			activity.setTheme(android.R.style.Theme_Holo);
			break;
		case THEME_WHITE:
			activity.setTheme(android.R.style.Theme_Holo_Light);
			break;
		case THEME_BLUE:
			activity.setTheme(android.R.style.Theme_Black);
			break;
		}
	}
}

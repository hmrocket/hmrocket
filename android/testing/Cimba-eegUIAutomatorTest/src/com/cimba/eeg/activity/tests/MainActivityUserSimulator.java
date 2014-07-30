/**
 * 
 */
package com.cimba.eeg.activity.tests;

import android.widget.ProgressBar;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.cimba.eeg.tests.BlackBoxCimbaEEG;

/**
 * Present all the possible user interaction in MainActivity
 * 
 * @author Mhamed Hlioui
 * @since Jul 24, 2014
 */
public final class MainActivityUserSimulator {

	/*
	 * *************************
	 * CAB MENU ITEMS
	 */
	public static final String MENU_ABOUT = "About";
	public static final String MENU_SETTING = "Settings";
	public static final String MENU_NEW_SESSION_TEXT = "New";
	public static final String MENU_STOP_SESSION_TEXT = "Stop";
	public static final String MENU_PAUSE_SESSION_TEXT = "Pause";
	public static final String MENU_T1_TEXT = "T1";
	public static final String MENU_T2_TEXT = "T2";
	public static final String MENU_T3_TEXT = "T3";
	public static final String DIALOG_CONNECTING_IN_PROGRESS_TITLE = "Please wait";

	public static void goToAboutActivity(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		// Launch About from Main
		UiObject menuMoreOption = new UiObject(
				new UiSelector()
						.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
		boolean pressMenu = menuMoreOption.exists();
		if (pressMenu)
			menuMoreOption.click();
		else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
			throw new UiObjectNotFoundException(
					"Couldn't click Overflow options menu item");

		UiObject menuAbout = new UiObject(new UiSelector().text(MENU_ABOUT));
		if (menuAbout.exists()) {
			menuAbout
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		} else {
			throw new UiObjectNotFoundException("Couldn't find menu item About");
		}
	}

	public static void goToSettingActivity(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {

		// Launch Setting from Main
		UiObject menuMoreOption = new UiObject(
				new UiSelector()
						.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
		boolean pressMenu = menuMoreOption.exists();
		if (pressMenu)
			menuMoreOption.click();
		else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
			throw new UiObjectNotFoundException(
					"Couldn't click Overflow options menu item");

		UiObject menuSetting = new UiObject(
				new UiSelector().textContains(MENU_SETTING));
		if (menuSetting.exists()) {
			menuSetting
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		} else {
			throw new UiObjectNotFoundException(
					"Couldn't find menu item Setting");
		}
	}

	public static void startNewSession(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject newSession = new UiObject(
				new UiSelector().textContains(MENU_NEW_SESSION_TEXT));
		if (newSession.exists())
			newSession
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The New menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (newSession.exists()) {
				newSession
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				uiAutomatorTC.getUiDevice().pressBack();
				throw new UiObjectNotFoundException(
						"Couldn't find new session menu item ");
			}
		}
	}

	public static void stopSession(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject stopSession = new UiObject(
				new UiSelector().textContains(MENU_STOP_SESSION_TEXT));
		if (stopSession.exists())
			stopSession
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The Stop menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (stopSession.exists()) {
				stopSession
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				uiAutomatorTC.getUiDevice().pressBack();
				throw new UiObjectNotFoundException(
						"Couldn't find Stop session menu item ");
			}
		}
	}

	public static void pauseSession(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject pauseSession = new UiObject(
				new UiSelector().textContains(MENU_PAUSE_SESSION_TEXT));
		if (pauseSession.exists())
			pauseSession
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The Pause menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (pauseSession.exists()) {
				pauseSession
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				uiAutomatorTC.getUiDevice().pressBack();
				throw new UiObjectNotFoundException(
						"Couldn't find Pause session menu item ");
			}
		}
	}

	/**
	 * This method should be called when the connecting dialog is shown
	 * 
	 * @param device
	 * @return true if the connect dialog is shown and we are connecting to
	 *         <code>device</code>>
	 * @see MainActivityUserSimulator#isConnectingInProgress()
	 */
	public static boolean isConnectingTo(String device) {
		UiObject connectingTo = new UiObject(
				new UiSelector().textContains(device));
		return connectingTo.exists();
	}

	/**
	 * @return true if the user has click start session and the connect dialog
	 *         still visible
	 */
	public static boolean isConnectingInProgress() {
		UiObject dialogTitle = new UiObject(
				new UiSelector().text(DIALOG_CONNECTING_IN_PROGRESS_TITLE));
		UiObject progressDilog = new UiObject(
				new UiSelector().className(ProgressBar.class));
		return dialogTitle.exists() && progressDilog.exists();
	}

	public static void clickT1ActionMenu(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject t1ActionMenuItem = new UiObject(
				new UiSelector().textContains(MENU_T1_TEXT));
		if (t1ActionMenuItem.exists())
			t1ActionMenuItem
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The T1 menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (t1ActionMenuItem.exists()) {
				t1ActionMenuItem
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				throw new UiObjectNotFoundException(
						"Couldn't find T1 menu item ");
			}
		}
	}

	public static void clickT2ActionMenu(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject t2ActionMenuItem = new UiObject(
				new UiSelector().textContains(MENU_T2_TEXT));
		if (t2ActionMenuItem.exists())
			t2ActionMenuItem
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The T2 menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (t2ActionMenuItem.exists()) {
				t2ActionMenuItem
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				throw new UiObjectNotFoundException(
						"Couldn't find T2 menu item ");
			}
		}
	}

	public static void clickT3ActionMenu(UiAutomatorTestCase uiAutomatorTC)
			throws UiObjectNotFoundException {
		UiObject t3ActionMenuItem = new UiObject(
				new UiSelector().textContains(MENU_T3_TEXT));
		if (t3ActionMenuItem.exists())
			t3ActionMenuItem
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {

			// The T3 menu item might be hidden in the MENU_OVERFLOW
			UiObject menuMoreOption = new UiObject(
					new UiSelector()
							.description(BlackBoxCimbaEEG.MENU_OVERFLOW_DESCRIPTION));
			boolean pressMenu = menuMoreOption.exists();
			if (pressMenu)
				menuMoreOption.click();
			else if (pressMenu = uiAutomatorTC.getUiDevice().pressMenu() == false)
				throw new UiObjectNotFoundException(
						"Couldn't click Overflow options menu item");

			if (t3ActionMenuItem.exists()) {
				t3ActionMenuItem
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			} else {
				throw new UiObjectNotFoundException(
						"Couldn't find T3 menu item ");
			}
		}
	}

}

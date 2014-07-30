/**
 * 
 */
package com.cimba.eeg.tests.util;

import android.os.RemoteException;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.cimba.eeg.tests.BlackBoxCimbaEEG;

/**
 * it's a utility class. Present the needed user interaction with system to
 * proceed wit h Black-Box testing
 * 
 * @author Mhamed Hlioui
 * @since Jul 25, 2014
 */
public final class SystemUserSimulator {

	// Consider Package Instead of Private Access with Private Inner Classes
	// http://developer.android.com/training/articles/perf-tips.html#PackageInner
	static final class RecentApps {
		static final String ACTION_REMOVE = "Remove from list";
		static final String ACTION_APP_INFO = "App info";
	}

	static final class Settings {
		static final String App_NAME = "Settings";
		static final String APPS_MANAGER = "Apps";
		static final String APPS_MANAGER_CLEAR_DATA = "Clear data";
	}

	private static final long TIMEOUT_LONG = 500;
	private static final long TIMEOUT_SHORT = 200;

	/**
	 * Clear the data of an installed app on the device
	 * 
	 * @param uiAutomatorTC
	 * @param appName
	 * @throws UiObjectNotFoundException
	 *             thrown if the app isn't installed
	 * @throws RemoteException
	 *             thrown by the system
	 */
	public static final void clearAppData(UiAutomatorTestCase uiAutomatorTC,
			String appName) throws UiObjectNotFoundException, RemoteException {
		uiAutomatorTC.getUiDevice().pressRecentApps();

		// 1 -Access app info
		launchApp(uiAutomatorTC, Settings.App_NAME);
		UiScrollable preferenceList = new UiScrollable(
				new UiSelector().scrollable(true));
		UiObject uiAppsManager = new UiObject(
				new UiSelector().text(Settings.APPS_MANAGER));
		preferenceList.scrollIntoView(uiAppsManager);
		uiAppsManager.clickAndWaitForNewWindow(TIMEOUT_LONG);
		UiObject app = new UiObject(new UiSelector().text(appName));
		if (app.exists() == false) {
			// Fetch the app in the list
			UiScrollable appInfoList = new UiScrollable(
					new UiSelector().className(ListView.class));
			if (appInfoList.scrollIntoView(app) == false) {
				throw new UiObjectNotFoundException(appName + " not installed");
			}
		}
		app.clickAndWaitForNewWindow(TIMEOUT_LONG);
		// 2- clear data
		UiObject clearDataButton = new UiObject(
				new UiSelector().text(Settings.APPS_MANAGER_CLEAR_DATA));
		if (clearDataButton.exists() == false) {
			// Scroll to find clear data button
			UiScrollable scrollView = new UiScrollable(
					new UiSelector().className(ScrollView.class));
			scrollView.scrollIntoView(clearDataButton);
		}

		clearDataButton.clickAndWaitForNewWindow(TIMEOUT_SHORT);
		UiObject okButton = new UiObject(new UiSelector().text("OK"));
		okButton.click();
		uiAutomatorTC.getUiDevice().pressBack();
		uiAutomatorTC.getUiDevice().pressBack();
	}

	/**
	 * Kill an app from the recentTask
	 * 
	 * @param uiAutomatorTC
	 * @param appName
	 * @throws UiObjectNotFoundException
	 *             thrown if the app isn't in Recent Task list
	 * @throws RemoteException
	 *             thrown by the system
	 */
	public static final void removeAppFromRecentTasks(
			UiAutomatorTestCase uiAutomatorTC, String appName)
			throws UiObjectNotFoundException, RemoteException {
		executeActionInRecentApps(uiAutomatorTC, appName,
				RecentApps.ACTION_REMOVE);

	}

	/**
	 * Access the AppInfo panel From recent task list
	 * 
	 * @param uiAutomatorTC
	 * @param appName
	 *            App name
	 * @throws UiObjectNotFoundException
	 *             thrown if the app isn't in Recent Task list
	 * @throws RemoteException
	 *             thrown by he system
	 */
	public static final void accessAppInfoFromRecentTasks(
			UiAutomatorTestCase uiAutomatorTC, String appName)
			throws UiObjectNotFoundException, RemoteException {
		executeActionInRecentApps(uiAutomatorTC, appName,
				RecentApps.ACTION_APP_INFO);
	}

	private final static void executeActionInRecentApps(
			UiAutomatorTestCase uiAutomatorTC, String appName, String action)
			throws RemoteException, UiObjectNotFoundException {
		// 1- pressRecentApps
		uiAutomatorTC.getUiDevice().pressRecentApps();
		// 2- find app
		UiObject appTitle = new UiObject(new UiSelector().text(appName));

		if (appTitle.exists() == false) {
			// Couldn't find app in the recent task list
			// If the list is scrollable the app might be hidden... Scroll the
			// list to find it
			UiScrollable recentAppsList = new UiScrollable(
					new UiSelector().scrollable(true));
			if (recentAppsList.exists() == false
					|| recentAppsList.scrollIntoView(appTitle) == false) {
				// exit RecentApps and throw error
				uiAutomatorTC.getUiDevice().pressBack();
				throw new UiObjectNotFoundException(appName
						+ " not found in the Recent tasks list");
			}
		}
		// 3- trigger CM by a longClick
		UiObject recentappTaskImage = appTitle.getFromParent(new UiSelector()
				.className(FrameLayout.class));
		recentappTaskImage.longClick();
		// after a long click u might need to wait until contextual menu is
		// shown
		// 4 - Execute action
		UiObject uiAction = new UiObject(new UiSelector().text(action));
		if (uiAction.exists()) {
			uiAction.click();
		} else {
			// exit RecentApps and throw error
			uiAutomatorTC.getUiDevice().pressBack();
			uiAutomatorTC.getUiDevice().pressBack();
			throw new UiObjectNotFoundException(action
					+ " not found in RecentTasks's contextual menu ");
		}
	}

	public static final void launchApp(UiAutomatorTestCase uiAutomatorTC,
			String appName) throws UiObjectNotFoundException {
		// Simulate a short press on the HOME button.
		uiAutomatorTC.getUiDevice().pressHome();

		// We’re now in the home screen. Next, we want to simulate
		// a user bringing up the All Apps screen.
		// If you use the uiautomatorviewer tool to capture a snapshot
		// of the Home screen, notice that the All Apps button’s
		// content-description property has the value “Apps”. We can
		// use this property to create a UiSelector to find the button.
		UiObject allAppsButton = new UiObject(
				new UiSelector().description("Apps"));

		// Simulate a click to bring up the All Apps screen.
		if (allAppsButton.exists())
			allAppsButton
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else {
			// The Nexus 7 running on Cynamod 4.4 has text set to Apps
			allAppsButton = new UiObject(new UiSelector().text("Apps"));
			if (allAppsButton.exists())
				allAppsButton
						.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			else
				throw new UiObjectNotFoundException(
						"Couldn't find Apps button UiSelector[DESCRIPTION=Apps] and UiSelector[TEXT=Apps]");
		}

		// In the All Apps screen, the Cimba-EEG app is located in
		// the Apps tab. To simulate the user bringing up the Apps tab,
		// we create a UiSelector to find a tab with the text
		// label “Apps”.
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

		// Simulate a click to enter the Apps tab.
		if (appsTab.exists())
			appsTab.click();
		else
			System.out.println("appsTab not found");

		// Next, in the apps tabs, we can simulate a user swiping until
		// they come to the Settings app icon. Since the container view
		// is scrollable, we can use a UiScrollable object.
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));

		UiObject app = new UiObject(new UiSelector().text(appName));

		if (appViews.exists()) {
			// Set the swiping mode to horizontal (the default is vertical)
			// if(appViews.exists())
			appViews.scrollIntoView(app);
		} else
			System.out.println("No Scrollable View found");

		if (app.exists())
			app.click();
		else
			throw new UiObjectNotFoundException("Cimba-EEG wasn't found");

	}
}

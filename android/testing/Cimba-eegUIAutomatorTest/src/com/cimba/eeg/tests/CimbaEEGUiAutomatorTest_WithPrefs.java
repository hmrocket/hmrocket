/**
 * 
 */
package com.cimba.eeg.tests;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.cimba.eeg.activity.tests.AboutActivityUserSimulator;
import com.cimba.eeg.activity.tests.CimbaPreferenceActivityUserSimulator;
import com.cimba.eeg.activity.tests.MainActivityUserSimulator;
import com.cimba.eeg.tests.util.SystemUserSimulator;

/**
 * <p>
 * This a Black-Box UI testing of the Cimba-App for a predefined Preference
 * </p>
 * <p>
 * Credit: http://developer.android.com/tools/testing/testing_ui.html
 * </p>
 * 
 * @author mhamed
 * @since Jul 25, 2014
 */
public class CimbaEEGUiAutomatorTest_WithPrefs extends UiAutomatorTestCase {

	private final static boolean D = true;

	private String device;
	private boolean isDebuggable;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		device = BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_SELECTED_DEVICE;
		isDebuggable = Boolean.parseBoolean(getParams().getString(
				BlackBoxCimbaEEG.EXTRA_DEBUGGABLE));
	}

	public void testUseCaseScenario() throws UiObjectNotFoundException {
		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("launch "
					+ BlackBoxCimbaEEG.APP_UNDER_TEST);
		SystemUserSimulator.launchApp(this, BlackBoxCimbaEEG.APP_UNDER_TEST);
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("launch "
					+ BlackBoxCimbaEEG.APP_UNDER_TEST);

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("launch Setting activity");
		launchSettingTest();
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("launch Setting activity");

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("Setting Activity State");
		settingActivityStateTest();
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("Setting Activity State");

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("Setting Activity functional");
		exploreSettingActivityTest();
		if (D)
			BlackBoxCimbaEEG
					.testMethodSuccessful("Setting Activity functional");

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("Connect to predefined device");
		connectDeviceTest();
		if (D)
			BlackBoxCimbaEEG
					.testMethodSuccessful("Connect to predefined device");

		if (isDebuggable) {
			if (D)
				BlackBoxCimbaEEG.echoTestingMethod("launch About activity");
			launchAboutTest();
			if (D)
				BlackBoxCimbaEEG.testMethodSuccessful("launch About activity");

			if (D)
				BlackBoxCimbaEEG.echoTestingMethod("About activity functional");
			exploreAboutActivity();
			if (D)
				BlackBoxCimbaEEG
						.testMethodSuccessful("About activity functional");
		}
	}

	/**
	 * Launch of the Setting Activity of Cimba EEG from the
	 * MainActivityUserSimulator
	 * 
	 * @throws UiObjectNotFoundException
	 */
	protected void launchSettingTest() throws UiObjectNotFoundException {
		// Launch Setting
		MainActivityUserSimulator.goToSettingActivity(this);
		getUiDevice().pressBack();
	}

	protected void settingActivityStateTest() throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToSettingActivity(this);
		// we are at Setting activity and we going to Verify all its preference
		// it should match the predefined preference
		// 1- Device list preference
		boolean isDeviceSelected = CimbaPreferenceActivityUserSimulator
				.isDeviceSelected(BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_SELECTED_DEVICE);
		assertTrue("The device selected dosen't match ", isDeviceSelected);

		// 2- Auto Start session
		boolean autoStart = CimbaPreferenceActivityUserSimulator
				.isAutoStartOn();
		assertEquals(
				BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_AUTO_CONNECT_ON_STARTUP,
				autoStart);

		// 3- Use Pre-filter preference
		boolean useFilter = CimbaPreferenceActivityUserSimulator
				.isUsePreFilterOn();
		assertEquals(
				BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_USE_PRE_FILTER,
				useFilter);

		// return to Main Activity
		getUiDevice().pressBack();
	}

	protected void mainActivityStateTest() throws UiObjectNotFoundException {
		// we are at Main activity and we going to Verify that the predefined
		// preference is taken the right effect
		// 1- verify when session start it connect to the right device defined
		// in the preference
		MainActivityUserSimulator.startNewSession(this);
		boolean isCoonectingToThatDevice = MainActivityUserSimulator
				.isConnectingTo(device);
		assertTrue(
				"Start New session does not connect to the correct device defined in the preferenceactivity",
				isCoonectingToThatDevice);

		// 2- Auto Start session
		boolean autoStart = CimbaPreferenceActivityUserSimulator
				.isAutoStartOn();
		assertEquals(
				BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_AUTO_CONNECT_ON_STARTUP,
				autoStart);

		// 3- Use Pre-filter preference
		boolean useFilter = CimbaPreferenceActivityUserSimulator
				.isUsePreFilterOn();
		assertEquals(
				BlackBoxCimbaEEG.PREDEFINED_APP_PREFERENCE.APP_PREF_USE_PRE_FILTER,
				useFilter);

		// return to Main Activity
		getUiDevice().pressBack();
	}

	protected void exploreSettingActivityTest()
			throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToSettingActivity(this);
		// we are at Setting activity and we going to explore all its preference
		// 1- Device list preference
		// 1.1 click device list
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// 1.2 cancel
		CimbaPreferenceActivityUserSimulator.cancelSelectedDevicePref(this);
		// 1.3 click device list
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// 1.4 pick any device if there is any

		// 2- Auto Start session
		// 2.1- check Auto Start session Box
		CimbaPreferenceActivityUserSimulator.checkAutoStart();
		// 2.2 verify Auto Start session Box is checked
		boolean autoStart = CimbaPreferenceActivityUserSimulator
				.isAutoStartOn();
		assertTrue("The autoStart wasn't checked", autoStart);
		// 2.3- re-check Auto Start session Box
		CimbaPreferenceActivityUserSimulator.uncheckAutoStart();
		// 2.3- verify Auto Start session Box Unchecked
		autoStart = CimbaPreferenceActivityUserSimulator.isAutoStartOn();
		assertFalse("The autoStart wasn't unchecked", autoStart);

		// 3- Use Pre-filter preference
		// 3.1- check Pre-filter Box
		CimbaPreferenceActivityUserSimulator.checkUsePreFilter();
		// 3.2- verify Pre-filter Box is checked
		boolean useFilter = CimbaPreferenceActivityUserSimulator
				.isUsePreFilterOn();
		assertTrue("Use Pre-Filter option isn't on", useFilter);
		// 3.3- re-check Pre-filter Box
		CimbaPreferenceActivityUserSimulator.uncheckUsePreFilter();
		// 3.3- verify Pre-filter Box Unchecked
		useFilter = CimbaPreferenceActivityUserSimulator.isUsePreFilterOn();
		assertFalse("Use Pre-Filter option wasn't unchecked", useFilter);

		getUiDevice().pressBack();
	}

	/**
	 * Connect to a device specified by in the runTest.sh script and passed by
	 * -e DEVICE $device
	 * 
	 * @throws UiObjectNotFoundException
	 */
	protected void selectAndConnectDeviceTest()
			throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToSettingActivity(this);
		// click list
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// pick a device in the list
		CimbaPreferenceActivityUserSimulator.selectDevice(device);
		// back to main Activity
		getUiDevice().pressBack();
		// start new session
		MainActivityUserSimulator.startNewSession(this);
	}

	/**
	 * Start session
	 * 
	 * @throws UiObjectNotFoundException
	 */
	protected void connectDeviceTest() throws UiObjectNotFoundException {
		// start new session
		MainActivityUserSimulator.startNewSession(this);
		// verify that the connecting dialog is shown
		assertTrue("No connecting dialog",
				MainActivityUserSimulator.isConnectingInProgress());
		// verify that the dialog message is indicating a progress connection to
		// the right device
		assertTrue("Not connecting to the right device",
				MainActivityUserSimulator.isConnectingTo(device));
	}

	protected void launchAboutTest() throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToAboutActivity(this);
		// go back to main activity
		getUiDevice().pressBack();
	}

	/**
	 * Do 2 test related to About activity
	 * 
	 * @throws UiObjectNotFoundException
	 */
	protected void exploreAboutActivity() throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToAboutActivity(this);
		// we are at About activity and we going to explore all the possible
		// tests
		// 1- Verify that what it's seen in About Activity match adb APK package
		// analyze
		// Ref: T0001
		boolean isDebuggableDisplay = AboutActivityUserSimulator
				.isBuildTypeDebuggable();
		assertEquals("AboutActivity is displaying false information "
				+ AboutActivityUserSimulator.BUILD_TYPE_TEXT
				+ (isDebuggable ? " is Debuggable" : "is Release"),
				isDebuggable, isDebuggableDisplay);
		// 2-Test that the application had properly setup the distribution SDK.
		// Ref: T0002
		String uuidDistrubutionSdk = isDebuggable ? BlackBoxCimbaEEG.DEBUGGABLE_DISTRIBUTION_SDK_APP_ID
				: BlackBoxCimbaEEG.RELEASE_DISTRIBUTION_SDK_APP_ID;
		String uuidDistrubutionSdkDisplay = AboutActivityUserSimulator
				.seeDistributionSdkId();
		assertEquals(
				"AboutActivity is displaying false information "
						+ AboutActivityUserSimulator.DISTRIBUTION_SDK_APP_ID_TEXT
						+ (isDebuggable ? BlackBoxCimbaEEG.DEBUGGABLE_DISTRIBUTION_SDK_APP_ID
								: BlackBoxCimbaEEG.RELEASE_DISTRIBUTION_SDK_APP_ID),
				uuidDistrubutionSdk, uuidDistrubutionSdkDisplay);

		getUiDevice().pressBack();
	}

	protected void nullDeviceTest() throws UiObjectNotFoundException {
		// click list
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// pick a device in the list
		CimbaPreferenceActivityUserSimulator
				.selectDevice(BlackBoxCimbaEEG.NULL_DEVICE);
		// back to main Activity
		getUiDevice().pressBack();
		// start new session
		MainActivityUserSimulator.startNewSession(this);
		// a. Check that a progress dialog appears.
		assertTrue("The progress is not shown",
				MainActivityUserSimulator.isConnectingInProgress());
		assertTrue(MainActivityUserSimulator
				.isConnectingTo(BlackBoxCimbaEEG.NULL_DEVICE));
		// b. check that the dialog has disappear
		sleep(BlackBoxCimbaEEG.NULL_DEVICE_TIMEOUT);
		assertFalse("the progress dialog didn't disappear after "
				+ BlackBoxCimbaEEG.NULL_DEVICE_TIMEOUT,
				MainActivityUserSimulator.isConnectingInProgress());
		// c. Check that the “New” button state ( or text ) changed back to
		// “New”
		boolean stopButtonFound = true;
		try {
			MainActivityUserSimulator.stopSession(this);
		} catch (Exception e) {
			// The Simulator couldn't simulate the click on the button stop
			stopButtonFound = false;
			e.printStackTrace();
		}
		assertFalse(
				"Check that the “New” button state ( or text ) changed back to “New”",
				stopButtonFound);

	}

}

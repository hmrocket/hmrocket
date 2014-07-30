/**
 * 
 */
package com.cimba.eeg.tests;

import android.os.RemoteException;

import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.cimba.eeg.activity.tests.AboutActivityUserSimulator;
import com.cimba.eeg.activity.tests.CimbaPreferenceActivityUserSimulator;
import com.cimba.eeg.activity.tests.MainActivityUserSimulator;
import com.cimba.eeg.tests.util.SystemUserSimulator;

/**
 * <p>
 * This a Black-Box UI testing of the Cimba-App
 * </p>
 * <p>
 * Credit: http://developer.android.com/tools/testing/testing_ui.html
 * </p>
 * <p>
 * Note: All tests (Method finishing with *test) should start from MainActivity
 * and Finish back there
 * </p>
 * 
 * @author mhamed
 * @since Jul 21, 2014
 */
public class CimbaEEGUiAutomatorTest extends UiAutomatorTestCase {

	private final static boolean D = true;

	private String device;
	private boolean isDebuggable;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		device = getParams().getString(BlackBoxCimbaEEG.EXTRA_DEVICE);
		isDebuggable = Boolean.parseBoolean(getParams().getString(
				BlackBoxCimbaEEG.EXTRA_DEBUGGABLE));
	}

	public void testUseCaseScenario() throws UiObjectNotFoundException,
			RemoteException {
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
			BlackBoxCimbaEEG.echoTestingMethod("Explore Setting Activity");
		exploreSettingActivityTest();
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("Explore Setting Activity");

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("Connect to specific device");
		connectDeviceTest();
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("Connect to specific device");

		if (D)
			BlackBoxCimbaEEG.echoTestingMethod("no Device Selected test");
		noDeviceSelectedTest();
		if (D)
			BlackBoxCimbaEEG.testMethodSuccessful("no Device Selected test");

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

			if (D)
				BlackBoxCimbaEEG.echoTestingMethod("Null Device functional");
			nullDeviceTest();
			if (D)
				BlackBoxCimbaEEG.testMethodSuccessful("Null Device functional");

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

	protected void exploreSettingActivityTest()
			throws UiObjectNotFoundException {
		MainActivityUserSimulator.goToSettingActivity(this);
		// we are at Setting activity and we going to explore all its preference
		// 1- Device list preference
		// 1.1 (no device is selected) click device list
		assertFalse("the device selection shoudln't have a selected device",
				CimbaPreferenceActivityUserSimulator.isDeviceSelected());
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// 1.2 cancel (still no device is selected)
		CimbaPreferenceActivityUserSimulator.cancelSelectedDevicePref(this);
		assertFalse("the device selection shoudln't have a selected device",
				CimbaPreferenceActivityUserSimulator.isDeviceSelected());
		// 1.3 click device list
		CimbaPreferenceActivityUserSimulator.clickSelectedDevicePref();
		// 1.4 pick any device if there is any
		CimbaPreferenceActivityUserSimulator.selectDevice(device);
		assertTrue("The device wasn't selected",
				CimbaPreferenceActivityUserSimulator.isDeviceSelected(device));
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
	}

	/**
	 * Connect to a device specified by in the runTest.sh script and passed by
	 * -e DEVICE $device
	 * <p>
	 * Setting activity must be the top activity at this point or the test will
	 * fail
	 * </p>
	 * 
	 * @throws UiObjectNotFoundException
	 */
	protected void connectDeviceTest() throws UiObjectNotFoundException {
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
	 * Test T0014, T015, T16
	 * <ul>
	 * <ol>
	 * 1- Assure that no device is selected
	 * </ol>
	 * <ol>
	 * 2- From MainActivity start to new session
	 * </ol>
	 * <ol>
	 * 3- Assure you are in PreferenceActivity and the list of available devices
	 * is open
	 * </ol>
	 * <ol>
	 * 4- close Device List and Assure that there is no device selected
	 * </ol>
	 * <ol>
	 * 5- return to MainActivity
	 * </ol>
	 * </ul>
	 * 
	 * @throws UiObjectNotFoundException
	 *             can be thrown by the system if a system object isn't found or
	 *             {@link BlackBoxCimbaEEG#APP_UNDER_TEST} isn't found
	 * @throws RemoteException
	 *             thrown by the system
	 */
	protected void noDeviceSelectedTest() throws RemoteException,
			UiObjectNotFoundException {
		// 1)
		SystemUserSimulator.clearAppData(this, BlackBoxCimbaEEG.APP_UNDER_TEST);
		SystemUserSimulator.launchApp(this, BlackBoxCimbaEEG.APP_UNDER_TEST);
		// 2)
		MainActivityUserSimulator.startNewSession(this);
		// 3)
		assertTrue(
				"With no device selected, the device selection list should automatically opens",
				CimbaPreferenceActivityUserSimulator.isSelectDeviceListOpen());
		// 4)
		CimbaPreferenceActivityUserSimulator.cancelSelectedDevicePref(this);
		assertFalse(
				"No device should be selected by default",
				CimbaPreferenceActivityUserSimulator.isDeviceSelected());
		// 5)
		CimbaPreferenceActivityUserSimulator.goToMainActivity(this);
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

/**
 * 
 */
package com.cimba.eeg.activity.tests;

import android.widget.CheckedTextView;
import android.widget.ListView;

import com.android.uiautomator.core.UiCollection;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;
import com.cimba.eeg.tests.BlackBoxCimbaEEG;

/**
 * Present all the possible user interaction in CimbaPreferenceActivity
 * 
 * @author Mhamed Hlioui
 * @since Jul 24, 2014
 */
public final class CimbaPreferenceActivityUserSimulator {

	public final static String SELECTED_DEVICE_TITLE = "Selected device";
	public static final String SELECT_A_DEVICE_LIST_TITLE = "Select a device";
	public final static String AUTO_START_SESSION_TITLE = "Auto start session";
	public final static String USE_PRE_FILTER_TITLE = "use pre filter";
	public final static String CANCEL = "Cancel";
	public final static int AUTO_START_SESSION_LAYOUT_INDEX = 3;
	public final static int USE_PRE_FILTER_LAYOUT_INDEX = 4;

	public static void checkUsePreFilter() throws UiObjectNotFoundException {
		UiCollection uiUseFilterLayout = new UiCollection(
				new UiSelector().index(USE_PRE_FILTER_LAYOUT_INDEX));
		if (uiUseFilterLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT Uicontainer not found");
		}
		UiObject uiUseFilterCheckBox = uiUseFilterLayout
				.getChild(new UiSelector().checkable(true));
		if (uiUseFilterCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT_CheckBox UiObject not found");
		}
		if (uiUseFilterCheckBox.isChecked()) {
			// Do nothing USE_PRE_FILTER option is already checked
		} else {
			uiUseFilterCheckBox.click();
		}
	}

	public static boolean isUsePreFilterOn() throws UiObjectNotFoundException {
		UiCollection uiUseFilterLayout = new UiCollection(
				new UiSelector().index(USE_PRE_FILTER_LAYOUT_INDEX));
		if (uiUseFilterLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT Uicontainer not found");
		}
		UiObject uiUseFilterCheckBox = uiUseFilterLayout
				.getChild(new UiSelector().checkable(true));
		if (uiUseFilterCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT_CheckBox UiObject not found");
		}
		return uiUseFilterCheckBox.isChecked();
	}

	public static void uncheckUsePreFilter() throws UiObjectNotFoundException {
		UiCollection uiUseFilterLayout = new UiCollection(
				new UiSelector().index(USE_PRE_FILTER_LAYOUT_INDEX));
		if (uiUseFilterLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT Uicontainer not found");
		}
		UiObject uiUseFilterCheckBox = uiUseFilterLayout
				.getChild(new UiSelector().checkable(true));
		if (uiUseFilterCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"USE_PRE_FILTER_LAYOUT_CheckBox UiObject not found");
		}
		if (uiUseFilterCheckBox.isChecked()) {
			uiUseFilterCheckBox.click();
		} else {
			// Do nothing USE_PRE_FILTER option is already unchecked
		}
	}

	public static void checkAutoStart() throws UiObjectNotFoundException {
		UiCollection uiAutoStartLayout = new UiCollection(
				new UiSelector().index(AUTO_START_SESSION_LAYOUT_INDEX));
		if (uiAutoStartLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_LAYOUT Uicontainer not found");
		}
		UiObject uiAutoStartCheckBox = uiAutoStartLayout
				.getChild(new UiSelector().checkable(true));
		if (uiAutoStartCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_CheckBox UiObject not found");
		}
		if (uiAutoStartCheckBox.isChecked()) {
			// Do nothing Auto Start option is already checked
		} else {
			uiAutoStartCheckBox.click();
		}
	}

	public static boolean isAutoStartOn() throws UiObjectNotFoundException {
		UiCollection uiAutoStartLayout = new UiCollection(
				new UiSelector().index(AUTO_START_SESSION_LAYOUT_INDEX));
		if (uiAutoStartLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_LAYOUT Uicontainer not found");
		}
		UiObject uiAutoStartCheckBox = uiAutoStartLayout
				.getChild(new UiSelector().checkable(true));
		if (uiAutoStartCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_CheckBox UiObject not found");
		}
		return uiAutoStartCheckBox.isChecked();
	}

	public static void uncheckAutoStart() throws UiObjectNotFoundException {
		UiCollection uiAutoStartLayout = new UiCollection(
				new UiSelector().index(AUTO_START_SESSION_LAYOUT_INDEX));
		if (uiAutoStartLayout.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_LAYOUT Uicontainer not found");
		}
		UiObject uiAutoStartCheckBox = uiAutoStartLayout
				.getChild(new UiSelector().checkable(true));
		if (uiAutoStartCheckBox.exists() == false) {
			throw new UiObjectNotFoundException(
					"AUTO_START_SESSION_CheckBox UiObject not found");
		}
		if (uiAutoStartCheckBox.isChecked()) {
			uiAutoStartCheckBox.click();
		} else {
			// Do nothing Auto Start option is already unchecked

		}
	}

	/**
	 * <p>
	 * Simulate the Selection of a device.
	 * </p>
	 * <p>
	 * Note: The Select a Device List Preference must be clicked at this point
	 * </p>
	 * 
	 * @param device
	 * @throws UiObjectNotFoundException
	 *             if the device dosen't exist or the Select a Device List not
	 *             shown
	 */
	public static void selectDevice(String device)
			throws UiObjectNotFoundException {
		UiObject uiDevice = new UiObject(new UiSelector().textContains(device));
		if (uiDevice.exists())
			uiDevice.click();
		else {
			// The device might be hidden in the bottom or top of List
			// Scroll the list to find it
			UiScrollable uiDeviceList = new UiScrollable(
					new UiSelector().scrollable(true));
			if (uiDeviceList.exists() == false)
				throw new UiObjectNotFoundException("Couldn't find The Device "
						+ device + " in the list");
			else {
				uiDeviceList.scrollIntoView(uiDevice);
				uiDevice.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
			}
		}
	}

	/**
	 * @param device
	 *            The Bluetooth hardware Device Address (BD_ADDR)
	 * @return True if Selected_Device_Preference Summary contains
	 *         <code>device</code>>, false otherwise
	 */
	public static boolean isDeviceSelected(String device) {
		UiObject uiSelectedDeviceSummary = new UiObject(
				new UiSelector().text(device));
		return uiSelectedDeviceSummary.exists();
	}

	/**
	 * @return true if there is a device selected, false otherwise
	 * @throws UiObjectNotFoundException
	 *             thrown if CimbaPreferenceActivity not in front task
	 */
	public static boolean isDeviceSelected() throws UiObjectNotFoundException {
		clickSelectedDevicePref();
		UiObject uiDeviceSelected = new UiObject(new UiSelector().className(
				CheckedTextView.class).checked(true));
		if (uiDeviceSelected.exists()) {
			// there is a device selected
			return true;
		}
		UiScrollable listDevice = new UiScrollable(new UiSelector().className(
				ListView.class).scrollable(true));

		try {
			// scroll to uiDeviceSelected or throw an exception
			listDevice.scrollIntoView(uiDeviceSelected);
			return true;
		} catch (Exception e) {
			// there is no device selected
			return false;
		}
	}

	/**
	 * @return true if the Select a Device List is open
	 */
	public static boolean isSelectDeviceListOpen() {
		UiObject selectDeviceListTitle = new UiObject(
				new UiSelector().text(SELECT_A_DEVICE_LIST_TITLE));
		return selectDeviceListTitle.exists();
	}

	/**
	 * 
	 * 
	 * @throws UiObjectNotFoundException
	 */
	public static void clickSelectedDevicePref()
			throws UiObjectNotFoundException {
		UiObject selectedDevicePref = new UiObject(
				new UiSelector().text(SELECTED_DEVICE_TITLE));
		if (selectedDevicePref.exists())
			selectedDevicePref
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else
			throw new UiObjectNotFoundException(
					"Couldn't find selected Device Preference");
	}

	/**
	 * <p>
	 * Note: The Select a Device List Preference must be clicked at this point
	 * </p>
	 * 
	 * @param uiAutomatorTC
	 * @throws UiObjectNotFoundException
	 */
	public static void cancelSelectedDevicePref(
			UiAutomatorTestCase uiAutomatorTC) throws UiObjectNotFoundException {
		UiObject selectedDevicePrefCancel = new UiObject(
				new UiSelector().text(CANCEL));
		if (selectedDevicePrefCancel.exists())
			selectedDevicePrefCancel
					.clickAndWaitForNewWindow(BlackBoxCimbaEEG.MEDIUM_TIME_OUT);
		else
			uiAutomatorTC.getUiDevice().pressBack();
	}

	public static void goToMainActivity(UiAutomatorTestCase uiAutomatorTest) {
		uiAutomatorTest.getUiDevice().pressBack();

	}

}

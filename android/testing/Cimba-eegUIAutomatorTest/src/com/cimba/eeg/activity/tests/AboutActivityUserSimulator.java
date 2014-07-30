/**
 * 
 */
package com.cimba.eeg.activity.tests;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;

/**
 * Present all the possible user interaction in AboutActivity
 * 
 * @author Mhamed Hlioui
 * @since Jul 24, 2014
 */
public final class AboutActivityUserSimulator {

	/*
	 * **************
	 * Displayed info to the user
	 */
	public static final String BUILD_TYPE_TEXT = "Build Type";
	private static final String BUILD_TYPE_DEBUGGABLE = "Debuggable build";
	public static final String BUILD_VERSION_TEXT = "Build Version";
	public static final String USING_DISTRIBUTION_SDK_TEXT = "Using Distribution SDK";
	public static final String DISTRIBUTION_SDK_APP_ID_TEXT = "Distribution SDK App Id";

	public static boolean isBuildTypeDebuggable() {
		UiObject buildTypeValue = new UiObject(
				new UiSelector().text(BUILD_TYPE_DEBUGGABLE));
		return buildTypeValue.exists();
	}

	public static String seeBuildVersion() throws UiObjectNotFoundException {
		UiObject buildVersionValue = new UiObject(new UiSelector().index(3));
		return buildVersionValue.getText();
	}

	public static boolean UsingDistributionSdk() {
		UiObject usingDistributionSdkValue = new UiObject(
				new UiSelector().text("Yes"));
		return usingDistributionSdkValue.exists();
	}

	public static String seeDistributionSdkId()
			throws UiObjectNotFoundException {
		UiObject distributionSdkId = new UiObject(new UiSelector().index(7));
		// uuid : cbedb145-2879-419f-b72b-e608ea2e1383
		String id = distributionSdkId.getText();
		// remove 'uuid : ' (7 char)
		return id.substring(7, id.length());
	}
}

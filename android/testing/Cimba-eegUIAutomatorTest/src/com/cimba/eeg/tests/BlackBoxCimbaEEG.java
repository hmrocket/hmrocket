/**
 * 
 */
package com.cimba.eeg.tests;

/**
 * This class contains all the hard coded values (mostly are known by the user)
 * <p>
 * Please do not change these variable without making change on
 * project/runTests.sh
 * </p>
 * 
 * @author Mhamed Hlioui
 * @since Jul 22, 2014
 */
public final class BlackBoxCimbaEEG {

	public static final long MEDIUM_TIME_OUT = 500;
	public static final String APP_UNDER_TEST = "Cimba EEG";
	public static final String MENU_OVERFLOW_DESCRIPTION = "More options";
	/**
	 * Note 1 In case you changed this variable value Don't omit to make the
	 * same changes to runTests.sh file
	 */
	public static final String EXTRA_DEVICE = "DEVICE";
	/**
	 * Note 1 In case you changed this variable value Don't omit to make the
	 * same changes to runTests.sh file
	 */
	public static final String EXTRA_DEBUGGABLE = "DEBUGGABLE";
	/**
	 * Note 2 In case you changed DISTRIBUTION_SDK_APP_ID value in the
	 * application under test Don't omit to make the same changes to this
	 * variable value
	 */
	public static final String DEBUGGABLE_DISTRIBUTION_SDK_APP_ID = "cbedb145-2879-419f-b72b-e608ea2e1383";
	/**
	 * Note 2 In case you changed DISTRIBUTION_SDK_APP_ID value in the
	 * application under test Don't omit to make the same changes to this
	 * variable value
	 */
	public static final String RELEASE_DISTRIBUTION_SDK_APP_ID = "cbedb145-2879-419f-b72b-e608ea2e1383";
	public static final String NULL_DEVICE = "Null Device";
	public static final long NULL_DEVICE_TIMEOUT = 10000;

	private BlackBoxCimbaEEG() {
		// Forbid the initialization of this class
	}

	public static final void testMethodSuccessful(String testMethod) {
		System.out.println(" ************************\n");
		System.out.println(testMethod + " test Success \n");
		System.out.println(" ************************");
	}

	public static final void echoTestingMethod(String testMethod) {
		System.out.println(" ************************\n");
		System.out.println(testMethod + " test \n");
		System.out.println(" ************************");
	}

	/**
	 * This is the predefined preference for
	 * {@link CimbaEEGUiAutomatorTest_WithPrefs}. This class should concord
	 * exactly with the xml file located in /shared_prefs folder
	 * 
	 * @author Mhamed Hlioui
	 * @since Jul 28, 2014
	 */
	public static final class PREDEFINED_APP_PREFERENCE {
		public static final String APP_PREF_SELECTED_DEVICE = "00:07:80:5F:E9:C8";
		public static final boolean APP_PREF_USE_PRE_FILTER = true;
		public static final boolean APP_PREF_AUTO_CONNECT_ON_STARTUP = false;
	}

}

#!/bin/bash

#Test requirement: 
#                   1- Device under test must be set to English language
#                   2- Application version should be $TestVersion
TestVersion=0.7.0

developper=`whoami`
if [ developper==mhamed ] 
then
#Default variable for Mhamed-Mac environment
TARGET_ANDROID_ID=9
Device=TPS00301
else
#Default variable for Nico-Mac environment
#use `android list targets` to pick a TARGET_ANDROID_ID
TARGET_ANDROID_ID=9
Device=TPS00301
fi
#other variable passed from adb to the test
Debuggable=false

while getopts t:d: option
do
case "${option}"
in
t) TARGET_ANDROID_ID=${OPTARG};;
d) Device=${OPTARG};;
esac
done


android create uitest-project -n Cimba-eegUiAutomatorTest -t $TARGET_ANDROID_ID -p .
ant build
adb push bin/Cimba-eegUiAutomatorTest.jar /data/local/tmp/
if [ ! -f ../../bin/Cimba-EEG.apk ]
then
#../../buildCimba-EEG.sh
echo  "buildCimba-EEG.sh not finished yet"
else
adb uninstall com.cimba.eeg
adb install ../../bin/Cimba-EEG.apk
fi
#is APK signed debuggable ?
pkgFlags=`adb shell dumpsys | grep -A16 "Package \[com.cimba.eeg\]" | grep pkgFlags`
if [ $pkgFlags==**DEBUGGABLE** ]
then
Debuggable=true
fi
#does app version match the test version
AppVersion=`adb shell dumpsys | grep -A16 "Package \[com.cimba.eeg\]" | grep versionName`
if [ ! $AppVersion==**$TestVersion** ]
then
echo "AppVersion and TestVersion dosen't match"
fi
adb shell uiautomator runtest Cimba-eegUiAutomatorTest.jar -c com.cimba.eeg.tests.CimbaEEGUiAutomatorTest -e DEVICE $Device -e DEBUGGABLE $Debuggable
#run tests with pre defined preferences
adb uninstall com.cimba.eeg
adb install ../../bin/Cimba-EEG.apk
adb push shared_prefs /data/data/com.cimba.eeg
adb shell uiautomator runtest Cimba-eegUiAutomatorTest.jar -c com.cimba.eeg.tests.CimbaEEGUiAutomatorTest_WithPrefs -e DEVICE $Device -e DEBUGGABLE $Debuggable
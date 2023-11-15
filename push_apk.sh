gradle clean assembleServerDebug assembleServerDebugAndroidTest
adb install app/build/outputs/apk/androidTest/server/debug/appium-uiautomator2-server-debug-androidTest.apk
adb install app/build/outputs/apk/server/debug/appium-uiautomator2-server-v5.12.16.apk

source ~/Tools/miniconda3/etc/profile.d/conda.sh
conda activate Test-Translation
python /Users/ruizhengu/Projects/Test-Translation/Appium-Playground/Appium-Python/test.py

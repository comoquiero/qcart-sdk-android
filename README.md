## Build the APK:
```
./gradlew assembleDebug -PsdkMode=local
./gradlew assembleDebug -PsdkMode=maven
./gradlew assembleDebug -PsdkMode=jitpack
```

## Start emulator (optional)
```
emulator -avd Small_Phone_API_30_X86
```

## Install the APK on your connected device or emulator:
```
adb install -r app/build/outputs/apk/debug/app-debug.apk
```

## Show logs in the console
```
adb logcat | grep qcart
```

## Start the app with the deeplink
```
adb shell am start -a android.intent.action.VIEW -d "https://test.abc?sku=111&sku=222" com.example.qcarttestapp
```
# BiometricPromptDemo
Android fingerprint, Api23 and Api28 are supported

## Authentication
NB: Root access is required

Add your pin sequence in the `authenticate()` function in [`MainActivity.java`](https://github.com/fbiego/BiometricPromptDemo/blob/master/app/src/main/java/gaoyang/biometricdemo/MainActivity.java)

This works by injecting events which requires root access for the app to execute shell commands. More info [`here`](https://stackoverflow.com/questions/7789826/adb-shell-input-events)

You may also need to implement this in a `Service ` to make it run in the background




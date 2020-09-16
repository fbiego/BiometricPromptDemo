package gaoyang.biometricdemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nestia.biometriclib.BiometricPromptManager;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private Button mButton;
    private BiometricPromptManager mManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text_view);
        mButton = findViewById(R.id.button);

        mManager = BiometricPromptManager.from(this);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SDK version is "+ Build.VERSION.SDK_INT);
        stringBuilder.append("\n");
        stringBuilder.append("isHardwareDetected : "+mManager.isHardwareDetected());
        stringBuilder.append("\n");
        stringBuilder.append("hasEnrolledFingerprints : "+mManager.hasEnrolledFingerprints());
        stringBuilder.append("\n");
        stringBuilder.append("isKeyguardSecure : "+mManager.isKeyguardSecure());
        stringBuilder.append("\n");

        mTextView.setText(stringBuilder.toString());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mManager.isBiometricPromptEnable()) {
                    mManager.authenticate(new BiometricPromptManager.OnBiometricIdentifyCallback() {
                        @Override
                        public void onUsePassword() {
                            Toast.makeText(MainActivity.this, "onUsePassword", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onSucceeded() {

                            Toast.makeText(MainActivity.this, "onSucceeded", Toast.LENGTH_SHORT).show();
                            
                            authenticate();     // perform authentication 
                        }

                        @Override
                        public void onFailed() {

                            Toast.makeText(MainActivity.this, "onFailed", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(int code, String reason) {

                            Toast.makeText(MainActivity.this, "onError", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancel() {

                            Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    
    private void authenticate(){
        
        // root access required
        
        try {
            long timeout = 250L;
            Process p1 = Runtime.getRuntime().exec("su -c input tap 210 888");      // perform a tap event on  x, y position
            TimeUnit.MILLISECONDS.sleep(timeout);                                   // pause before next command
            Process p2 = Runtime.getRuntime().exec("su -c input keyevent 25");      // simulate a key press, refer here https://stackoverflow.com/questions/7789826/adb-shell-input-events
            TimeUnit.MILLISECONDS.sleep(timeout);
            
        } catch (IOException | InterruptedException e){
            //TODO;
        }
        
    }
        
}

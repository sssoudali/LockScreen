package com.aions.lockscreen;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.snackbar.Snackbar;
import android.app.ActivityManager;


public class MainActivity extends AppCompatActivity {

    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;
    private RelativeLayout RelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize DevicePolicyManager and ComponentName
        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, MyAdminReceiver.class);
        RelativeLayout =  findViewById(R.id.RelativeLayout);
        lockScreen();

    }

    private void lockScreen() {
        // Implement screen locking logic here
        boolean isAdminActive = mDevicePolicyManager.isAdminActive(mComponentName);
        try {

            if (isAdminActive) {
                mDevicePolicyManager.lockNow();
            } else {
                // Request device admin permission
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Please enable device admin permission to lock the screen.");
                startActivity(intent);

            }
        }
        catch (SecurityException e)
        {AdminIntent();
            Log.d("SecurityExceptionnnn",e.toString());//print(e.toString());
           Snackbar.make(RelativeLayout,"Error:"+e.toString(),Snackbar.LENGTH_LONG).show();
        }



    }
    public void AdminIntent()
    {
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
        intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Please enable device admin permission to lock the screen.");
        startActivity(intent);
    }
}
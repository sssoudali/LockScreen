package com.aions.lockscreen;
import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
public class MyAdminReceiver extends DeviceAdminReceiver{

    @Override
    public void onEnabled( Context context,  Intent intent) {
        Toast.makeText(context, "Device Admin : enabled", Toast.LENGTH_SHORT).show();
        super.onEnabled(context, intent);
    }

}

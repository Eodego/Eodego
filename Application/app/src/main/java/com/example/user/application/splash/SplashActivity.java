package com.example.user.application.splash;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.user.application.datamanager.DataManager;
import com.example.user.application.mainacvitity.MainActivity;

/**
 * Created by user on 15. 8. 23.
 */
public class SplashActivity extends Activity {
    private DataManager data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            data = DataManager.getInstance();
            data.executeThread();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}

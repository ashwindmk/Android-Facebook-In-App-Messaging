package com.example.ashwin.facebookinappmessaging;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.notifications.NotificationCardResult;
import com.facebook.notifications.NotificationsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);

        NotificationsManager.presentCardFromNotification(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);
        NotificationsManager.presentCardFromNotification(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        NotificationCardResult result = NotificationsManager.handleActivityResult(requestCode, resultCode, data);

        if (result != null && !result.equals("null")) {
            Toast.makeText(this, "Result: " + result.getActionUri(), Toast.LENGTH_LONG).show();
        }
    }

    public void test1Clicked(View view) {
        AppEventsLogger logger = AppEventsLogger.newLogger(this);
        logger.logEvent("Test 1 button clicked");
    }
}

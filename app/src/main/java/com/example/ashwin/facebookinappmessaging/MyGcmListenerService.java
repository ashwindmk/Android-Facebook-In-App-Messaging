package com.example.ashwin.facebookinappmessaging;

/**
 * Created by ashwin on 20/10/16.
 */

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.notifications.NotificationsManager;
import com.google.android.gms.gcm.GcmListenerService;

public class MyGcmListenerService extends GcmListenerService {

    private static final String TAG = "MyGcmListenerService";

    /**
     * Called when message is received.
     *
     * @param from SenderID of the sender.
     * @param data Data bundle containing message data as key/value pairs.
     *             For Set of keys use data.keySet().
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(String from, Bundle data) {

        Log.v(TAG, "onMessageReceived(" + from + ", " + data + ")");

        // [START_EXCLUDE]
        /**
         * Production applications would usually process the message here.
         * Eg: - Syncing with server.
         *     - Store message in local database.
         *     - Update UI.
         */

        /**
         * In some cases it may be useful to show a notification indicating to the user
         * that a message was received.
         */

        if (NotificationsManager.canPresentCard(data)) {
            NotificationsManager.presentNotification(
                    this,
                    data,
                    new Intent(getApplicationContext(), MainActivity.class)
            );
        }

        // [END_EXCLUDE]
    }
    // [END receive_message]
}
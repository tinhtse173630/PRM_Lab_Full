package com.exercise.lab8;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID = "notification_channel";   // Define the channel ID for notifications

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Create notification channel
        createNotificationChannel();

        Button sendNotification = findViewById(R.id.SendNoti);
        // Set click listener for the button
        sendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification(); // Call method to send a notification
            }
        });
    }

    // *  As of Target Android 8.0 (API level 26) or above ,post a notification without specifying a notification channel, the notification doesn't appear and the system logs an error
    // Details From : https://developer.android.com/develop/ui/views/notifications/channels#java
    // Method to create a notification channel
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // Check if the Android version is Oreo or above ( O mean ver 26 )
            CharSequence name = "Channel name"; // Name of the channel
            String description = "Channel description"; // Description of the channel
            int importance = NotificationManager.IMPORTANCE_DEFAULT; // Importance level
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance); // Create a new notification channel
            channel.setDescription(description); // Set the channel description

            // Get the NotificationManager and create the channel
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    // Method to send a notification
    private void sendNotification() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher); // Decode the bitmap image for the large icon
        // Build the notification using NotificationCompat
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Title Push Notification")
                .setContentText("Message Push Notification")
                .setSmallIcon(R.drawable.ic_notification_custom)
                .setLargeIcon(bitmap)
                .setColor(getResources().getColor(R.color.colorAccent))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        // Get the NotificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) { // Check if notificationManager is not null
            notificationManager.notify(getNotificationId(), builder.build()); // Notify with a unique ID
        }
    }

    // Method to generate a unique notification ID
    private int getNotificationId() {
        return (int) new Date().getTime();// Use current time in milliseconds as ID
    }
}
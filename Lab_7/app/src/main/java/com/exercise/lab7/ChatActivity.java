package com.exercise.lab7;

import android.Manifest;
import android.app.SearchManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;

public class ChatActivity extends AppCompatActivity {

    ImageView imgGoogle, imgCall, imgMessage;

    // Define request codes for permissions
    private static final int REQUEST_CALL_PHONE = 1;
    private static final int REQUEST_SEND_SMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Set the layout for the chat activity
        setContentView(R.layout.activity_chat);

        // Initialize ImageView components
        imgGoogle = findViewById(R.id.imgGoogle);
        imgCall = findViewById(R.id.imgCall);
        imgMessage = findViewById(R.id.imgMessage);

        // Set click listener to initiate a phone call when imgCall is clicked
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall("0898100471");
            }
        });

        // Set click listener to send an SMS when imgMessage is clicked
        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("0898100471");
            }
        });

        // Set click listener to open Google Search when imgGoogle is clicked
        imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleSearch("Your search query here");
            }
        });
    }

    // Method to initiate a phone call, requesting permission if necessary
    private void makePhoneCall(String phoneNumber) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Start the call directly if the Android version is below Marshmallow
            startCallIntent(phoneNumber);
            return;
        }

        // Check for CALL_PHONE permission
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PHONE);
        } else {
            // Permission already granted, initiate the call
            startCallIntent(phoneNumber);
        }
    }

    // Method to initiate an SMS, requesting permission if necessary
    private void sendMessage(String phoneNumber) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            // Start the SMS directly if the Android version is below Marshmallow
            startSmsIntent(phoneNumber);
            return;
        }

        // Check for SEND_SMS permission
        if (checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Request permission if not granted
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    REQUEST_SEND_SMS);
        } else {
            // Permission already granted, initiate the SMS
            startSmsIntent(phoneNumber);
        }
    }

    // Helper method to start an SMS intent
    private void startSmsIntent(String phoneNumber) {
        Intent messageIntent = new Intent(Intent.ACTION_SENDTO);
        messageIntent.setData(Uri.parse("smsto:" + phoneNumber));
        messageIntent.putExtra("sms_body", "Your message here");
        startActivity(messageIntent);
    }

    // Helper method to start a phone call intent
    private void startCallIntent(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    // Method to open Google Search with a query
    private void openGoogleSearch(String query) {
        Intent searchIntent = new Intent(Intent.ACTION_WEB_SEARCH);
        searchIntent.putExtra(SearchManager.QUERY, query);
        startActivity(searchIntent);
    }

    // Handle the result of permission requests
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                makePhoneCall("0898100471");
            } else {
                // Permission denied, show a message
                Toast.makeText(this, "Permission Denied for Call", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, send the message
                sendMessage("0898100471");
            } else {
                // Permission denied, show a message
                Toast.makeText(this, "Permission Denied for SMS", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
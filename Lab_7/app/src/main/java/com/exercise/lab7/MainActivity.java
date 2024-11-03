package com.exercise.lab7;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
 private  static  final int REQUEST_PERMISSION_CODE = 10;
 Button btn_requestPermission , btn_openSetting;
 ImageButton btn_sms;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        btn_openSetting = findViewById(R.id.btn_open_settings_permission);
        btn_requestPermission = findViewById(R.id.btn_request_permission);
        btn_sms = findViewById(R.id.button_sms);

         // Openn Sms Activity
        btn_sms.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, ChatActivity.class)) );



        // Request the permissions location system
        btn_requestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClickRequestPermission();     // Call method to request permission when the button is clicked
            }
        });

     // Show settings to allow manually change On/Off the permissions
     btn_openSetting.setOnClickListener(v -> {
         // Open the app's settings so the user can manually change the permissions
         Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS); // Create an intent to open the app's settings screen
         Uri uri = Uri.fromParts("package", getPackageName(), null); // Get the app's package name and create a URI object for it
         intent.setData(uri); // Set the URI as data for the intent (points to this app's settings page)
         startActivity(intent); // Start the activity to display
     });

    }
    private void ClickRequestPermission() {
       if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){   // If the Android version is lower than Marshmallow, no need to check permissions
           return ; // Exit the method
       }
       // version > 6
        // For Android versions Marshmallow (6.0) and higher
        // Check if the permission is already granted
        if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission Already Granted", Toast.LENGTH_SHORT).show();
        }else{ // If not granted, request the location permission
            String permissions = (Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(new String[]{permissions}, REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Check if the result is for the location permission request
       if(requestCode == REQUEST_PERMISSION_CODE){
           if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){ // If permission is granted, show a success message
               Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
           }else{  // If permission is denied, show a failure message
               Toast.makeText(this, "Perrmission Denied", Toast.LENGTH_SHORT).show();
           }
       }
    }
}
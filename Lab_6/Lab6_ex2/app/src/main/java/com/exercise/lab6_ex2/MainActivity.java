package com.exercise.lab6_ex2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnPopupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPopupMenu = findViewById(R.id.btnPopupMenu); // Initialize the button for showing the popup menu
        btnPopupMenu.setOnClickListener(v -> showPopupMenu()); // Set an onClick listener for the button to trigger the popup menu
    }

 // Method to show a popup menu when the button is clicked
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, btnPopupMenu); // Create a PopupMenu object, with the button as its anchor view
        popupMenu.getMenuInflater().inflate(R.menu.menu_popup, popupMenu.getMenu()); // Inflate the popup menu from XML (R.menu.menu_popup)
        popupMenu.show();  // Display the popup menu
    } 
}
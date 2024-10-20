package com.exercise.lab6_ex3;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MainActivity extends AppCompatActivity {
    Button btnChangeBackgroundColor;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = findViewById(R.id.constraintLayoutMain); // Initialize the layout (ConstraintLayout in XML)
        btnChangeBackgroundColor = findViewById(R.id.btnChangeBackgroundColor); / Initialize the button that triggers background color change
        registerForContextMenu(btnChangeBackgroundColor); // Register the button for a context menu (activated via long press)
    }

    // Inflate the context menu when the user long presses the button
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    // Handle item selection from the context menu
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        changeBackgroundColor(item.getItemId());
        return super.onContextItemSelected(item); // Ensure default handling occurs
    }

    // Change background color based on menu item selected
    private void changeBackgroundColor(int menuItemId) {
        if (menuItemId == R.id.menuItemRed) {
            layout.setBackgroundColor(getResources().getColor(R.color.red));
        } else if (menuItemId == R.id.menuItemBlue) {
            layout.setBackgroundColor(getResources().getColor(R.color.blue));
        } else if (menuItemId == R.id.menuItemYellow) {
            layout.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }
}
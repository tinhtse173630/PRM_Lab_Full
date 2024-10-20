package com.exercise.lab6_ex1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Inflate the options menu from XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Handle selection of items from the options menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuShare) {
            Toast.makeText(this, "Bạn Đã chọn Item [" + item.getTitle().toString()+ "]", Toast.LENGTH_SHORT).show();
        } else
            if (item.getItemId() == R.id.menuSearch) {
                Toast.makeText(this, "Bạn Đã chọn Item [" + item.getTitle().toString()+ "]", Toast.LENGTH_SHORT).show();
        } else
            if (item.getItemId() == R.id.menuContent) {
                Toast.makeText(this, "Bạn Đã chọn Item [" + item.getTitle().toString()+ "]", Toast.LENGTH_SHORT).show();
        } else
            if (item.getItemId() == R.id.menuEmail) {
                Toast.makeText(this, "Bạn Đã chọn Item [" + item.getTitle().toString()+ "]", Toast.LENGTH_SHORT).show();
        } else
            if (item.getItemId() == R.id.menuPhone) {
                Toast.makeText(this, "Bạn Đã chọn Item [" + item.getTitle().toString()+ "]", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}
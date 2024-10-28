package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    //khai bao
    Button clk1,clk2,clk3,clk4;
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

            //anh xa
            clk1 = findViewById(R.id.clk1);
            clk2 = findViewById(R.id.clk2);
            clk3 = findViewById(R.id.clk3);
            clk4 = findViewById(R.id.clk4);

            //code
            // chuyen activity
            clk1.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, Activity1.class);
                startActivity(intent);
            });
            clk2.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                startActivity(intent);
            });
            clk3.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, Activity3.class);
                startActivity(intent);
            });
            clk4.setOnClickListener(view -> {
                Intent intent = new Intent(MainActivity.this, Activity4.class);
                startActivity(intent);
            });

        }
}
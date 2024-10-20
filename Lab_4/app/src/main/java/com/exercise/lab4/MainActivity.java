package com.exercise.lab4;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static String orderedFood = null;
    private static String orderedDrink = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize the buttons and text views from the layout.

        Button btnOrderFood = findViewById(R.id.btnOrderFood);
        Button btnOrderDrink = findViewById(R.id.btnOrderDrink);
        Button btnQuit = findViewById(R.id.btnQuit);
        TextView tvOrderedFood = findViewById(R.id.tvOrderedFood);
//        TextView tvOrderedDrink = findViewById(R.id.tvOrderedDrink);
        // Retrieve ordered food and drink from the intent, if available.

        String newOrderedFood = getIntent().getStringExtra("orderedFood");
        String newOrderedDrink = getIntent().getStringExtra("orderedDrink");
        // Update the static variables with new orders if they are present.

        if (newOrderedFood != null) {
            orderedFood = newOrderedFood;
        }
        // Update the static variables with new drink orders if they are present.
        if (newOrderedDrink != null) {
            orderedDrink = newOrderedDrink;
        }

        // Display the ordered drink, or show a default message if no order exists.
//        if (orderedDrink != null) {
//            tvOrderedDrink.setText("Đồ uống đã chọn: " + orderedDrink);
//        } else {
//            tvOrderedDrink.setText("Đồ uống đã chọn: Chưa có");
//        }

        // Display the ordered food and drink in the format "đồ ăn - đồ uống"
        if (orderedFood != null && orderedDrink != null) {
            // Nếu cả món ăn và đồ uống đều đã chọn
            tvOrderedFood.setText(orderedFood + " - " + orderedDrink);
        } else if (orderedFood != null) {
            // Nếu chỉ có món ăn đã chọn
            tvOrderedFood.setText(orderedFood + " - Đồ uống chưa có");
        } else if (orderedDrink != null) {
            // Nếu chỉ có đồ uống đã chọn
            tvOrderedFood.setText("Món ăn chưa có - " + orderedDrink);
        } else {
            // Nếu cả món ăn và đồ uống chưa được chọn
            tvOrderedFood.setText("Món ăn và đồ uống chưa có");
        }

        // Set a click listener to open the FoodActivity when the "Order Food" button is clicked.

        btnOrderFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(intent);
        });
        // Set a click listener to open the DrinkActivity when the "Order Drink" button is clicked.

        btnOrderDrink.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DrinkActivity.class);
            startActivity(intent);
        });
        // Set a click listener to show a confirmation dialog when the "Quit" button is clicked.
        btnQuit.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Thoát")
                    .setMessage("Bạn có chắc chắn muốn thoát ứng dụng?")
                    .setPositiveButton("Thoát", (d, w) -> {
                        // Close the app if "Quit" is confirmed.
                        finishAffinity();
                        System.exit(0);
                    })
                    .setNegativeButton("Huỷ", null)
                    .show();
        });
    }
}
package com.exercise.lab4;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        // Initialize the radio group and order button from the layout.

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnOrderFood = findViewById(R.id.btnOrderFood);
        // Create a list of foods and dynamically add them as radio buttons to the radio group.

        List<String> foodList = new ArrayList<>();
        foodList.add("Phở Hà Nội");
        foodList.add("Bún Bò Huế");
        foodList.add("Mì Quảng");
        foodList.add("Hủ Tíu Sài Gòn");
        // Loop through the food list and add each food as a radio button with specific styling.

        foodList.forEach(food -> {
            RadioButton radioButton = new RadioButton(FoodActivity.this);
            radioButton.setText(food);
            radioButton.setPadding(12, 0, 0, 0);
            radioButton.setTextSize(18);
            radioButton.setTextColor(Color.parseColor("#4A4A4A"));
            radioButton.setTextAlignment(RadioButton.TEXT_ALIGNMENT_CENTER);
            radioGroup.addView(radioButton);
        });
        // Set a click listener on the order button to handle food selection.

        btnOrderFood.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) {         // Check if no food has been selected.

                Toast.makeText(FoodActivity.this, "Bạn chưa chọn món ăn nào.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Get the selected food and pass it to MainActivity.

            RadioButton radioButton = findViewById(selectedId);
            String food = radioButton.getText().toString();
            Intent intent = new Intent(FoodActivity.this, MainActivity.class);
            intent.putExtra("orderedFood", food);
            startActivity(intent);
        });
    }
}
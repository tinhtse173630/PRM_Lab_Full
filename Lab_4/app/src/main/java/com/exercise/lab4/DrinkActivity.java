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

public class DrinkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

// Initialize the radio group and order button from the layout.
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button btnOrderDrink = findViewById(R.id.btnOrderDrink);

        // Create a list of drinks and dynamically add them as radio buttons to the radio group.
        List<String> drinkList = new ArrayList<>();
        drinkList.add("Pepsi");
        drinkList.add("Heineken");
        drinkList.add("Tiger");
        drinkList.add("Sài gòn Đỏ");

        // Loop through the drink list and add each drink as a radio button with specific styling.
        drinkList.forEach(drink -> {
            RadioButton radioButton = new RadioButton(DrinkActivity.this);
            radioButton.setText(drink);
            radioButton.setPadding(12, 0, 0, 0);
            radioButton.setTextSize(18);
            radioButton.setTextColor(Color.parseColor("#4A4A4A"));
            radioButton.setTextAlignment(RadioButton.TEXT_ALIGNMENT_CENTER);
            radioGroup.addView(radioButton);
        });

        // Set a click listener on the order button to handle drink selection.
        btnOrderDrink.setOnClickListener(v -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            if (selectedId == -1) { // Check if no drink has been selected.
                Toast.makeText(DrinkActivity.this, "Bạn chưa chọn đồ uống nào.", Toast.LENGTH_SHORT).show();
                return;
            }
            // Get the selected drink and pass it to MainActivity.
            RadioButton radioButton = findViewById(selectedId);
            String drink = radioButton.getText().toString();
            Intent intent = new Intent(DrinkActivity.this, MainActivity.class);
            intent.putExtra("orderedDrink", drink);
            startActivity(intent);
        });
    }
}
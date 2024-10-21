package com.exercise.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Activity1 extends AppCompatActivity {

    //Khai bao
    EditText enum1, enum2;
    Button btn1;
    TextView tanswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);
// Anh xa
        enum1 = findViewById(R.id.Enum1);
        enum2 = findViewById(R.id.Enum2);
        btn1 = findViewById(R.id.btn1);
        tanswer = findViewById(R.id.TAnswer);

        //code
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                //
                int min = Integer.parseInt(enum1.getText().toString());
                int max = Integer.parseInt(enum2.getText().toString());
                //
                int org = random.nextInt((max - min) + 1) + min;
                //
                String result = String.valueOf(org);
                System.out.println(result);
                tanswer.setText(result);
            }
        });
    }
}
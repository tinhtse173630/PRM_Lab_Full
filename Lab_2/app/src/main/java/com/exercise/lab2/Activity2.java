package com.exercise.lab2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    //khai bao
    Button btn4, btn5, btn6, btn7;
    EditText Enum1, Enum2;
    TextView TAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_2);

        // Anh xa
        btn4 = findViewById(R.id.btn4); // tru
        btn5 = findViewById(R.id.btn5); // nhan
        btn6 = findViewById(R.id.btn6); // cong
        btn7 = findViewById(R.id.btn7); // chia
        Enum1 = findViewById(R.id.Enum1); // so thu 1
        Enum2 = findViewById(R.id.Enum2); // so thu 2
        TAnswer = findViewById(R.id.TAnswer);

        //Code
        // logic hep tru
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(Enum1.getText().toString());
                int num2 = Integer.parseInt(Enum2.getText().toString());

                int cal = num1 - num2; //  tru
                String result = String.valueOf(cal);
                System.out.println(result);
                TAnswer.setText(result);
            }
        });

        // logic  nhan
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(Enum1.getText().toString());
                int num2 = Integer.parseInt(Enum2.getText().toString());
                int cal = num1 * num2; //  nhan
                String result = String.valueOf(cal);
                System.out.println(result);
                TAnswer.setText(result);
            }
        });

        // logic  cong
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num1 = Integer.parseInt(Enum1.getText().toString());
                int num2 = Integer.parseInt(Enum2.getText().toString());
                int cal = num1 + num2; //  cong
                String result = String.valueOf(cal);
                System.out.println(result);
                TAnswer.setText(result);
            }
        });

        // logic  chia
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num1 = Integer.parseInt(Enum1.getText().toString());
                    int num2 = Integer.parseInt(Enum2.getText().toString());

                    int cal = 0; //  chia
                    if (num2 > 0) {
                        cal = num1 / num2;
                    }
                    String result = String.valueOf(cal);
                    System.out.println(result);
                    TAnswer.setText(result);
                }
            });

    }
}
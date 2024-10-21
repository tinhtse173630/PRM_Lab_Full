package com.exercise.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity3_SignIn extends AppCompatActivity {

    //khai bao
    EditText Etext1, Etext2;
    Button btn1;
    TextView Tviewcreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_3_sign_in);

        // anh xa
        Etext1 = findViewById(R.id.Etext1); // User name field
        Etext2 = findViewById(R.id.Etext2); // Password field
        btn1 = findViewById(R.id.btn1); // Button sign in
        Tviewcreate = findViewById(R.id.Tviewcreate); // Text view use to click create

        // vao activity sign up khi nhan Tviewcreate ( Sign up )
        Tviewcreate.setOnClickListener(view -> {
            Intent intent = new Intent(Activity3_SignIn.this, Activity3_SignUp.class);
            startActivity(intent);
        });

        // check su kien dang nhap
        // khi nhan vao btn1 -> lay du lieu nhap username,pass -> check du lieu nhap
        btn1.setOnClickListener(view -> {
            String username = Etext1.getText().toString().trim(); // lay du lieu username
            String password = Etext2.getText().toString().trim(); // lay du lieu password

            if (username.isEmpty() || password.isEmpty()) { // check du lieu rong
                Toast.makeText(Activity3_SignIn.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else {
                // // cho phep vao
                Toast.makeText(Activity3_SignIn.this, "Logged in!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
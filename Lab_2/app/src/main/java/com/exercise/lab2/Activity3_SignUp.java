package com.exercise.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Activity3_SignUp extends AppCompatActivity {

    //khai bao
    EditText Etext1, Etext2, Etext3;
    Button btn1;
    TextView Tviewcreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity3_sign_up);

        // anh xa
        Etext1 = findViewById(R.id.Etext1); // User name field
        Etext2 = findViewById(R.id.Etext2); // Password field
        Etext3 = findViewById(R.id.Etext3); // Confirm Password field
        btn1 = findViewById(R.id.btn1); // Button sign up
        Tviewcreate = findViewById(R.id.Tviewcreate); // Text view use to click create

        // vao activity sign up khi nhan Tviewcreate ( Sign up )
        Tviewcreate.setOnClickListener(view -> {
            Intent intent = new Intent(Activity3_SignUp.this, Activity3_SignIn.class);
            startActivity(intent);
        });

        // check su kien dang nhap
        // khi nhan vao btn1 -> lay du lieu nhap username,pass, confirm bang -> check du lieu nhap
        btn1.setOnClickListener(view -> {
            String username = Etext1.getText().toString().trim(); // lay du lieu username
            String password = Etext2.getText().toString().trim(); // lay du lieu password
            String confirmPassword = Etext3.getText().toString().trim(); // lay du lieu Confirm password

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) { // check du lieu rong
                Toast.makeText(Activity3_SignUp.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) { // check du lieu password = confirm pass
                Toast.makeText(Activity3_SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else { // cho phep tao
                Toast.makeText(Activity3_SignUp.this, "Account created!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
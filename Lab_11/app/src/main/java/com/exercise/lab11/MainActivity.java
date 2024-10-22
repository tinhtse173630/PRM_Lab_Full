package com.exercise.lab11;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.exercise.lab11.api.TraineeRepository;
import com.exercise.lab11.api.TraineeService;
import com.exercise.lab11.model.Trainee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TraineeService traineeService; // Service to interact with the API
    EditText edName, edEmail, edPhone, edGender;
    Button btnSave, btnGetFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = (EditText) findViewById(R.id.edName);
        edEmail = (EditText) findViewById(R.id.edEmail);
        edPhone = (EditText) findViewById(R.id.edPhone);
        edGender = (EditText) findViewById(R.id.edGender);

        btnSave = (Button) findViewById(R.id.buttonSaveData);
        btnGetFull = (Button) findViewById(R.id.buttonFullList);

        // Set click listeners for the buttons
        btnSave.setOnClickListener(this);
        btnGetFull.setOnClickListener(this);

        traineeService = TraineeRepository.getTraineeService(); // Get service to interact with API
    }

    // Handle button clicks
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonSaveData) {
            save(); // Save trainee data
        } else if (view.getId() == R.id.buttonFullList) {
            directToFullList(); // Go to the full list of trainees
        }
    }

    // Redirect to ListViewActivity to show the list of trainees
    private void directToFullList() {
        Intent intent = new Intent(MainActivity.this, ListViewActivity.class);
        startActivity(intent); // Start the ListViewActivity
        finish(); // Finish this activity
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    // Method to save the trainee data
    private void save() {
        // Get input values from the fields
        String name = edName.getText().toString();
        String email = edEmail.getText().toString();
        String phone = edPhone.getText().toString();
        String gender = edGender.getText().toString();

        Trainee trainee = new Trainee(name, email, phone, gender); // Create a new trainee object with the input values

        try {
            // API call to create a new trainee
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if(response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save successfully !", Toast.LENGTH_LONG).show();
                        clearFields(); // Clear the input fields after saving
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save fail !", Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(Exception ex) {
            Log.d("Loi", ex.getMessage());
        }
    }

    // Clear the input fields after saving or returning to the main screen
    private void clearFields() {
        edName.setText("");
        edPhone.setText("");
        edEmail.setText("");
        edGender.setText("");
    }
}
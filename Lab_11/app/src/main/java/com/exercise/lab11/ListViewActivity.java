package com.exercise.lab11;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exercise.lab11.api.TraineeRepository;
import com.exercise.lab11.api.TraineeService;
import com.exercise.lab11.model.Trainee;
import com.exercise.lab11.model.TraineeAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Activity displaying a list of trainees
public class ListViewActivity extends AppCompatActivity implements View.OnClickListener{
    TraineeService traineeService; // Service to interact with API
    ListView lvTrainee; // ListView to display trainees
    TraineeAdapter adapter; // Adapter to populate ListView with trainee data
    List<Trainee> traineeList = new ArrayList<>(); // Holds the trainee data
    Trainee selectedTrainee; // Stores the currently selected trainee

    EditText edId, edName, edPhone, edEmail, edGender;
    Button btnUpdate, btnRemove, btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other); // Set the layout

        // Linking UI elements with the code
        lvTrainee = findViewById(R.id.listStudent);
        traineeService = TraineeRepository.getTraineeService(); // Get service from repository

        edId     = findViewById(R.id.textViewID);
        edName   = findViewById(R.id.textViewName);
        edPhone  = findViewById(R.id.textViewPhone);
        edEmail  = findViewById(R.id.textViewEmail);
        edGender = findViewById(R.id.textViewGender);

        btnUpdate = findViewById(R.id.buttonEdit);
        btnRemove = findViewById(R.id.buttonDelete);
        btnReturn = findViewById(R.id.buttonReturn);

        btnUpdate.setOnClickListener(this);
        btnRemove.setOnClickListener(this);
        btnReturn.setOnClickListener(this);

        listAllTrainees();  // Load the list of trainees
    }

    // Method to list all trainees by calling API
    private void listAllTrainees() {
        try {
            Call<Trainee[]> call = traineeService.getAllTrainees(); // API call to get all trainees

            call.enqueue(new Callback<Trainee[]>() {
                @Override
                public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                    if (!response.isSuccessful()) {
                        Log.d("API Error", "Error code: " + response.code());
                        Toast.makeText(ListViewActivity.this, "Failed to load trainees, code: " + response.code(), Toast.LENGTH_LONG).show();
                        return;
                    }

                    Trainee[] trainees = response.body(); // Get the response body
                    if (trainees == null) {
                        Log.d("API Error", "Empty response");
                        return;
                    }

                    // Add trainees to list and update UI with adapter
                    traineeList.addAll(Arrays.asList(trainees));
                    adapter = new TraineeAdapter(ListViewActivity.this, traineeList, trainee -> loadTraineeInfo(trainee));
                    lvTrainee.setAdapter(adapter);
                }

                @Override
                public void onFailure(Call<Trainee[]> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(ListViewActivity.this, "Failed to load trainees" + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }
        catch(Exception ex) {
            Log.d("Loi", ex.getMessage());
        }
    }

    // Loads selected trainee details into the input fields
    private void loadTraineeInfo(Trainee trainee) {
        selectedTrainee = trainee;
        edId.setText(String.valueOf(trainee.getId()));
        edName.setText(trainee.getName());
        edPhone.setText(trainee.getPhone());
        edEmail.setText(trainee.getEmail());
        edGender.setText(trainee.getGender());
    }

    // Handles button clicks
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.buttonEdit) {
            updateTrainee(); // Handle update
        } else if (view.getId() == R.id.buttonDelete) {
            removeTrainee(); // Handle remove
        } else if (view.getId() == R.id.buttonReturn) {
            returnToMainActivity();
        }
    }

    // Update selected trainee's info
    private void updateTrainee() {
        String name = edName.getText().toString();
        String phone = edPhone.getText().toString();
        String email = edEmail.getText().toString();
        String gender = edGender.getText().toString();

        if (selectedTrainee != null) {
            // Set new details to trainee
            selectedTrainee.setName(name);
            selectedTrainee.setPhone(phone);
            selectedTrainee.setEmail(email);
            selectedTrainee.setGender(gender);

            Call<Trainee> call = traineeService.updateTrainees(selectedTrainee.getId(), selectedTrainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ListViewActivity.this, "Update successful", Toast.LENGTH_LONG).show();

                        adapter.notifyDataSetChanged();

                        clearFields(); // Clear the input fields after update
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(ListViewActivity.this, "Update failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // Remove the selected trainee
    private void removeTrainee() {
        if (selectedTrainee != null) {
            Call<Void> call = traineeService.deleteTrainees(selectedTrainee.getId());
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        traineeList.remove(selectedTrainee); // Remove from local list
                        adapter.notifyDataSetChanged(); // Update the UI
                        Toast.makeText(ListViewActivity.this, "Remove successful", Toast.LENGTH_LONG).show();
                        clearFields(); // Clear the input fields after remove
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(ListViewActivity.this, "Remove failed", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    // Clears all the input fields
    private void clearFields() {
        edId.setText("none");
        edName.setText("None");
        edPhone.setText("None");
        edEmail.setText("None");
        edGender.setText("None");
        selectedTrainee = null;
    }

    // Returns to the main activity screen
    private void returnToMainActivity() {
        Intent intent = new Intent(ListViewActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Finish this activity
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}

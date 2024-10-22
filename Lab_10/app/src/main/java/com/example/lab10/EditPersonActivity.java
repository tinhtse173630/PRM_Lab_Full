package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

public class EditPersonActivity extends AppCompatActivity {
    private EditText edtFirstName;
    private EditText edtLastName;

    private Button btnSave;
    private int personId;

    private Intent intent;
    private AppDatabase database;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_edit_person);

        Toolbar toolbar = findViewById(R.id.toolbarEdit); // Assuming you have a Toolbar in your layout
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Edit"); // Set the title if needed
        }

        initViews();
        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            btnSave.setText("Update");
            personId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = database.personDao().loadPersonById(personId);
                    populateUI(person);
                }
            });
        }
    }

    private void populateUI(Person person) {
        if (person == null) {
            return;
        }

        edtFirstName.setText(person.getFirstName());
        edtLastName.setText(person.getLastName());
    }

    private void initViews() {
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }

    public void onSaveButtonClicked() {
        String firstName = edtFirstName.getText() != null ? edtFirstName.getText().toString() : "";
        String lastName = edtLastName.getText() != null ? edtLastName.getText().toString() : "";

        if (firstName.isEmpty() || lastName.isEmpty()) {
            Toast.makeText(this, "Please enter both first name and last name.", Toast.LENGTH_SHORT).show();
            return;
        }

        final Person person = new Person(firstName, lastName);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (getIntent() != null && !getIntent().hasExtra(Constants.UPDATE_Person_Id)) {
                    database.personDao().insert(person);
                } else {
                    person.setUid(personId);
                    database.personDao().update(person);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}



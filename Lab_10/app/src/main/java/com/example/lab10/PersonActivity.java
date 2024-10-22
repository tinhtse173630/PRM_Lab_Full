package com.example.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PersonActivity extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar); // Assuming you have a Toolbar in your layout
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("Home");
        }

        fabAdd = findViewById(R.id.fab);

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PersonActivity.this, EditPersonActivity.class));
            }
        });

        recyclerView = findViewById(R.id.rvPerson);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PersonAdapter(this);
        recyclerView.setAdapter(adapter);

        database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Person> tasks = adapter.getTasks();
                        database.personDao().delete(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Person> persons = database.personDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setTasks(persons);
                    }
                });
            }
        });
    }
}

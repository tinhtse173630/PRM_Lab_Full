package com.example.lab_3;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class Activity1 extends AppCompatActivity {
// khai bao
    ListView lvMon;
    ArrayList<String> arrayMon = new ArrayList<>();
    Button add,edit,delete;
    EditText input;
int location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_1);

        //Anh xa
        lvMon = findViewById(R.id.lvMon);
        add = findViewById(R.id.add);
        edit = findViewById(R.id.edit);
        delete = findViewById(R.id.delete);
        input = findViewById(R.id.input);

        // Them du lieu ao
        arrayMon.add("Android");
        arrayMon.add("PHP");
        arrayMon.add("IOS");
        arrayMon.add("Unity");
        arrayMon.add("ASP.net");
        ArrayAdapter adapter = new ArrayAdapter( Activity1.this, android.R.layout.simple_list_item_1, arrayMon);
        lvMon.setAdapter(adapter);

        // them du lieu
        add.setOnClickListener(view -> {
            String mon = input.getText().toString();
            arrayMon.add(mon);
            adapter.notifyDataSetChanged();
        });

        // xac nhan vi tri trong list
        lvMon.setOnItemClickListener((adapterView, view, i, l) -> {
            input.setText(arrayMon.get(i));
            location = i;
        });

        // Sua du lieu
        edit.setOnClickListener(view -> {
                arrayMon.set(location,input.getText().toString());
                adapter.notifyDataSetChanged();
        });

        // xoa du lieu
        delete.setOnClickListener(view -> {
            arrayMon.remove(location);
            adapter.notifyDataSetChanged();
        });
    }
}
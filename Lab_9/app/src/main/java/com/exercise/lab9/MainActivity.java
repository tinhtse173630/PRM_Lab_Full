package com.exercise.lab9;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Database database;                  // Database helper object
    ListView lvCongViec;                // ListView to display tasks
    ArrayList<CongViec> arrayCongViec;  // List of tasks
    CongViecAdapter adapter;            // Adapter to bind tasks to the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvCongViec = findViewById(R.id.listviewCongViec);

        arrayCongViec = new ArrayList<>();   // Initialize the task list
        adapter = new CongViecAdapter(this, R.layout.dong_cong_viec, arrayCongViec); // Initialize the adapter
        lvCongViec.setAdapter(adapter);      // Set the adapter to the ListView

        // Create a new database named
        database = new Database(this, "GhiChu.sqlite", null, 1);

        // Create the CongViec (Table) table if it doesn't already exist
        database.QueryData("CREATE TABLE IF NOT EXISTS CongViec (id INTEGER PRIMARY KEY AUTOINCREMENT, TenCV NVARCHAR(200))");

        // Insert data
        // Uncomment below lines if you want to insert data only once
//         database.QueryData("INSERT INTO CongViec VALUES (null, 'Project Android')");
//         database.QueryData("INSERT INTO CongViec VALUES (null, 'Design app')");

        // Select data
        GetDataCongViec(); // Load the task data from the database and display it in the ListView

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu); // Inflate the menu with the add task option
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {  // When the add task menu item is clicked, open the dialog to add a new task
            DialogThem();                        // Show the add task dialog
        }
        return super.onOptionsItemSelected(item);
    }

    // Dialog to add a new task
    private void DialogThem() {
        Dialog dialog = new Dialog(this);                       // Create a new dialog
        dialog.setContentView(R.layout.dialog_them_cong_viec);         // Set the dialog layout
        EditText edtTen = dialog.findViewById(R.id.editTextTenCV);
        Button btnThem = dialog.findViewById(R.id.buttonThem);
        Button btnHuy = dialog.findViewById(R.id.buttonHuy);

        // Handle button click listener
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tencv = edtTen.getText().toString().trim(); // Get task name from EditText
                // Check if the task name is empty
                if (tencv.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc!", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert the new task into the database
                    database.QueryData("INSERT INTO CongViec VALUES (null, '" + tencv + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();   // Close the dialog
                    GetDataCongViec();  // Refresh the task list
                }
            }
        });

        // Cancel button click listener
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show(); // Show the dialog
    }

    // Dialog to edit an existing task
    public void DialogSuaCongViec(String ten, int id) {
        Dialog dialog = new Dialog(this);       // Create a new dialog
        dialog.setContentView(R.layout.dialog_sua);
        EditText edtTenCV = dialog.findViewById(R.id.editTextTenCV);
        Button btnXacNhan = dialog.findViewById(R.id.buttonXacnhan);
        Button btnHuy = dialog.findViewById(R.id.buttonHuyEdit);

        edtTenCV.setText(ten);  // Set the current task name in the EditText
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenMoi = edtTenCV.getText().toString().trim();   // Get the updated task name
                database.QueryData("UPDATE CongViec SET TenCV = '" + tenMoi + "' WHERE id = '" + id + "'");
                Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
                dialog.dismiss();   // Close the dialog
                GetDataCongViec();  // Refresh the task list
            }
        });

        // Close the dialog when cancel button is clicked
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    // Dialog to delete an existing task
    public void DialogXoaCongViec(String tencv, int Id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);      // Create an AlertDialog
        dialogXoa.setMessage("Bạn có muốn xóa công việc " + tencv + " không?");    // Set the delete confirmation message
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Delete the task from the database
                database.QueryData("DELETE FROM CongViec WHERE Id = '" + Id + "'");
                Toast.makeText(MainActivity.this, "Đã xóa " + tencv, Toast.LENGTH_SHORT).show();
                GetDataCongViec();  // Refresh the task list
            }
        });
        dialogXoa.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();  // Cancel the deletion
            }
        });
        dialogXoa.show();   // Show the AlertDialog
    }

    // Method to load tasks from the database and display them in the ListView
    private void GetDataCongViec() {
        arrayCongViec.clear();  // Clear the existing task list ( Array ) before add new task
        Cursor dataCongViec = database.GetData("SELECT * FROM CongViec");

        // Loop through the result and add tasks to the list
        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);     // Get the task name
            int id = dataCongViec.getInt(0);            // Get the task ID
            arrayCongViec.add(new CongViec(id, ten));      // Add the task to the list
        }
        adapter.notifyDataSetChanged();                    // Notify the adapter to update the ListView
    }
}

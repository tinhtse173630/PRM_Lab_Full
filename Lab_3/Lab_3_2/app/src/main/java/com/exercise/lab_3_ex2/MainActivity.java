package com.exercise.lab_3_ex2;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvFruit;
    ArrayList<Fruit> fruitList = new ArrayList<Fruit>();
    EditText txtName, txtDescription;
    Spinner imageSpinner;
    Button btnAdd, btnRemove, btnEdit;
    int selectedPos = -1; // Position of the currently selected fruit
    int selectedImage = -1; // Position of the selected image in the spinner

    // Array of fruit images (ensure these resources exist in your drawable folder)
    private int[] fruitImages = {
        R.drawable.banana,
        R.drawable.dragonfruit,
        R.drawable.graps,
        R.drawable.orange,
        R.drawable.chomchom,
        R.drawable.melon,
        R.drawable.pear,
        R.drawable.strawberry
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // Enable edge-to-edge display for the activity
        setContentView(R.layout.activity_main); // Set the layout for the activity
        // Apply window insets to manage padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components and set up data
        Mapping();
        // Step 2: Set up the custom adapter
        ImageSpinnerAdapter spinnerAdapter = new ImageSpinnerAdapter(this, fruitImages);
        imageSpinner.setAdapter(spinnerAdapter);
        // Set up the adapter for the ListView displaying fruits
        FruitAdapter adapter = new FruitAdapter(this, R.layout.list_item,fruitList);
        lvFruit.setAdapter(adapter);


        // Listener for selecting an image from the spinner
        imageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedImage = i; // Update the selected image index
                //Toast.makeText(MainActivity.this, "Selected position: " + i, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Nothing
            }
        });
        // Listener for clicking on an item in the ListView

// Image Dropdown list (Spinner)
        lvFruit.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Get the selected fruit from the list
                Fruit selectedFruit = fruitList.get(i);
                txtDescription.setText(selectedFruit.getDescription());
                txtName.setText(selectedFruit.getName());

                // Find the position of the fruit's image in the image array
                int imagePos = 0;
                for (int j = 0; j < fruitImages.length; j++){
                    if (fruitImages[j] == selectedFruit.getImage()){
                        imagePos = j; // Store the image position
                        break;
                    }
                }
                imageSpinner.setSelection(imagePos);  // Set the spinner selection to the image position
                selectedPos = i; // Set the selected position
            }
        });
        // Add fruit
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fruitList.add(new Fruit(
                        txtName.getText().toString(),
                        txtDescription.getText().toString(),
                        fruitImages[selectedImage] ));
                adapter.notifyDataSetChanged(); // Notify the adapter to refresh the ListView
                Toast.makeText(MainActivity.this, "Thêm mới trái cây thành công", Toast.LENGTH_SHORT).show();
            }
        });

        // Edit fruit
        btnEdit.setOnClickListener(view -> {
            if (selectedPos != -1) { // Check if an item is selected
                String name = txtName.getText().toString();
                String description = txtDescription.getText().toString();
                Fruit updatedFruit = new Fruit(name, description, fruitImages[selectedImage]);
                fruitList.set(selectedPos, updatedFruit); // Update the list at the selected position
                adapter.notifyDataSetChanged(); // Notify the adapter to refresh the ListView
                Toast.makeText(MainActivity.this, "Cập nhật trái cây thành công !" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Hãy chọn trái cây cần sửa ?", Toast.LENGTH_SHORT).show();
            }
        });

        // Remove fruit
        btnRemove.setOnClickListener(view -> {
            if (selectedPos != -1) {
                fruitList.remove(selectedPos); // Remove the selected fruit from the list
                adapter.notifyDataSetChanged(); // Notify the adapter to refresh the ListView
                txtName.setText(""); // Clear input
                txtDescription.setText(""); // Clear input
                selectedPos = -1; // Reset selected position
                Toast.makeText(MainActivity.this, "Xóa trái cây thành công !" , Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Hãy chọn trái cây cần xóa ?", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void Mapping(){
        lvFruit = findViewById(R.id.lvFruits);
        txtName = findViewById(R.id.etTen);
        txtDescription = findViewById(R.id.etMoTa);
        imageSpinner = findViewById(R.id.spAnh);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        btnRemove = findViewById(R.id.btnDelete);

        fruitList.add(new Fruit("Chuối tiêu","Chuối tiêu Long An", R.drawable.banana));
        fruitList.add(new Fruit("Thanh long","Thanh long ruột đỏ", R.drawable.dragonfruit));
        fruitList.add(new Fruit("Dưa hấu","Dưa hấu mê lon", R.drawable.melon));
        fruitList.add(new Fruit("Cam cam","Cam cam là do cam màu cam", R.drawable.orange));
        fruitList.add(new Fruit("Dâu tây","Dâu tây Đã Lạt", R.drawable.strawberry));
        fruitList.add(new Fruit("Việt quất","Việt quất Biên Hòa", R.drawable.graps));
    };
}
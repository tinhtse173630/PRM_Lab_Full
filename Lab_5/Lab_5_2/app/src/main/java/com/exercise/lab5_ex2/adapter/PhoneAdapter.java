package com.exercise.lab5_ex2.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.lab5_ex2.R;
import com.exercise.lab5_ex2.model.Phone;

import java.util.List;

// Adapter class for managing a list of Phone objects in a RecyclerView
public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
    private List<Phone> phones;

    // Constructor to initialize the phone list
    public PhoneAdapter(List<Phone> phones) {
        this.phones = phones;
    }

    // Create and return a new ViewHolder for displaying a phone item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
// Inflate the layout for a phone list item
        View userView = inflater.inflate(R.layout.row_phone, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    // Bind phone data to the ViewHolder's views for display in the RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone phone = phones.get(position);
        holder.imagePhone.setImageResource(phone.getImage());
        holder.tvPhoneName.setText(phone.getName());
        holder.tvBrandValue.setText(phone.getBrand());
        holder.tvYearValue.setText(String.valueOf(phone.getReleaseYear()));
        holder.tvPrice.setText(phone.getPrice() + "");

        // Handle Edit button click
        holder.btnAddPhone.setOnClickListener(v -> {
            // When the Edit button is clicked, save the new values from the EditText fields

            String newName = holder.tvPhoneName.getText().toString();
            String newBrand = holder.tvBrandValue.getText().toString();
            int newYear = Integer.parseInt(holder.tvYearValue.getText().toString());
            double newPrice = Double.parseDouble(holder.tvPrice.getText().toString());

            // Create a new Phone object with the new values
            Phone newPhone = new Phone(newName, newBrand, newYear, newPrice, R.drawable.iphone12);
            phones.add(newPhone);

            // Notify the adapter that the data has changed, refreshing the current item
            notifyItemChanged(phones.size()-1);
        });

        // Handle Edit button click
        holder.btnEditPhone.setOnClickListener(v -> {
            // When the Edit button is clicked, save the new values from the EditText fields

            String newName = holder.tvPhoneName.getText().toString();
            String newBrand = holder.tvBrandValue.getText().toString();
            int newYear = Integer.parseInt(holder.tvYearValue.getText().toString());
            double newPrice = Double.parseDouble(holder.tvPrice.getText().toString());

            // Update the phone object with the new values
            phone.setName(newName);
            phone.setBrand(newBrand);
            phone.setReleaseYear(newYear);
            phone.setPrice(newPrice);

            // Notify the adapter that the data has changed, refreshing the current item
            notifyItemChanged(position);
        });

        holder.imagePhone.setOnClickListener(v -> {
            // Create an array of drawable resource IDs (add more images if necessary)
            final int[] imageOptions = {
                    R.drawable.iphone11,
                    R.drawable.iphone12,
                    R.drawable.iphone13,
                    R.drawable.iphone13pro,
                    R.drawable.iphonese
            };

            // Create an AlertDialog to display the available images
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            builder.setTitle("Choose an Image");

            // Set up the list of images for selection
            String[] imageNames = {"iPhone 11", "iPhone 12", "iPhone 13", "iPhone 13 Pro", "iPhone SE"};
            builder.setItems(imageNames, (dialog, which) -> {
                // When an image is selected, update the ImageView with the chosen image
                holder.imagePhone.setImageResource(imageOptions[which]);

                // Optionally, you can also update the phone object with the selected image
                phone.setImage(imageOptions[which]);

                // Notify the adapter if needed to refresh the UI
                notifyItemChanged(position);
            });

            // Show the dialog
            builder.show();
        });

        // Handle Delete button click
        holder.btnDeletePhone.setOnClickListener(v -> {
            // Call deletePhone method and remove the phone from the list
            phones.remove(position);
            notifyItemRemoved(position);
        });
    }

    // Return the total number of phone items to be displayed
    @Override
    public int getItemCount() {
        return phones.size();
    }
    // ViewHolder class that holds references to the views for each phone item
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhone;
        EditText tvPhoneName, tvBrandValue, tvYearValue, tvPrice;
        Button btnAddPhone, btnEditPhone, btnDeletePhone;

        // Constructor to bind the views in the layout
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePhone = itemView.findViewById(R.id.imagePhone);
            tvPhoneName = itemView.findViewById(R.id.tvPhoneName);
            tvBrandValue = itemView.findViewById(R.id.tvBrandValue);
            tvYearValue = itemView.findViewById(R.id.tvYearValue);
            tvPrice = itemView.findViewById(R.id.tvPrice);

//            imagePhone = itemView.findViewById(R.id.imagePhoneAdd);
//            tvPhoneName = itemView.findViewById(R.id.tvPhoneNameAdd);
//            tvBrandValue = itemView.findViewById(R.id.tvBrandValueAdd);
//            tvYearValue = itemView.findViewById(R.id.tvYearValueAdd);
//            tvPrice = itemView.findViewById(R.id.tvPrice);

            btnAddPhone = itemView.findViewById(R.id.btnAddPhone);
            btnEditPhone = itemView.findViewById(R.id.btnEditPhone);
            btnDeletePhone = itemView.findViewById(R.id.btnDeletePhone);
        }
    }



}

package com.exercise.lab9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CongViecAdapter extends BaseAdapter {
    private MainActivity context;           // Reference to the activity
    private int layout;                     // Layout for each item in the ListView
    private List<CongViec> congViecList;    // List of tasks

    // Constructor for the adapter
    public CongViecAdapter(MainActivity context, int layout, List<CongViec> congViecList) {
        this.context = context;
        this.layout = layout;
        this.congViecList = congViecList;
    }

    @Override
    public int getCount() {
        return congViecList.size();         // Return the size of the task list
    }

    @Override
    public Object getItem(int position) {
        return congViecList.get(position);  // Return the task at the given position
    }

    @Override
    public long getItemId(int position) {
        return position;                    // Return the position as the item ID
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;                  // ViewHolder to hold references to the views
        if (convertView == null) {          // If no reusable view is available, inflate a new view
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, parent, false);
            // Bind views from the layout to the ViewHolder
            holder.txtTen = convertView.findViewById(R.id.texviewTen);
            holder.imgDelete = convertView.findViewById(R.id.imageviewDelete);
            holder.imgEdit = convertView.findViewById(R.id.imageviewEdit);
            convertView.setTag(holder);     // Store the ViewHolder for future reuse
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // Get the task at the current position and set its name in the TextView
        CongViec congViec = congViecList.get(position);
        holder.txtTen.setText(congViec.getTenCV());

        // Set up the Edit button's click listener to open the edit dialog
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSuaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });

        // Set up the Delete button's click listener to open the delete confirmation dialog
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCongViec(congViec.getTenCV(), congViec.getIdCV());
            }
        });
        return convertView;         // Return the completed view for the ListView
    }

    // ViewHolder class to hold references to the views in each item
    private static class ViewHolder {
        TextView txtTen;
        ImageView imgDelete;
        ImageView imgEdit;
    }
}

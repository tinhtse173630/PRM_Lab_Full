package com.example.lab10;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.MyViewHolder> {
    private Context context;
    private List<Person> personList;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_person,
                viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.MyViewHolder holder, int i) {
        holder.firstName.setText(personList.get(i).getFirstName());
        holder.lastName.setText(personList.get(i).getLastName());
    }

    @Override
    public int getItemCount() {
        if (personList == null) {
            return 0;
        }
        return personList.size();
    }

    public void setTasks(List<Person> personList) {
        this.personList = personList;
        notifyDataSetChanged();
    }

    public List<Person> getTasks() {
        return personList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView firstName, lastName;
        ImageView editImage;

        MyViewHolder(@NonNull final View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);

            editImage = itemView.findViewById(R.id.editIcon);
            editImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int elementId = personList.get(getAdapterPosition()).getUid();
                    Intent i = new Intent(context, EditPersonActivity.class);
                    i.putExtra(Constants.UPDATE_Person_Id, elementId);
                    context.startActivity(i);
                }
            });
        }
    }
}

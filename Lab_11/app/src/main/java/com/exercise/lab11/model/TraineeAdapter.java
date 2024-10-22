package com.exercise.lab11.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.exercise.lab11.R;

import java.util.List;

public class TraineeAdapter extends ArrayAdapter<Trainee> {
    private Context context;
    private List<Trainee> traineeList;
    private OnTraineeClickListener listener;

    public TraineeAdapter(Context context, List<Trainee> traineeList, OnTraineeClickListener listener) {
        super(context, 0, traineeList);
        this.context = context;
        this.traineeList = traineeList;
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.small_item, parent, false);
        }

        Trainee trainee = traineeList.get(position);

        TextView tvTraineeId = convertView.findViewById(R.id.textViewID);
        TextView tvTraineeName = convertView.findViewById(R.id.textViewName);
        TextView tvTraineePhone = convertView.findViewById(R.id.textViewPhone);
        TextView tvTraineeEmail = convertView.findViewById(R.id.textViewEmail);
        TextView tvTraineeGender = convertView.findViewById(R.id.textViewGender);

        tvTraineeId.setText(trainee.getId() + "");
        tvTraineeName.setText(trainee.getName());
        tvTraineePhone.setText(trainee.getPhone());
        tvTraineeEmail.setText(trainee.getEmail());
        tvTraineeGender.setText(trainee.getGender());

        convertView.setOnClickListener(v -> listener.onTraineeClick(trainee));

        return convertView;
    }

    public interface OnTraineeClickListener {
        void onTraineeClick(Trainee trainee);
    }
}

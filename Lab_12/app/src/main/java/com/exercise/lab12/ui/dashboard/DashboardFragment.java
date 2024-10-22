package com.exercise.lab12.ui.dashboard;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.lab12.R;
import com.exercise.lab12.databinding.FragmentDashboardBinding;
import com.exercise.lab12.ui.viewmodel.SharedViewModel;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private SharedViewModel sharedViewModel;

    public static DashboardFragment newInstance() {
        return new DashboardFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel  .class);

        binding.btnUppNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedViewModel.incrementData();
            }
        });


        sharedViewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                binding.textDashboard.setText(String.valueOf(data));
            }
        });

        return root;

    }
}
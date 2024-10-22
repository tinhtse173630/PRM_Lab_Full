package com.exercise.lab12.ui.notification;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.exercise.lab12.R;
import com.exercise.lab12.databinding.FragmentNotificationBinding;
import com.exercise.lab12.ui.viewmodel.SharedViewModel;

public class NotificationFragment extends Fragment {

    private FragmentNotificationBinding binding;

    private SharedViewModel sharedViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentNotificationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        sharedViewModel.getSharedData().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer data) {
                binding.textNotification.setText(String.valueOf(data));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
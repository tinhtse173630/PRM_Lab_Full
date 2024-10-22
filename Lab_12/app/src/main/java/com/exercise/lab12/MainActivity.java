package com.exercise.lab12;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.exercise.lab12.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Binding layout của bạn
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Thiết lập Toolbar
        setSupportActionBar(binding.toolbar);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notification).build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination,
                                             @Nullable Bundle arguments) {
                // Sử dụng if-else để kiểm tra ID
                if (destination.getId() == R.id.navigation_home) {
                    binding.toolbar.setTitle("Home");
                } else if (destination.getId() == R.id.navigation_dashboard) {
                    binding.toolbar.setTitle("Dashboard");
                } else if (destination.getId() == R.id.navigation_notification) {
                    binding.toolbar.setTitle("Notifications");
                } else {
                    binding.toolbar.setTitle("Title");
                }
            }
        });
    }}
package com.exercise.lab5_ex1;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.exercise.lab5_ex1.adapter.UserAdapter;
import com.exercise.lab5_ex1.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        // Initialize the RecyclerView for displaying the user list
        RecyclerView rvUserList = findViewById(R.id.rvUserList);

        // Create a list of User objects with predefined data
        users = new ArrayList<>();
        users.add(new User("BaoHT", "Nguyen Thanh Bao", "Baoht@gmail.com"));
        users.add(new User("nghiatt", "Ton Trong Nghia", "nghiatt@gmail.com"));
        users.add(new User("Antv", "Tran Van AN", "Antv@gmail.com"));
        users.add(new User("BangTT", "Tran Thanh Bang", "Bangtt@gmail.com"));
        users.add(new User("Antm", "Tran Manh An", "antm@gmail.com"));
        users.add(new User("KhangTT", "Tran Thanh Khang", "khangtt@gmail.com"));
        users.add(new User("HungTT", "Tran Thanh Hung", "hungtt@gmail.com"));
        users.add(new User("ThanhVC", "Vo Cong Thanh", "thanhvc@gmail.com"));
        users.add(new User("hungtm", "Tran Manh Hung", "hungtm@gmail.com"));


        // Set up the adapter and layout manager for the RecyclerView
        UserAdapter userAdapter = new UserAdapter(users);
        rvUserList.setAdapter(userAdapter);
        rvUserList.setLayoutManager(new LinearLayoutManager(this));
    }
}
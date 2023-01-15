package com.example.android_assignment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.android_assignment.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

public class StudentMark extends AppCompatActivity {

    Button logout,navMark,navHome,navWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_mark);

        logout = findViewById(R.id.logout);
        navMark = findViewById(R.id.navMark);
        navHome = findViewById(R.id.navHome);
        navWork = findViewById(R.id.navWork);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMark.this, StudentLogin.class));
                SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember1", "false");
                editor.apply();
                finish();
            }
        });

        navMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMark.this, StudentMark.class));
            }
        });

        navWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMark.this, StudentWork.class));
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMark.this, StudentMainActivity.class));
            }
        });
    }
}

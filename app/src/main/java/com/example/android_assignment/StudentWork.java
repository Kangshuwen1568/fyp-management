package com.example.android_assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StudentWork extends AppCompatActivity {

    Button navHome, navMark, navWork, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        navMark = findViewById(R.id.navMark);
        navHome = findViewById(R.id.navHome);
        navWork = findViewById(R.id.navWork);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentWork.this, StudentLogin.class));
                SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember1", "false");
                editor.apply();
                finish();
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentWork.this, StudentMainActivity.class));
            }
        });

        navMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentWork.this, StudentMark.class));
            }
        });

        navWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentWork.this, StudentWork.class));
            }
        });
    }
}
package com.example.android_assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class StudentMainActivity extends AppCompatActivity {

    Button logout,upload,upload2,upload3,upload4,upload5,upload6,navMark,navHome,navWork;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main);

        logout = findViewById(R.id.logout);
        upload = findViewById(R.id.upload);
        upload2 = findViewById(R.id.upload2);
        upload3 = findViewById(R.id.upload3);
        upload4 = findViewById(R.id.upload4);
        upload5 = findViewById(R.id.upload5);
        upload6 = findViewById(R.id.upload6);
        navMark = findViewById(R.id.navMark);
        navHome = findViewById(R.id.navHome);
        navWork = findViewById(R.id.navWork);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(StudentMainActivity.this, StudentLogin.class));
                SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember1", "false");
                editor.apply();
                finish();
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        upload2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        upload3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        upload4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        upload5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        upload6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, UploadPdf.class));
            }
        });

        navMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, StudentMark.class));
            }
        });

        navWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, StudentWork.class));
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StudentMainActivity.this, StudentMainActivity.class));
            }
        });
    }
}

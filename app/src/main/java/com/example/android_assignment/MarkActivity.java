package com.example.android_assignment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MarkActivity extends AppCompatActivity {

    Button navHome, navMark, navWork, comment_btn, logout;
    EditText title, mark, comment;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);

        title = findViewById(R.id.titleInput);
        mark = findViewById(R.id.markInput);
        comment = findViewById(R.id.commentInput);
        comment_btn = findViewById(R.id.comment_btn);

        navMark = findViewById(R.id.navMark);
        navHome = findViewById(R.id.navHome);
        navWork = findViewById(R.id.navWork);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MarkActivity.this, LoginActivity.class));
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String myTitle = title.getText().toString().trim();
                String myMark = mark.getText().toString().trim();
                String myComment = comment.getText().toString().trim();

                if (myTitle.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Enter the title!!!", Toast.LENGTH_SHORT).show();
                }

                if (myMark.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Enter the mark!!!", Toast.LENGTH_SHORT).show();
                }

                if (myComment.isEmpty()) {
                    Toast.makeText(MarkActivity.this, "Enter the comment!!!", Toast.LENGTH_SHORT).show();
                }

                String dbTitle = "1 Title";
                String dbMark = "2 Mark";
                String dbComment = "3 Comment";
                if (i <= 9999) {
                    i = i + 1;
                    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                    DatabaseReference databaseReference = firebaseDatabase.getReference("Mark List " + i);
                    databaseReference.child(dbTitle).setValue(myTitle).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                title.setText("");
                                Toast.makeText(MarkActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbMark).setValue(myMark).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mark.setText("");
                                Toast.makeText(MarkActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    databaseReference.child(dbComment).setValue(myComment).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                comment.setText("");
                                Toast.makeText(MarkActivity.this, "Updated", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(MarkActivity.this, "Something Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        navHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, MainActivity.class));
            }
        });

        navWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, WorkActivity.class));
            }
        });

        navMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MarkActivity.this, MarkActivity.class));
            }
        });
    }
}

package com.example.android_assignment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class StudentLogin extends AppCompatActivity {

    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText, adminLogin;
    private Button loginButton;
    private CheckBox remember1;
    private FirebaseAuth auth;
    GoogleSignInOptions gOptions;
    GoogleSignInClient gClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        remember1 = findViewById(R.id.remember1);
        signupRedirectText = findViewById(R.id.signUpRedirectText);
        adminLogin = findViewById(R.id.adminLogin);

        auth = FirebaseAuth.getInstance();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(StudentLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(StudentLogin.this, StudentMainActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(StudentLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLogin.this, SignUpActivity.class));
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StudentLogin.this, LoginActivity.class));
            }
        });

        //Inside onCreate
        gOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gClient = GoogleSignIn.getClient(this, gOptions);

        GoogleSignInAccount gAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (gAccount != null) {
            finish();
            Intent intent = new Intent(StudentLogin.this, StudentMainActivity.class);
            startActivity(intent);
        }
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK) {
                            Intent data = result.getData();
                            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                            try {
                                task.getResult(ApiException.class);
                                finish();
                                Intent intent = new Intent(StudentLogin.this, StudentMainActivity.class);
                                startActivity(intent);
                            } catch (ApiException e) {
                                Toast.makeText(StudentLogin.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

        SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
        String checkbox1 = preferences.getString("remember1", "");

        if (checkbox1.equals("true")) {
            startActivity(new Intent(StudentLogin.this, StudentMainActivity.class));
        } else {
            Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
        }

        remember1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember1", "true");
                    editor.apply();
                    Toast.makeText(StudentLogin.this, "checked", Toast.LENGTH_SHORT).show();
                } else if (!compoundButton.isChecked()) {
                    SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember1", "false");
                    editor.apply();
                    Toast.makeText(StudentLogin.this, "unchecked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.ds.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private TextView tvLogin,tvForgotPassword;
    private EditText email,password;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        email = findViewById(R.id.edt_emailID);
        password = findViewById(R.id.edt_pwd);
        login = findViewById(R.id.btn_loginHere);
        tvForgotPassword = findViewById(R.id.tv_forgotPassword);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               loginUser();
            }

            private void loginUser() {
                String mail = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (mail.isEmpty()) {
                    email.setError("Address is Required");
                    email.requestFocus();
                    return;
                }

                if (pass.isEmpty()) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }

                if (pass.length() < 6) {
                    password.setError("Min password length should be 6 char");
                    password.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(task -> {


                    if (task.isSuccessful()){
                        if(mAuth.getCurrentUser().isEmailVerified()){
                            Intent intent = new Intent(Login.this, Bottom_Navigation.class);
                            startActivity(intent);
                            Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Login.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = email.getText().toString().trim();
                if (mail.isEmpty()) {
                    email.setError("Please enter your email for reset");
                    email.requestFocus();
                    return;
                }
                mAuth.sendPasswordResetEmail(email.getText().toString().trim())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Password send to your email", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
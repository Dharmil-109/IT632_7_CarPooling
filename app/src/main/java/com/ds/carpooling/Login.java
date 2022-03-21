package com.ds.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Login.this, "Yaad Toh Rakho", Toast.LENGTH_SHORT).show();
            }
        });

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
                        Intent intent = new Intent(Login.this, Bottom_Navigation.class);
                        startActivity(intent);
                        Toast.makeText(Login.this, "Logged in successfuly", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(Login.this, "Failed to Login", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
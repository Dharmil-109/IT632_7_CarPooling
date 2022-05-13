package com.ds.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ds.carpooling.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class RegisterHere extends AppCompatActivity {

    private TextView register,gender;
    private EditText firstname,lastname,email,phone,address,password,confirmPassword;
    private RadioButton male,female;
    private Button submit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_here);

        mAuth=FirebaseAuth.getInstance();
        register = findViewById(R.id.tv_register);
        gender = findViewById(R.id.tv_select_gender);

        firstname = findViewById(R.id.edt_firstname);
        lastname = findViewById(R.id.edt_lastname);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        address = findViewById(R.id.edt_address);
        password = findViewById(R.id.edt_password);
        confirmPassword = findViewById(R.id.edt_confirm_password);

        male = findViewById(R.id.radio_male);
        female = findViewById(R.id.radio_female);

        submit = findViewById(R.id.btn_submit);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }

            private void registerUser() {
                String fname = firstname.getText().toString().trim();
                String lname = lastname.getText().toString().trim();
                String mail = email.getText().toString().trim();
                String phoneNo = phone.getText().toString().trim();
                String add = address.getText().toString().trim();
                String pwd = password.getText().toString().trim();
                String cPwd = confirmPassword.getText().toString().trim();

                if (fname.isEmpty()) {
                    firstname.setError("First Name is Required");
                    firstname.requestFocus();
                    return;
                }

                if (lname.isEmpty()) {
                    lastname.setError("Last Name is Required");
                    lastname.requestFocus();
                    return;
                }

                if (mail.isEmpty()) {
                    email.setError("Email is Required");
                    email.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(mail).matches()) {
                    email.setError("Please Provide Valid Email");
                    email.requestFocus();
                    return;
                }

                if (phoneNo.isEmpty()) {
                    phone.setError("Phone Number is Required");
                    phone.requestFocus();
                    return;
                }

                if (phoneNo.length()<10) {
                    phone.setError("Please Enter Valid Phone Number");
                    phone.requestFocus();
                    return;
                }

                if (add.isEmpty()) {
                    address.setError("Address is Required");
                    address.requestFocus();
                    return;
                }

                if (pwd.isEmpty()) {
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }

                if (pwd.length() <6) {
                    password.setError("Min password length should be 6 char");
                    password.requestFocus();
                    return;
                }

                if (cPwd.isEmpty()) {
                    confirmPassword.setError("Confirm Password is Required");
                    confirmPassword.requestFocus();
                    return;
                }

                if (!cPwd.equals(pwd)){
                    confirmPassword.setError("Password must be same");
                    confirmPassword.requestFocus();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(mail,pwd).addOnCompleteListener(task -> {

                            if (task.isSuccessful()){

                                User user = new User(fname,lname,mail,phoneNo,add,pwd);

                                mAuth.getCurrentUser().sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(RegisterHere.this, "Registration Successful please check your email for verification", Toast.LENGTH_SHORT).show();

                                        FirebaseDatabase database=FirebaseDatabase.getInstance();
                                        DatabaseReference DBRef=database.getReference("Registration").child(mAuth.getCurrentUser().getUid());
                                        DBRef.setValue(user).addOnCompleteListener(task1 -> {

                                            if (task1.isSuccessful()){
                                                Intent intent = new Intent(RegisterHere.this, Login.class);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(RegisterHere.this, "Registration failed. Try again ", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(RegisterHere.this, MainActivity.class);
                                                startActivity(intent);
                                            }
                                        });
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterHere.this, "Verification Email has not been sent !", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else{
                                Toast.makeText(RegisterHere.this, "Failed to register", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
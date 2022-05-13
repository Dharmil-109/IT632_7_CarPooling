package com.ds.carpooling;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ViewProfile extends AppCompatActivity {

    EditText fname,lname,email,phone,address;
    String name,lastname,mail,mobile,gen,add;
    Button btnUpdate;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        fname = findViewById(R.id.edt_firstname);
        lname = findViewById(R.id.edt_lastname);
        email = findViewById(R.id.edt_email);
        phone = findViewById(R.id.edt_phone);
        address = findViewById(R.id.edt_address);
        btnUpdate = findViewById(R.id.btn_update);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Registration");
        SharedPreferences sp = getApplicationContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
        sp.getString("email", "");
        sp.getString("password", "");
        // Initialising the text view and imageview
        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    name = "" + dataSnapshot1.child("fname").getValue();
                    lastname = "" + dataSnapshot1.child("lname").getValue();
                    mail = "" + dataSnapshot1.child("email").getValue();
                    mobile = "" + dataSnapshot1.child("phoneNo").getValue();
                    add = "" + dataSnapshot1.child("address").getValue();

                    fname.setText(name);
                    lname.setText(lastname);
                    email.setText(mail);
                    phone.setText(mobile);
                    address.setText(add);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ViewProfile.this, "Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package com.ds.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ds.carpooling.model.OfferRideClass;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OfferRide extends AppCompatActivity {

    private Object offerRide;
    private EditText sourceAdd, destinationAdd;
    private Spinner spinnerSeats, spinnerLuggage;
    private Button btnOfferRide;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);

        sourceAdd = findViewById(R.id.edt_source_add);
        destinationAdd = findViewById(R.id.edt_destination_add);
        spinnerSeats = findViewById(R.id.spinner_seats);
        spinnerLuggage = findViewById(R.id.spinner_luggage);
        btnOfferRide = findViewById(R.id.btn_offer_ride);
        mAuth = FirebaseAuth.getInstance();

        btnOfferRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                offerTheRide();
            }

            private void offerTheRide() {

                String srcAdd = sourceAdd.getText().toString().trim();
                String destAdd = destinationAdd.getText().toString().trim();
                String seats = spinnerSeats.getSelectedItem().toString().trim();
                String luggage = spinnerLuggage.getSelectedItem().toString().trim();

                if (srcAdd.isEmpty()) {
                    sourceAdd.setError("Source Address is Required");
                    sourceAdd.requestFocus();
                    return;
                }

                if (destAdd.isEmpty()) {
                    destinationAdd.setError("Destination Address is Required");
                    destinationAdd.requestFocus();
                    return;
                }

                OfferRideClass offerRideClass = new OfferRideClass(srcAdd,destAdd,seats,luggage);

                FirebaseDatabase database=FirebaseDatabase.getInstance();
                DatabaseReference DBRef=database.getReference("OfferRide").child(mAuth.getCurrentUser().getUid());
                DBRef.setValue(offerRideClass).addOnCompleteListener(task -> {

                    if (task.isSuccessful()){
                        Toast.makeText(OfferRide.this, "Ride Added Successfully", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(OfferRide.this, "Ride is not Added. Please try Again!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        //Seats Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeats.setAdapter(adapter);
        spinnerSeats.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) offerRide);

        //Luggage Spinner
        ArrayAdapter<CharSequence> adapterLuggage = ArrayAdapter.createFromResource(this,
                R.array.option, android.R.layout.simple_spinner_item);
        adapterLuggage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLuggage.setAdapter(adapterLuggage);
        spinnerLuggage.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) offerRide);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
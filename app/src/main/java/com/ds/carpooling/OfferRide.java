package com.ds.carpooling;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class OfferRide extends AppCompatActivity {

    private Object offerRide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_ride);

        Spinner spinnerSeats = findViewById(R.id.spinner_seats);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSeats.setAdapter(adapter);
        spinnerSeats.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) offerRide);

        Spinner spinnerOption = findViewById(R.id.spinner_luggage);
        ArrayAdapter<CharSequence> adapterLuggage = ArrayAdapter.createFromResource(this,
                R.array.option, android.R.layout.simple_spinner_item);
        adapterLuggage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOption.setAdapter(adapterLuggage);
        spinnerOption.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) offerRide);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected(AdapterView<?> parent) {
    }
}
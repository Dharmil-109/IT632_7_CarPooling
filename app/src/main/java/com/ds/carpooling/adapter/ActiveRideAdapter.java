package com.ds.carpooling.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ds.carpooling.model.ActiveRideDetails;
import com.ds.carpooling.R;
import com.ds.carpooling.model.RequestRide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActiveRideAdapter extends RecyclerView.Adapter<ActiveRideAdapter.MyViewHolder> {

    Context context;
    ArrayList<ActiveRideDetails> list;

    public ActiveRideAdapter(Context context, ArrayList<ActiveRideDetails> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ActiveRideAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.active_ride_item, parent, false);
        return new ActiveRideAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActiveRideAdapter.MyViewHolder holder, int position) {
        ActiveRideDetails activeRideDetails = list.get(position);
        holder.sourceAddField.setText(activeRideDetails.getSourceAdd());
        holder.destinationAddField.setText(activeRideDetails.getDestinationAdd());
        holder.seatsField.setText(activeRideDetails.getSeats());
        holder.luggageField.setText(activeRideDetails.getLuggage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView sourceAddField, destinationAddField, seatsField, luggageField;
        Button request;
        String name, contact;

        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;
        FirebaseDatabase firebaseDatabase;
        DatabaseReference databaseReference;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceAddField = itemView.findViewById(R.id.tv_item_sourceAddField);
            destinationAddField = itemView.findViewById(R.id.tv_item_destinationAddField);
            seatsField = itemView.findViewById(R.id.tv_item_totalSeatsField);
            luggageField = itemView.findViewById(R.id.tv_item_luggageField);
            request = itemView.findViewById(R.id.btn_request);

            //new
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("Registration");
            SharedPreferences sp = itemView.getContext().getSharedPreferences("MyUserPrefs", Context.MODE_PRIVATE);
            sp.getString("email", "");
            sp.getString("password", "");
            // Initialising the text view and imageview
            Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        name = "" + dataSnapshot1.child("fname").getValue();
                        contact = "" + dataSnapshot1.child("phoneNo").getValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


            request.setOnClickListener(new View.OnClickListener() {
                private String TAG;

                @Override
                public void onClick(View view) {

                    String sourceAdd = sourceAddField.getText().toString().trim();
                    String destinationAdd = destinationAddField.getText().toString().trim();

                    RequestRide requestRide = new RequestRide(name, contact, sourceAdd, destinationAdd);

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference DBRef = database.getReference("Request Ride").child(firebaseAuth.getCurrentUser().getUid());
                    DBRef.setValue(requestRide).addOnCompleteListener(task -> {

                        if (task.isSuccessful()) {
                            Toast.makeText(view.getContext(), "Requested", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(view.getContext(), "Please try again later!!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

        }
    }

}

package com.ds.carpooling.rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ds.carpooling.adapter.ActiveRideAdapter;
import com.ds.carpooling.model.ActiveRideDetails;
import com.ds.carpooling.R;
import com.ds.carpooling.databinding.FragmentActiveRideBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActiveRideFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ActiveRideAdapter adapter;
    ArrayList<ActiveRideDetails> list;

    private FragmentActiveRideBinding binding;

    public ActiveRideFragment() {
    }


    public static ActiveRideFragment newInstance(String param1, String param2) {
        ActiveRideFragment fragment = new ActiveRideFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentActiveRideBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.rv_request_rides);
        databaseReference = FirebaseDatabase.getInstance().getReference("OfferRide");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        adapter= new ActiveRideAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    ActiveRideDetails activeRideDetails = dataSnapshot.getValue(ActiveRideDetails.class);
                    list.add(activeRideDetails);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return root;
    }
}
package com.ds.carpooling.rides;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ds.carpooling.R;
import com.ds.carpooling.adapter.RequestRideAdapter;
import com.ds.carpooling.databinding.FragmentActiveRideBinding;
import com.ds.carpooling.databinding.FragmentRequestRideBinding;
import com.ds.carpooling.model.RequestRide;
import com.ds.carpooling.model.RequestRideClass;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class RequestRideFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private FragmentRequestRideBinding binding;

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    RequestRideAdapter adapter;
    ArrayList<RequestRideClass> list;


    public RequestRideFragment() {
    }

    public static RequestRideFragment newInstance(String param1, String param2) {
        RequestRideFragment fragment = new RequestRideFragment();
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
        binding = FragmentRequestRideBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.rv_request_rides);
        databaseReference = FirebaseDatabase.getInstance().getReference("Request Ride");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        adapter = new RequestRideAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    RequestRideClass requestRide = dataSnapshot.getValue(RequestRideClass.class);
                    list.add(requestRide);
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
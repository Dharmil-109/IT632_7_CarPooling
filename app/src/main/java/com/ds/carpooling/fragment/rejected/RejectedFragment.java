package com.ds.carpooling.fragment.rejected;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ds.carpooling.R;
import com.ds.carpooling.adapter.ApproveAdapter;
import com.ds.carpooling.adapter.RejectedAdapter;
import com.ds.carpooling.databinding.FragmentRejectedBinding;
import com.ds.carpooling.model.CarDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RejectedFragment extends Fragment {

    private FragmentRejectedBinding binding;
    private RecyclerView recyclerView;
    private DatabaseReference database;
    private RejectedAdapter rejectedAdapter;
    private ArrayList<CarDetails> list;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RejectedViewModel notificationsViewModel =
                new ViewModelProvider(this).get(RejectedViewModel.class);

        binding = FragmentRejectedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = root.findViewById(R.id.rv_rejectedList);
        database = FirebaseDatabase.getInstance().getReference("CarDetails");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        rejectedAdapter = new RejectedAdapter(getContext(), list);
        recyclerView.setAdapter(rejectedAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CarDetails carDetails = dataSnapshot.getValue(CarDetails.class);
                    list.add(carDetails);
                }
                rejectedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
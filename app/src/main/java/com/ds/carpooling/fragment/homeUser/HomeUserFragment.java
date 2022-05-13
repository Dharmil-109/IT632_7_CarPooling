package com.ds.carpooling.fragment.homeUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ds.carpooling.MapsActivity;
import com.ds.carpooling.OfferRide;
import com.ds.carpooling.R;
import com.ds.carpooling.databinding.FragmentHomeUserBinding;

public class HomeUserFragment extends Fragment {

    private FragmentHomeUserBinding userbinding;
    Button btnOfferRide, btnTakeRide;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeUserViewModel notificationsViewModel =
                new ViewModelProvider(this).get(HomeUserViewModel.class);

        userbinding = FragmentHomeUserBinding.inflate(inflater, container, false);
        View root = userbinding.getRoot();

        //btn set and initialize
        btnOfferRide = root.findViewById(R.id.btn_OfferRide);
        btnOfferRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), OfferRide.class);
                startActivity(i);
            }
        });

        btnTakeRide = root.findViewById(R.id.btn_TakeRide);
        btnTakeRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), MapsActivity.class);
                startActivity(i);
            }
        });

//        final TextView textView = binding.textHome;
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        userbinding = null;
    }
}
package com.ds.carpooling.fragment.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ds.carpooling.AddCar;
import com.ds.carpooling.R;
import com.ds.carpooling.ViewProfile;
import com.ds.carpooling.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    Button btnAddCar,btnViewProfile;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnViewProfile = root.findViewById(R.id.btn_View_Profile);
        btnViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ViewProfile.class);
                startActivity(i);
            }
        });

        btnAddCar = root.findViewById(R.id.btn_AddCarDetails);
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddCar.class);
                startActivity(i);
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
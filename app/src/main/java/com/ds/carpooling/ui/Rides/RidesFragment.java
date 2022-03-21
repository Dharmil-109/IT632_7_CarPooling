package com.ds.carpooling.ui.Rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ds.carpooling.databinding.FragmentRidesBinding;

public class RidesFragment extends Fragment {

    private FragmentRidesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RidesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(RidesViewModel.class);

        binding = FragmentRidesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRides;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
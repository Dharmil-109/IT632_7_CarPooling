package com.ds.carpooling.fragment.rides;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.ds.carpooling.databinding.FragmentRidesBinding;
import com.ds.carpooling.rides.RideAdapter;
import com.google.android.material.tabs.TabLayout;

public class RidesFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private FragmentRidesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RidesViewModel dashboardViewModel =
                new ViewModelProvider(this).get(RidesViewModel.class);

        binding = FragmentRidesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tabLayout = root.findViewById(com.ds.carpooling.R.id.tab_layout);
        viewPager = root.findViewById(com.ds.carpooling.R.id.view_pager);

        RideAdapter adapter = new RideAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

       /* final TextView textView = binding.textRides;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
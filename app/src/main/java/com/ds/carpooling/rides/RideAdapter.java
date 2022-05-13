package com.ds.carpooling.rides;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;


public class RideAdapter extends FragmentStatePagerAdapter {

    public RideAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new RequestRideFragment();

            case 1:
                return new ActiveRideFragment();

            default:
                return new RequestRideFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Request Rides";

            case 1:
                return "Active Rides";

            default:
                return "Request Rides";
        }
    }
}

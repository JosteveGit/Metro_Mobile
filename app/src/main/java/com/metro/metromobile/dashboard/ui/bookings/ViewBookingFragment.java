package com.metro.metromobile.dashboard.ui.bookings;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metro.metromobile.R;


public class ViewBookingFragment extends Fragment {


    public ViewBookingFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_booking, container, false);
    }

}
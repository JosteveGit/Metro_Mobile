package com.metro.metromobile.dashboard.ui.bookings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.ViewPagerAdapter;

public class BookingFragment extends Fragment {
    CardView addBooking;
    CardView viewBooking;

    private BookingViewModel bookingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bookingViewModel = ViewModelProviders.of(this).get(BookingViewModel.class);
        View view = inflater.inflate(R.layout.fragment_booking, container, false);

        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pagerB);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Add Fragments to adapter one by one
        adapter.addFragment(new ActiveBookingFrag(), "My Bookings");
        adapter.addFragment(new NewBookingFrag(), "New Booking");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabsB);
        tabLayout.setupWithViewPager(viewPager);

//        addBooking = view.findViewById(R.id.addBooking);
//        viewBooking = view.findViewById(R.id.viewBooking);
//        addBooking.setOnClickListener(view1 -> {
//            if (getFragmentManager() != null) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.nav_host_fragment, new AddBookingFragment())
//                        .addToBackStack(null).commit();
//            }
//        });
//
//        viewBooking.setOnClickListener(view12 -> {
//            if (getFragmentManager() != null) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.nav_host_fragment, new ViewBookingFragment())
//                        .addToBackStack(null).commit();
//            }
//        });

//        final TextView textView = root.findViewById(R.id.text_history);
//        bookingViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return view;
    }
}
package com.metro.metromobile.dashboard.ui.cars;

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

public class CarFragment extends Fragment {

    private CarViewModel carViewModel;
    CardView addCar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        carViewModel =
                ViewModelProviders.of(this).get(CarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_cars, container, false);
        ViewPager viewPager = (ViewPager) root.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        // Add Fragments to adapter one by one
        adapter.addFragment(new CarsFrag(), "My Cars");
        adapter.addFragment(new AddCarFragment(), "Add Car");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
//        addCar = root.findViewById(R.id.addCar);
//        addCar.setOnClickListener(view -> {
//            if (getFragmentManager() != null) {
//                getFragmentManager().beginTransaction()
//                        .replace(R.id.nav_host_fragment, new AddCarFragment())
//                        .addToBackStack(null).commit();
//            }
//        });
        return root;
    }
}
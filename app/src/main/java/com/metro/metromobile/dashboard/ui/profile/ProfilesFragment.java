package com.metro.metromobile.dashboard.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.metro.metromobile.R;
import com.metro.metromobile.dashboard.ViewPagerAdapter;

public class ProfilesFragment extends Fragment {
    private ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profiles,container,false);
        ViewPager viewPager = root.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter((getChildFragmentManager()));

        adapter.addFragment(new EditProfileFragment(),"EDIT PROFILE" );
        adapter.addFragment(new ChangePasswordFragment(),"CHANGE PASSWORD");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = root.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        return root;
    }
}

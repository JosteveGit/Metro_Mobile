package com.metro.metromobile.introduction.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.metro.metromobile.introduction.fragment.OnBoardingFragment;

public class OnboardingAdapter extends FragmentPagerAdapter {

    public OnboardingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return OnBoardingFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 3;
    }
}
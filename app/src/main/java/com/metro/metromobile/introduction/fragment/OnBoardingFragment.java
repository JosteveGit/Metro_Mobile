package com.metro.metromobile.introduction.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.metro.metromobile.R;
import com.metro.metromobile.shared.base.BaseFragment;

public class OnBoardingFragment extends BaseFragment {

    private static final String PAGE = "page";

    private int page;

    public static OnBoardingFragment newInstance(int page) {
        OnBoardingFragment fragment = new OnBoardingFragment();

        Bundle b = new Bundle();
        b.putInt(PAGE, page);

        fragment.setArguments(b);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            page = getArguments().getInt(PAGE);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.setTag(page);
    }

    @Override
    protected int getFragmentLayout() {
        switch (page) {
            case 0:
            default:
                return R.layout.welcome_slide;
            case 1:
                return R.layout.welcome_slide_2;
            case 2:
                return R.layout.welcome_slide_3;
        }
    }
}
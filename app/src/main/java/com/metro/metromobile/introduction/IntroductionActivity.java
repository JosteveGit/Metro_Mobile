package com.metro.metromobile.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.viewpager.widget.ViewPager;

import com.metro.metromobile.activities.ForgotPasswordActivity;
import com.metro.metromobile.R;
import com.metro.metromobile.activities.LoginActivity;
import com.metro.metromobile.introduction.adapter.OnboardingAdapter;
import com.metro.metromobile.shared.base.BaseActivity;
import com.metro.metromobile.utils.manager.SessionManager;
import com.metro.metromobile.utils.ui.OnBoardingPageTransformer;
import com.pixelcan.inkpageindicator.InkPageIndicator;

import butterknife.BindView;
import butterknife.OnClick;

public class IntroductionActivity extends BaseActivity implements
        ViewPager.OnPageChangeListener {

    @BindView(R.id.onboarding_view_pager)
    ViewPager onBoardingViewPager;

    @BindView(R.id.indicator)
    InkPageIndicator indicator;

    @BindView(R.id.skip_btn)
    Button btnSkip;

    @BindView(R.id.next_page)
    FrameLayout btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        onBoardingViewPager.setAdapter(new OnboardingAdapter(getSupportFragmentManager()));
        onBoardingViewPager.setPageTransformer(false, new OnBoardingPageTransformer());
        onBoardingViewPager.addOnPageChangeListener(this);
        indicator.setViewPager(onBoardingViewPager);
    }

    private int getItem() {
        return onBoardingViewPager.getCurrentItem() + 1;
    }

    // Click Methods-------------------------------------------------------------------------------
    @OnClick(R.id.skip_btn)
    void onSkipButtonClicked() {
        SessionManager.setSeenLanding();
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @OnClick(R.id.next_page)
    void doSwipeToNext() {
        onBoardingViewPager.setCurrentItem(getItem(), true);
    }

    // Overridden Methods---------------------------------------------------------------------------
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        if (position == 2) {
            btnSkip.setText(R.string.got_it);
            btnNext.setVisibility(View.GONE);
        } else {
            btnSkip.setText(R.string.skip);
            btnNext.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
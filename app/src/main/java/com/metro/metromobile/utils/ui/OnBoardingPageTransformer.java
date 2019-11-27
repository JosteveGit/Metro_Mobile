package com.metro.metromobile.utils.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.metro.metromobile.R;

public class OnBoardingPageTransformer implements ViewPager.PageTransformer {

    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        float pageWidthTimesPosition = pageWidth * position;

        if (position <= -1.0f || position >= 1.0f) {

            // Page is not visible -- stop any running animations

        } else if (position == 0.0f) {

            // Page is selected -- reset any views if necessary

        } else {

            // Page is currently being swiped -- perform animations here

            // Fragment 1
            final View text_vw_1 = page.findViewById(R.id.header_slide);
            if (text_vw_1 != null) text_vw_1.setTranslationX(pageWidthTimesPosition * 0.5f);

            final View text_vw_2 = page.findViewById(R.id.desc_slide);
            if (text_vw_2 != null) text_vw_2.setTranslationX(pageWidthTimesPosition * 0.7f);

            // Fragment 2
            final View slide_2_text_vw_1 = page.findViewById(R.id.header_slide_2);
            if (slide_2_text_vw_1 != null) slide_2_text_vw_1.setTranslationX(pageWidthTimesPosition
                    * 0.5f);

            final View slide_2_text_vw_2 = page.findViewById(R.id.desc_slide_2);
            if (slide_2_text_vw_2 != null) slide_2_text_vw_2.setTranslationX(pageWidthTimesPosition
                    * 0.7f);

            // Fragment 3
            final View slide_3_text_vw_1 = page.findViewById(R.id.header_slide_3);
            if (slide_3_text_vw_1 != null) slide_3_text_vw_1.setTranslationX(pageWidthTimesPosition
                    * 0.5f);

            final View slide_3_text_vw_2 = page.findViewById(R.id.desc_slide_3);
            if (slide_3_text_vw_2 != null) slide_3_text_vw_2.setTranslationX(pageWidthTimesPosition
                    * 0.7f);
        }
    }
}
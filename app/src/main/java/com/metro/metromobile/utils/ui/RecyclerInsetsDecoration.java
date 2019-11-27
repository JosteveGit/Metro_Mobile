package com.metro.metromobile.utils.ui;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerInsetsDecoration extends RecyclerView.ItemDecoration {

    private int mInsets;

    public RecyclerInsetsDecoration(int mInsets) {
        this.mInsets = mInsets;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
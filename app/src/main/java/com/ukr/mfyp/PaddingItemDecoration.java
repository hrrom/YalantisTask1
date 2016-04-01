package com.ukr.mfyp;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Provides padding for items of RecyclerView
 */

public class PaddingItemDecoration extends RecyclerView.ItemDecoration {

    private int mPadding;

    public PaddingItemDecoration(int padding){
        mPadding = padding;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        final int itemPosition = parent.getChildAdapterPosition(view);
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        final int itemCount = state.getItemCount();

        // First item
        if (itemPosition == 0) {
            outRect.set(0, 0, mPadding, 0);
        }
        // Last item
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(mPadding, 0, 0, 0);
        }
        // Positions between first and last items
        else {
            outRect.set(mPadding, 0, mPadding, 0);
        }
    }

}
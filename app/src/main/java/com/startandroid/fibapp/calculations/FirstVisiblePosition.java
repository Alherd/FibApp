package com.startandroid.fibapp.calculations;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class FirstVisiblePosition extends RecyclerView.OnScrollListener {
    private LinearLayoutManager linearLayoutManager;

    public FirstVisiblePosition(final LinearLayoutManager layoutManager) {
        this.linearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(final RecyclerView view, final int dx, final int dy) {
        super.onScrolled(view, dx, dy);
        int firstVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
        firstVisiblePositionChanged(firstVisibleItem);
    }

    public abstract void firstVisiblePositionChanged(final int firstVisibleNumber);
}

package com.startandroid.fibapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.startandroid.fibapp.R;

public final class FibonacciAdapter extends RecyclerView.Adapter<FibonacciHolder> {

    @Override
    public FibonacciHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.list_item_fibonacci, parent, false);
        return new FibonacciHolder(view);
    }

    @Override
    public void onBindViewHolder(final FibonacciHolder holder, final int POSITION) {
        holder.showFibonacciNumberTextView(POSITION);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}

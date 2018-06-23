package com.startandroid.fibapp.adapter;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.startandroid.fibapp.R;
import com.startandroid.fibapp.calculations.FibonacciCalculator;
import com.startandroid.fibapp.interfaces.Getter;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class FibonacciHolder extends RecyclerView.ViewHolder {
    private final TextView fibonacciNumberTextView;
    private final Handler handler = new Handler();
    private final Getter<Boolean> getterIsThreadLiving = new Getter<Boolean>() {
        @Override
        public Boolean get() {
            return isThreadLiving;
        }
    };
    private boolean isThreadLiving;
    private int taskForExecutorCounter = 0;

    public FibonacciHolder(View itemView) {
        super(itemView);
        fibonacciNumberTextView = itemView.findViewById(R.id.fibonacciNumber);
    }

    private void cancel() {
        isThreadLiving = false;
    }

    public void showFibonacciNumberTextView(final int FIBONACCI_INDEX) {
        fibonacciNumberTextView.setText("");
        taskForExecutorCounter++;
        if (taskForExecutorCounter == 1) {
            cancel();
        }

        ExecutorServiceCreator.getExecutorService().submit(new Runnable() {
            @Override
            public void run() {
                isThreadLiving = true;
                taskForExecutorCounter--;
                if (taskForExecutorCounter > 0) {
                    return;
                }
                setFibonacciOnTextView(FIBONACCI_INDEX);
            }
        });
    }

    private void setFibonacciOnTextView(final int FIBONACCI_INDEX) {
        final BigInteger bigInteger = FibonacciCalculator.calculateFibonacciNumberByIndex(FIBONACCI_INDEX, getterIsThreadLiving);
        if (bigInteger == null || !getterIsThreadLiving.get()) {
            return;
        }
        final int maxLengthWithoutDots = 20;
        final int halfMaxLengthWithoutDots = maxLengthWithoutDots / 2;
        StringBuilder firstStringBuilder = new StringBuilder();
        StringBuilder secondStringBuilder = new StringBuilder();
        if (bigInteger.toString().length() > maxLengthWithoutDots) {
            char[] charsFibonacciNumber = bigInteger.toString().toCharArray();
            for (int i = 0; i < halfMaxLengthWithoutDots; i++) {
                firstStringBuilder.append(charsFibonacciNumber[i]);
            }
            for (int i = charsFibonacciNumber.length - halfMaxLengthWithoutDots; i < charsFibonacciNumber.length; i++) {
                secondStringBuilder.append(charsFibonacciNumber[i]);
            }
            final StringBuilder resultStringBuilder = firstStringBuilder.append("...").append(secondStringBuilder);
            if (!getterIsThreadLiving.get()) {
                return;
            }
            setFibonacciNumberOnUiThread(resultStringBuilder.toString());
        } else {
            setFibonacciNumberOnUiThread(bigInteger.toString());
        }
    }

    private void setFibonacciNumberOnUiThread(final String CONCLUSION_STRING) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                fibonacciNumberTextView.setText(CONCLUSION_STRING);
            }
        });
    }
}

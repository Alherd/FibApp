package com.startandroid.fibapp.adapter;

import com.startandroid.fibapp.calculations.FibonacciCalculator;
import com.startandroid.fibapp.interfaces.Getter;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ExecutorServiceCreator {
    private static ExecutorService executorService = Executors.newSingleThreadExecutor();

    private ExecutorServiceCreator() {
    }

    public static ExecutorService getExecutorService() {
        return executorService;
    }
}

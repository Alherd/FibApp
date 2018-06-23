package com.startandroid.fibapp.utils;

import android.content.Context;
import android.widget.Toast;

public final class ToastShowing {
    public static void postToastMessage(final Context context, final int MESSAGE) {
        Toast.makeText(context, MESSAGE, Toast.LENGTH_LONG).show();
    }
}

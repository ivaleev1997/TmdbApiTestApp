package com.firebasetestapp.tmdbapitestapp.utils;

import android.content.SharedPreferences;
import android.os.SystemClock;

import com.firebasetestapp.tmdbapitestapp.AppConstants;

import java.util.concurrent.TimeUnit;

public class RateLimiter {

    private long timeOut;
    private SharedPreferences mSharedPreferences;

    public RateLimiter(int timeOut, TimeUnit timeUnit, SharedPreferences sharedPreferences) {
        this.timeOut = timeUnit.toMillis(timeOut);
        mSharedPreferences = sharedPreferences;
    }

    public boolean shouldUpdate() {
        long last = mSharedPreferences.getLong(AppConstants.RATE_LIMITER, 0);

        if (last == 0) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putLong(AppConstants.RATE_LIMITER, getNowMills());
            editor.apply();

            return true;
        }

        if (getNowMills() - last > timeOut) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.putLong(AppConstants.RATE_LIMITER, getNowMills());
            editor.apply();

            return true;
        }

        return false;
    }


    private long getNowMills() {
        return SystemClock.uptimeMillis();
    }
}

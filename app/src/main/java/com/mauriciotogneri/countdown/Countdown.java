package com.mauriciotogneri.countdown;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import io.fabric.sdk.android.Fabric;

public class Countdown extends Application
{
    private Tracker tracker;

    @Override
    public void onCreate()
    {
        super.onCreate();

        Fabric.with(this, new Crashlytics());

        GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
        this.tracker = analytics.newTracker(R.xml.app_tracker);
    }

    public Tracker getTracker()
    {
        return this.tracker;
    }
}
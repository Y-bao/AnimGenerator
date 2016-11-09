package com.ybao.library.converter;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.Spring;
import com.ybao.library.performer.Performer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Y-bao on 2016/11/4.
 */

public abstract class Converter {
    List<Performer> mPerformers;

    protected boolean mFollow = true;

    protected Spring mSpring;

    protected double mRestValue;


    public Converter() {
        this(null, null, 0, true);
    }

    public Converter(double restValue, boolean isFollow) {
        this(null, null, restValue, isFollow);
    }

    public Converter(@NonNull Spring spring) {
        this(spring, null, true);
    }

    public Converter(@NonNull Spring spring, Performer[] performers) {
        this(spring, performers, true);
    }

    public Converter(@NonNull Spring spring, Performer[] performers, boolean isFollow) {
        this(spring, performers, 0, isFollow);
    }

    public Converter(@NonNull Spring spring, Performer[] performers, double restValue, boolean isFollow) {
        mSpring = spring;
        mRestValue = restValue;
        mFollow = isFollow;
        addAllListeners(performers);
    }

    public void constrain(View view, MotionEvent event) {
        if (mSpring != null && isFollow()) {
            mSpring.setVelocity(0);
        }
    }

    public void release(View view, MotionEvent event) {
        if (mSpring != null) {
            mSpring.setEndValue(mRestValue);
        }
    }

    private void addAllListeners(Performer[] performers) {
        if (mSpring == null || performers == null) {
            return;
        }
        mPerformers = new ArrayList<>();
        for (Performer performer : performers) {
            mSpring.addListener(performer);
            mPerformers.add(performer);
        }
    }

    private void addAllListeners(List<Performer> performers) {
        if (mSpring == null || performers == null) {
            return;
        }
        mPerformers = new ArrayList<>();
        for (Performer performer : performers) {
            mSpring.addListener(performer);
            mPerformers.add(performer);
        }
    }

    protected abstract double mapToSpring(float motionValue);

    public boolean isFollow() {
        return mFollow;
    }

    public Converter setFollow(boolean isFollow) {
        this.mFollow = isFollow;
        return this;
    }

    public Converter setRestValue(double mRestValue) {
        this.mRestValue = mRestValue;
        return this;
    }

    public double getRestValue() {
        return mRestValue;
    }

    public Spring getSpring() {
        return mSpring;
    }

    public boolean hasSpring() {
        return mSpring != null;
    }

    public Converter setSpring(@NonNull final Spring spring) {
        mSpring = spring;
        if (mSpring != null) {
            // Start spring at rest.
            addAllListeners(mPerformers);
            mSpring.setCurrentValue(mRestValue, true);
        }
        return this;
    }

    public Converter addPerformer(@NonNull Performer performer) {
        if (mPerformers == null) {
            mPerformers = new ArrayList<>();
        }
        mPerformers.add(performer);
        if (mSpring != null) {
            mSpring.addListener(performer);
        }
        return this;
    }

}

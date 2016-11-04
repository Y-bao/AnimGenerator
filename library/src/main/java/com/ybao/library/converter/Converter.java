package com.ybao.library.converter;

import android.support.annotation.NonNull;

import com.facebook.rebound.Spring;

/**
 * Created by Y-bao on 2016/11/4.
 */

public abstract class Converter {

    protected boolean mFollow = true;

    @NonNull
    protected Spring mSpring;

    protected double mRestValue;

    protected Converter(final double restValue, final boolean isFollow) {
        this(null, restValue, isFollow);
    }

    protected Converter(@NonNull final Spring spring) {
        this(spring, true);
    }

    protected Converter(@NonNull final Spring spring, final boolean isFollow) {
        this(spring, spring.getEndValue(), isFollow);
    }

    protected Converter(@NonNull final Spring spring, final double restValue, final boolean isFollow) {
        mSpring = spring;
        mRestValue = restValue;
        mFollow = isFollow;
    }

    protected abstract double mapToSpring(final float motionValue);

    public boolean isFollow() {
        return mFollow;
    }

    @NonNull
    public void setFollow(boolean isFollow) {
        this.mFollow = isFollow;
    }

    @NonNull
    public Spring getSpring() {
        return mSpring;
    }

    public void setSpring(@NonNull final Spring spring) {
        mSpring = spring;

        if (mSpring != null) {
            // Start spring at rest.
            mSpring.setCurrentValue(mRestValue, true);
        }
    }
}

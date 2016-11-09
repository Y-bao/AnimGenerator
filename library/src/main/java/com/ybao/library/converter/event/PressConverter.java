package com.ybao.library.converter.event;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.Spring;
import com.ybao.library.MotionProperty;
import com.ybao.library.performer.Performer;

/**
 * Created by Y-bao on 2016/11/4.
 */

public class PressConverter extends EventConverter {
    private double mActiveValue;

    public PressConverter(double activeValue) {
        this(MotionProperty.S, 0, activeValue);
    }

    public PressConverter(double restValue, double activeValue) {
        this(MotionProperty.S, restValue, activeValue);
    }

    public PressConverter(@NonNull MotionProperty property, double activeValue) {
        this(property, 0, activeValue);
    }

    public PressConverter(@NonNull MotionProperty property, double restValue, double activeValue) {
        this(null, null, property, restValue, activeValue);
    }

    public PressConverter(@NonNull Spring spring, @NonNull MotionProperty property, double activeValue) {
        this(spring, null, property, activeValue);
    }

    public PressConverter(@NonNull Spring spring, Performer[] performers, @NonNull MotionProperty property, double activeValue) {
        this(spring, performers, property, 0, activeValue);
    }

    public PressConverter(@NonNull Spring spring, Performer[] performers, @NonNull MotionProperty property, double restValue, double activeValue) {
        super(spring, performers, property, restValue, true);
        mActiveValue = activeValue;
    }

    @Override
    protected double mapToSpring(float motionValue) {
        return mActiveValue;
    }


    public void constrain(final MotionEvent event) {
        if (mSpring != null) {
            mSpring.setEndValue(mActiveValue);
        }
    }

    @Override
    public void convert(View view, @NonNull MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                constrain(view,event);
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                release(view,event);
                break;

            default:
        }
    }
}

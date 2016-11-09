package com.ybao.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;


public enum MotionProperty {

    X(View.TRANSLATION_X),

    Y(View.TRANSLATION_Y),

    S(CustomProperty.SCALE),

    R(CustomProperty.TRANSLATION_R);

    @NonNull
    private final Property<View, Float> mViewProperty;

    private MotionProperty(@NonNull final Property<View, Float> viewProperty) {
        mViewProperty = viewProperty;
    }


    @NonNull
    public Property<View, Float> getViewProperty() {
        return mViewProperty;
    }


    public float getValue(@NonNull final View view) {
        return mViewProperty.get(view);
    }


    public float getDistance(@NonNull View view, @NonNull final MotionEvent event, @NonNull float[] point) {
        switch (this) {
            case X:
                return event.getX() + X.getValue(view) - point[0];
            case Y:
                return event.getY() + Y.getValue(view) - point[1];
            case R:
                return (float) Math.sqrt(Math.pow(event.getX(0) + X.getValue(view) - point[0], 2) + Math.pow(event.getY(0) + Y.getValue(view) - point[1], 2));
            default:
                return event.getX() + X.getValue(view) - point[0];
        }
    }

    public float getHistoricalDistance(@NonNull View view, @NonNull final MotionEvent event, @NonNull float[] point) {
        switch (this) {
            case X:
                return event.getHistoricalX(0) + X.getValue(view) - point[0];
            case Y:
                return event.getHistoricalY(0) + Y.getValue(view) - point[1];
            case R:
                return (float) Math.sqrt(Math.pow(event.getHistoricalX(0) + X.getValue(view) - point[0], 2) + Math.pow(event.getHistoricalY(0) + Y.getValue(view) - point[1], 2));
            default:
                return event.getHistoricalX(0) + X.getValue(view) - point[0];
        }
    }

    public float getValue(@Nullable final MotionEvent event) {
        if (event != null) {
            switch (this) {
                case X:
                    return event.getX();
                case Y:
                    return event.getY();
                default:
                    return event.getX();
            }
        }

        return 0;
    }


    public float getHistoricalValue(@Nullable final MotionEvent event, final int index) {
        if (event != null) {
            switch (this) {
                case X:
                    return event.getHistoricalX(index);
                case Y:
                    return event.getHistoricalY(index);
                default:
                    return 0;
            }
        }

        return 0;
    }


    public float getOldestValue(final MotionEvent event) {
        return getHistoricalValue(event, 0);
    }


    public float getOffset(@Nullable final View view) {
        if (view != null) {
            switch (this) {
                case X:
                    return -view.getWidth() / 2;
                case Y:
                    return -view.getHeight() / 2;
                default:
                    return -view.getWidth() / 2;
            }
        }

        return 0;
    }
}
package com.ybao.library.converter.event;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.Spring;
import com.ybao.library.MotionProperty;
import com.ybao.library.performer.Performer;

/**
 * Created by Y-bao on 2016/11/4.
 */

public class MoveConverter extends EventConverter {
    protected float[] mDownPosition;

    public MoveConverter(@NonNull MotionProperty property) {
        super(property);
    }

    public MoveConverter(@NonNull MotionProperty property, double restValue, boolean isFollow) {
        super(property, restValue, isFollow);
    }

    public MoveConverter(@NonNull Spring spring, @NonNull MotionProperty property) {
        super(spring, property);
    }

    public MoveConverter(@NonNull Spring spring, Performer[] performers, @NonNull MotionProperty property) {
        super(spring, performers, property);
    }

    public MoveConverter(@NonNull Spring spring, Performer[] performers, @NonNull MotionProperty property, boolean isFollow) {
        super(spring, performers, property, isFollow);
    }

    public MoveConverter(@NonNull Spring spring, Performer[] performers, @NonNull MotionProperty property, double restValue, boolean isFollow) {
        super(spring, performers, property, restValue, isFollow);
    }

    @Override
    protected double mapToSpring(float motionValue) {
        return motionValue;
    }


    public void constrain(View view, MotionEvent event) {
        super.constrain(view, event);
        mDownPosition = new float[]{event.getX(), event.getY()};
    }

    @Override
    public void release(View view, MotionEvent event) {
        if (event.getHistorySize() > 0) {
            float v = (mProperty.getDistance(view, event, mDownPosition) - mProperty.getHistoricalDistance(view, event, mDownPosition)) / (event.getEventTime() - event.getHistoricalEventTime(0));
            mSpring.setVelocity(Math.min(v, 500));
        }
        super.release(view, event);

    }

    public void mime(View view, final MotionEvent event) {
        if (mSpring != null) {
            final float eventValue = mProperty.getDistance(view, event, mDownPosition);
            mSpring.setEndValue(mapToSpring(eventValue));

            if (isFollow()) {
                mSpring.setCurrentValue(mSpring.getEndValue());
            }
        }
    }

    @Override
    public void convert(View view, @NonNull MotionEvent event) {
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    constrain(view,event);
                    break;
                case MotionEvent.ACTION_MOVE:
                    mime(view, event);
                    break;
                default:
                case MotionEvent.ACTION_UP:
                    release(view,event);
                    break;
            }
        }
    }
}

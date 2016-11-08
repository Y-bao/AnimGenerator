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
    protected float mDownPosition;

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


    public void constrain(final MotionEvent event) {
        if (mSpring != null && isFollow()) {
            mSpring.setVelocity(0);
        }
        mDownPosition = mProperty.getValue(event) + mOffset;
    }

    public void mime(float offset, final float value, final float delta, final float dt, final MotionEvent event) {
        if (mSpring != null) {
            mSpring.setEndValue(mapToSpring(offset + value - mDownPosition));

            if (isFollow()) {
                mSpring.setCurrentValue(mSpring.getEndValue());

                if (dt > 0) {
                    mSpring.setVelocity(delta / dt);
                }
            }
        }
    }

    public void release(final MotionEvent event) {
        if (mSpring != null) {
            mSpring.setEndValue(mRestValue);
        }
    }

    @Override
    public void convert(View view, @NonNull MotionEvent event) {
        final float viewValue = mProperty.getValue(view);
        final float eventValue = mProperty.getValue(event);
        mOffset = mProperty.getOffset(view);

        if (event.getHistorySize() > 0) {
            final float historicalValue = mProperty.getOldestValue(event);

            convert(viewValue + mOffset, eventValue, eventValue - historicalValue, event);
        } else {
            convert(viewValue + mOffset, eventValue, 0, event);
        }
    }

    public void convert(final float offset, final float value, final float delta, @Nullable final MotionEvent event) {
        if (event != null) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    constrain(event);
                case MotionEvent.ACTION_MOVE:
                    if (event.getHistorySize() > 0) {
                        mime(offset, value, delta, event.getEventTime() - event.getHistoricalEventTime(0), event);
                    } else {
                        mime(offset, value, delta, 0, event);
                    }

                    break;
                default:
                case MotionEvent.ACTION_UP:
                    release(event);
                    break;
            }
        }
    }
}

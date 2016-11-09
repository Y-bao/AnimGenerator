package com.ybao.library.converter.event;

import android.support.annotation.NonNull;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.Spring;
import com.ybao.library.MotionProperty;
import com.ybao.library.converter.Converter;
import com.ybao.library.performer.MapPerformer;
import com.ybao.library.performer.Performer;

/**
 * Created by Y-bao on 2016/11/8.
 */

public abstract class EventConverter extends Converter {
    MotionProperty mProperty;

    public EventConverter(@NonNull final MotionProperty property) {
        this(null, null, property, 0, true);
    }

    public EventConverter(@NonNull final MotionProperty property, double restValue, boolean isFollow) {
        this(null, null, property, restValue, isFollow);
    }

    public EventConverter(Spring spring, @NonNull final MotionProperty property) {
        this(spring, null, property);
    }

    public EventConverter(Spring spring, Performer[] performers, @NonNull final MotionProperty property) {
        this(spring, performers, property, true);
    }

    public EventConverter(Spring spring, Performer[] performers, @NonNull final MotionProperty property, boolean isFollow) {
        this(spring, performers, property, 0, isFollow);
    }

    public EventConverter(Spring spring, Performer[] performers, @NonNull final MotionProperty property, double restValue, boolean isFollow) {
        super(spring, performers, restValue, isFollow);
        mProperty = property;
    }

    public abstract void convert(final View view, @NonNull final MotionEvent event);

    @Override
    public EventConverter setFollow(boolean isFollow) {
        super.setFollow(isFollow);
        return this;
    }

    @Override
    public EventConverter setRestValue(double mRestValue) {
        super.setRestValue(mRestValue);
        return this;
    }

    @Override
    public EventConverter setSpring(@NonNull Spring spring) {
        super.setSpring(spring);
        return this;
    }

    @Override
    public EventConverter addPerformer(@NonNull Performer performer) {
        super.addPerformer(performer);
        return this;
    }

    public EventConverter addPerformer(@NonNull View target) {
        Performer performer = new Performer(target, mProperty.getViewProperty());
        return addPerformer(performer);
    }

    public EventConverter addPerformer(@NonNull View target, @NonNull final Property<View, Float> property) {
        Performer performer = new Performer(target, property);
        return addPerformer(performer);
    }

    public EventConverter addMapPerformer(@NonNull final View target) {
        MapPerformer performer = new MapPerformer(target, mProperty.getViewProperty());
        return addPerformer(performer);
    }

    public EventConverter addMapPerformer(@NonNull final View target, @NonNull final Property<View, Float> property) {
        MapPerformer performer = new MapPerformer(target, property);
        return addPerformer(performer);
    }

    public EventConverter addMapPerformer(@NonNull final View target, final float initialStart, final float initialEnd, final float start, final float end) {
        MapPerformer performer = new MapPerformer(target, mProperty.getViewProperty(), initialStart, initialEnd, start, end);
        return addPerformer(performer);
    }

    public EventConverter addMapPerformer(@NonNull final View target, @NonNull final Property<View, Float> property, final float initialStart, final float initialEnd, final float start, final float end) {
        MapPerformer performer = new MapPerformer(target, property, initialStart, initialEnd, start, end);
        return addPerformer(performer);
    }
}

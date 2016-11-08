package com.ybao.library;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;

import com.facebook.rebound.SpringSystem;
import com.ybao.library.converter.event.EventConverter;

import java.util.ArrayList;
import java.util.List;


public class Generator {
    List<EventConverter> converters;
    MotionListener motionListener;

    public Generator(List<EventConverter> converters) {
        this.converters = converters;
        this.motionListener = new MotionListener();
    }

    public List<EventConverter> getConverters() {
        return converters;
    }

    public MotionListener getMotionListener() {
        return motionListener;
    }

    private class MotionListener implements View.OnTouchListener {
        @Override
        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouch(@NonNull View v, @NonNull MotionEvent event) {
            for (EventConverter converter : converters) {
                converter.convert(v, event);
            }
            return true;
        }
    }

    public static class Builder {
        @NonNull
        private List<EventConverter> mConverters = new ArrayList<EventConverter>();
        @NonNull
        private SpringSystem mSpringSystem;


        public Builder(@NonNull SpringSystem springSystem) {
            mSpringSystem = springSystem;
        }

        public Builder addConverter(EventConverter converter) {
            mConverters.add(converter);
            if (!converter.hasSpring()) {
                converter.setSpring(mSpringSystem.createSpring());
            }
            return this;
        }


        @NonNull
        public Generator build() {
            return new Generator(mConverters);
        }
    }
}

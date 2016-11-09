package com.ybao.library.performer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.View;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;


/**
 * Created by Y-bao on 2016/11/8.
 */

public class SpringPerformer implements SpringListener {


    @Nullable
    protected Spring mSpring;


    public SpringPerformer(@NonNull Spring spring) {
        this.mSpring = spring;
    }


    @Override
    public void onSpringUpdate(@NonNull final Spring spring) {
        mSpring.setEndValue(spring.getCurrentValue());
    }

    @Override
    public void onSpringAtRest(final Spring spring) {

    }

    @Override
    public void onSpringActivate(final Spring spring) {

    }

    @Override
    public void onSpringEndStateChange(final Spring spring) {

    }
}

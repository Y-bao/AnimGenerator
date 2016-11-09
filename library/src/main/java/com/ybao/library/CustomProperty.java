package com.ybao.library;

import android.util.FloatProperty;
import android.util.Property;
import android.view.View;

/**
 * Created by Y-bao on 2016/11/8.
 */

public class CustomProperty {
    public static final Property<View, Float> TRANSLATION_R = new FloatProperty<View>("translationR") {
        @Override
        public void setValue(View object, float value) {
        }

        @Override
        public Float get(View object) {
            return (float) Math.sqrt(Math.pow(object.getTranslationX(), 2) + Math.pow(object.getTranslationY(), 2));
        }
    };

    public static final Property<View, Float> SCALE = new FloatProperty<View>("SCALE") {
        @Override
        public void setValue(View object, float value) {
            object.setScaleX(value);
            object.setScaleY(value);
        }

        @Override
        public Float get(View object) {
            return object.getScaleX();
        }
    };
}

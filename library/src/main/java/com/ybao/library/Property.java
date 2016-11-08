package com.ybao.library;

import android.util.FloatProperty;
import android.view.View;

/**
 * Created by Y-bao on 2016/11/8.
 */

public class Property {
    public static final android.util.Property<View, Float> TRANSLATION_R = new FloatProperty<View>("translationR") {
        @Override
        public void setValue(View object, float value) {
            object.setTranslationY(value);
        }

        @Override
        public Float get(View object) {
            return object.getTranslationY();
        }
    };
}

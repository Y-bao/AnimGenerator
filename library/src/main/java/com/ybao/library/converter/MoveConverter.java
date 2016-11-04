package com.ybao.library.converter;

/**
 * Created by Y-bao on 2016/11/4.
 */

public class MoveConverter extends Converter {
    @Override
    protected double mapToSpring(float motionValue) {
        return motionValue;
    }
}

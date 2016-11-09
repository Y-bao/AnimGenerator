package com.ybao.animgenerator;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.rebound.SpringSystem;
import com.ybao.library.Generator;
import com.ybao.library.converter.event.PressConverter;
import com.ybao.library.performer.MapPerformer;

/**
 * Created by Y-bao on 2016/11/9.
 */
public class PressFlowerFragment extends Fragment {
    private static final int DIAMETER = 80;
    private static final int RING_DIAMETER = 5 * DIAMETER;
    private RelativeLayout mRootView;
    private View[] mCircles;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = (RelativeLayout) inflater.inflate(R.layout.fragment_press_follow, container, false);
        createView();

        double arc = 2 * Math.PI / mCircles.length;
        PressConverter moveConverter = new PressConverter(1);

        for (int i = 0; i < mCircles.length; i++) {
            View view = mCircles[i];
            moveConverter.addPerformer(new MapPerformer(view, View.TRANSLATION_X, 0, 1, 0, (float) (RING_DIAMETER * Math.cos(i * arc)))).addPerformer(new MapPerformer(view, View.TRANSLATION_Y, 0, 1, 0, -(float) (RING_DIAMETER * Math.sin(i * arc))));
        }

        mCircles[mCircles.length - 1].setOnTouchListener(new Generator.Builder(SpringSystem.create()).addConverter(moveConverter).build().getMotionListener());
        return mRootView;
    }


    private void createView() {
        mCircles = new View[6];
        float diameter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DIAMETER,
                getResources().getDisplayMetrics());

        final TypedArray circles = getResources().obtainTypedArray(R.array.circles);

        // layout params
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams((int) diameter,
                (int) diameter);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);

        // create the circle views
        int colorIndex = 0;
        for (int i = 0; i < mCircles.length; i++) {
            mCircles[i] = new View(getActivity());

            mCircles[i].setLayoutParams(params);

            mCircles[i].setBackgroundDrawable(getResources().getDrawable(circles
                    .getResourceId(colorIndex, -1)));

            colorIndex++;
            if (colorIndex >= circles.length()) {
                colorIndex = 0;
            }

            mRootView.addView(mCircles[i]);
        }

        circles.recycle();
    }
}

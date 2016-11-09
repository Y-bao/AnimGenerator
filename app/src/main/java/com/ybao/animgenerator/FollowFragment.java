package com.ybao.animgenerator;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringSystem;
import com.ybao.library.Generator;
import com.ybao.library.MotionProperty;
import com.ybao.library.converter.event.MoveConverter;
import com.ybao.library.converter.event.PressConverter;
import com.ybao.library.performer.Performer;
import com.ybao.library.performer.SpringPerformer;

/**
 * Created by Y-bao on 2016/11/9.
 */
public class FollowFragment extends Fragment {
    private static final int DIAMETER = 80;

    private ViewGroup mRootView;
    private View mCircle;
    private View[] mFollowers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_follow, container, false);
        mCircle = mRootView.findViewById(R.id.circle);
        createView();
        final SpringSystem springSystem = SpringSystem.create();

        final Spring springX = springSystem.createSpring();
        final Spring springY = springSystem.createSpring();

        // add springs to connect between the views
        final Spring[] followsX = new Spring[mFollowers.length];
        final Spring[] followsY = new Spring[mFollowers.length];

        for (int i = 0; i < mFollowers.length; i++) {

            // create spring to bind views
            followsX[i] = springSystem.createSpring();
            followsY[i] = springSystem.createSpring();
            followsX[i].addListener(new Performer(mFollowers[i], View.TRANSLATION_X));
            followsY[i].addListener(new Performer(mFollowers[i], View.TRANSLATION_Y));

            // imitates another character
            final SpringPerformer followX = new SpringPerformer(followsX[i]);
            final SpringPerformer followY = new SpringPerformer(followsY[i]);

            //  imitate the previous character
            if (i == 0) {
                springX.addListener(followX);
                springY.addListener(followY);
            } else {
                followsX[i - 1].addListener(followX);
                followsY[i - 1].addListener(followY);
            }
        }

        View circle = mRootView.findViewById(R.id.circle);
        circle.setOnTouchListener(new Generator.Builder(SpringSystem.create())
                .addConverter(new MoveConverter(springX, MotionProperty.X))
                .addConverter(new MoveConverter(springY, MotionProperty.Y))
                .build().getMotionListener());
        return mRootView;
    }

    private void createView() {
        mFollowers = new View[6];
        float diameter = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DIAMETER,
                getResources().getDisplayMetrics());

        FrameLayout.LayoutParams leaderParams = (FrameLayout.LayoutParams) mCircle
                .getLayoutParams();

        final TypedArray circles = getResources().obtainTypedArray(R.array.circles);

        // layout params
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams((int) diameter,
                (int) diameter);
        params.gravity = leaderParams.gravity;

        // create the circle views
        int colorIndex = 0;
        for (int i = 0; i < mFollowers.length; i++) {
            mFollowers[i] = new View(getActivity());

            mFollowers[i].setLayoutParams(params);

            mFollowers[i].setBackgroundDrawable(getResources().getDrawable(circles
                    .getResourceId(colorIndex, -1)));

            colorIndex++;
            if (colorIndex >= circles.length()) {
                colorIndex = 0;
            }

            mRootView.addView(mFollowers[i]);
        }

        circles.recycle();
    }
}

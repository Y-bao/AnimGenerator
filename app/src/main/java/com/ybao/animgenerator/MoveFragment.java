package com.ybao.animgenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.rebound.SpringSystem;
import com.ybao.library.Generator;
import com.ybao.library.MotionProperty;
import com.ybao.library.converter.event.MoveConverter;

/**
 * Created by Y-bao on 2016/11/9.
 */
public class MoveFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_move, container, false);
        View circle = rootView.findViewById(R.id.circle);
        circle.setOnTouchListener(new Generator.Builder(SpringSystem.create())
                .addConverter(new MoveConverter(MotionProperty.X).addPerformer(circle))
                .addConverter(new MoveConverter(MotionProperty.Y).addPerformer(circle))
                .build().getMotionListener());
        return rootView;
    }
}

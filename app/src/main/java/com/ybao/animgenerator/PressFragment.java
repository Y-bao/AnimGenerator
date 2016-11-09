package com.ybao.animgenerator;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.rebound.SpringSystem;
import com.ybao.library.Generator;
import com.ybao.library.converter.event.PressConverter;
/**
 * Created by Y-bao on 2016/11/9.
 */
public class PressFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_press, container, false);
        View circle = rootView.findViewById(R.id.circle);
        circle.setOnTouchListener(new Generator.Builder(SpringSystem.create())
                .addConverter(new PressConverter(1, 0.5).addPerformer(circle))
                .build().getMotionListener());
        return rootView;
    }
}

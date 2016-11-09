package com.ybao.animgenerator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.rebound.SpringSystem;
import com.ybao.library.Generator;
import com.ybao.library.MotionProperty;
import com.ybao.library.converter.event.MoveConverter;
import com.ybao.library.performer.MapPerformer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View view = findViewById(R.id.circle);

        double arc = 2 * Math.PI / 6;
        view.setOnTouchListener(new Generator.Builder(SpringSystem.create())
                .addConverter(new MoveConverter(MotionProperty.X).addPerformer(view))
                .addConverter(new MoveConverter(MotionProperty.Y).addPerformer(view))
                .build().getMotionListener());
//        view.setOnTouchListener(new Generator.Builder(SpringSystem.create())
//                .addConverter(new MoveConverter(MotionProperty.X).addPerformer(new MapPerformer(view, View.TRANSLATION_X, 0, 1, 0, (float) Math.cos(2 * arc))))
//                .addConverter(new MoveConverter(MotionProperty.Y).addPerformer(new MapPerformer(view, View.TRANSLATION_Y, 0, 1, 0, -(float) Math.sin(2 * arc))))
//                .build().getMotionListener());
    }
}

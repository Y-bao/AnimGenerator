package com.ybao.library.performer;

import android.support.annotation.NonNull;
import android.util.Property;
import android.view.View;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringUtil;

/**
 * Created by Y-bao on 2016/11/8.
 */
public class MapPerformer extends Performer {

	private static final String TAG = MapPerformer.class.getSimpleName();

	private float initialStart, initialEnd, start, end;

	
	public MapPerformer(@NonNull final View target, @NonNull final Property<View, Float> property) {
		this(target, property, 0, 1, 0, 1);
	}

	
	public MapPerformer(@NonNull final View target, @NonNull final Property<View, Float> property, final float start, final float end) {
		this(target, property, 0, 1, start, end);
	}

	
	public MapPerformer(@NonNull final View target, @NonNull final Property<View, Float> property, final float initialStart,
						final float initialEnd, final float start, final float end) {
		super(target, property);

		this.initialStart = initialStart;
		this.initialEnd = initialEnd;
		this.start = start;
		this.end = end;
	}

	@Override
	public void onSpringUpdate(@NonNull final Spring spring) {

		mProperty.set(mTarget,
				(float) SpringUtil.mapValueFromRangeToRange(spring.getCurrentValue(),
						initialStart, initialEnd, start, end)
		);
	}
}

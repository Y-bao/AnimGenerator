package com.ybao.library;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;


public enum MotionProperty {

	X(View.TRANSLATION_X),

	Y(View.TRANSLATION_Y);

	@NonNull
	private final Property<View, Float> mViewProperty;

	private MotionProperty(@NonNull final Property<View, Float> viewProperty) {
		mViewProperty = viewProperty;
	}


	@NonNull
	public Property<View, Float> getViewProperty() {
		return mViewProperty;
	}


	public float getValue(@Nullable final View view) {
		if (view != null) {
			return mViewProperty.get(view);
		}

		return 0;
	}


	public float getValue(@Nullable final MotionEvent event) {
		if (event != null) {
			switch (this) {
			case X:
				return event.getX(0);
			case Y:
				return event.getY(0);
			default:
				return event.getX(0);
			}
		}

		return 0;
	}


	public float getHistoricalValue(@Nullable final MotionEvent event, final int index) {
		if (event != null) {
			switch (this) {
			case X:
				return event.getHistoricalX(index);
			case Y:
				return event.getHistoricalY(index);
			default:
				return 0;
			}
		}

		return 0;
	}


	public float getOldestValue(final MotionEvent event) {
		return getHistoricalValue(event, 0);
	}


	public float getOffset(@Nullable final View view) {
		if (view != null) {
			switch (this) {
			case X:
				return -view.getWidth() / 2;
			case Y:
				return -view.getHeight() / 2;
			default:
				return -view.getWidth() / 2;
			}
		}

		return 0;
	}
}
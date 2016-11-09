package com.ybao.library.performer;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Property;
import android.view.View;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringListener;


/**
 * Created by Y-bao on 2016/11/8.
 */

public class Performer implements SpringListener {

	
	@Nullable
	protected View mTarget;
	
	@NonNull
	protected Property<View, Float> mProperty;

	
	public Performer(@NonNull final Property<View, Float> property) {
		this(null, property);
	}

	
	public Performer(@Nullable final View target, @NonNull final Property<View, Float> property) {
		this.mTarget = target;
		this.mProperty = property;
	}

	@Nullable
	public View getTarget() {
		return mTarget;
	}

	public void setTarget(@Nullable final View target) {
		this.mTarget = target;
	}

	@NonNull
	public Property getProperty() {
		return mProperty;
	}

	public void setProperty(@NonNull final Property<View, Float> property) {
		this.mProperty = property;
	}

	@Override
	public void onSpringUpdate(@NonNull final Spring spring) {
		if (mProperty != null && mTarget != null) {
			mProperty.set(mTarget, (float) spring.getCurrentValue());
		}
	}

	@Override
	public void onSpringAtRest(final Spring spring) {

	}

	@Override
	public void onSpringActivate(final Spring spring) {

	}

	@Override
	public void onSpringEndStateChange(final Spring spring) {

	}
}

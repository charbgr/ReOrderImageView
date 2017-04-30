package com.github.charbgr.reorderimageview;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

public class ReOrderImageView extends AppCompatImageView {

  private boolean isAscending = true;

  private Drawable ascToDescDrawable;
  private Drawable descToAscDrawable;

  public ReOrderImageView(Context context) {
    super(context);
    init();
  }

  public ReOrderImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public ReOrderImageView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @Override
  public void setOnClickListener(@Nullable final OnClickListener toBindClickListener) {
    super.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (toBindClickListener != null) {
          toBindClickListener.onClick(v);
        }

        toggleOrder();
      }
    });
  }

  private void init() {
    findDrawables();
    setDefaultDrawable();
    setOnClickListener(null);
  }

  private void findDrawables() {
    ascToDescDrawable =
        ContextCompat.getDrawable(getContext(), R.drawable.ascending_to_descending_animated_vector);

    descToAscDrawable =
        ContextCompat.getDrawable(getContext(), R.drawable.descending_to_ascending_animated_vector);
  }

  private void setDefaultDrawable() {
    setImageDrawable(descToAscDrawable);
  }

  public void toggleOrder() {
    if (isAscending) {
      animateToDescending();
    } else {
      animateToAscending();
    }
    isAscending = !isAscending;
  }

  private Animatable getAnimatable() {
    Drawable drawable = getDrawable();
    if (drawable instanceof Animatable) {
      return ((Animatable) drawable);
    }

    return null;
  }

  private synchronized void animateToDescending() {
    setImageDrawable(descToAscDrawable);
    Animatable animatable = getAnimatable();
    if (animatable == null) return;

    animatable.start();
  }

  private synchronized void animateToAscending() {
    setImageDrawable(ascToDescDrawable);
    Animatable animatable = getAnimatable();
    if (animatable == null) return;

    animatable.start();
  }
}

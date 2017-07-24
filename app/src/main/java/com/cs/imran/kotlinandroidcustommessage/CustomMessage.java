package com.cs.imran.kotlinandroidcustommessage;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Windows10 on 5/31/2017.
 */

public class CustomMessage {
    private static final int ANIMATION_DURATION = 600;
    private int HIDE_DELAY = 5000;
    private View mContainer;
    private int gravity = Gravity.CENTER;
    private TextView mTextView;
    private Handler mHandler;
    private AlphaAnimation mFadeInAnimation;
    private AlphaAnimation mFadeOutAnimation;
    ViewGroup container;
    ViewGroup vg_parent_layout;
    public CustomMessage(Context context, int HIDE_DELAY, int gravity) {
        container = (ViewGroup) ((Activity) context).findViewById(android.R.id.content);
        vg_parent_layout =(ViewGroup) ((Activity) context).getWindow().getDecorView().findViewById(android.R.id.content);
        View main_view = ((Activity) context).getLayoutInflater().inflate(R.layout.ll_custom_message, vg_parent_layout);
        this.HIDE_DELAY = HIDE_DELAY;
        this.gravity = gravity;
        init(main_view);
    }

    private void init(View main_view) {
        mContainer = main_view.findViewById(R.id.mbContainer);
        mContainer.setVisibility(View.GONE);
        mTextView = (TextView) main_view.findViewById(R.id.mbMessage);
        mFadeInAnimation = new AlphaAnimation(0.0f, 1.0f);
        mFadeOutAnimation = new AlphaAnimation(1.0f, 0.0f);
        mFadeOutAnimation.setDuration(ANIMATION_DURATION);
        mFadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mContainer.setVisibility(View.GONE);
                vg_parent_layout.removeView(mContainer);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        mHandler = new Handler();

    }

    public void show(String message) {
        mContainer.setVisibility(View.VISIBLE);
        ((LinearLayout) mContainer).setGravity(gravity| Gravity.CENTER_VERTICAL);

        mTextView.setText(message);

        mFadeInAnimation.setDuration(ANIMATION_DURATION);

        mContainer.startAnimation(mFadeInAnimation);
        mHandler.postDelayed(mHideRunnable, HIDE_DELAY);
    }

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mContainer.startAnimation(mFadeOutAnimation);
        }
    };
}

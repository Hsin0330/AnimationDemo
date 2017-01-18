package com.symhung.animationdemo;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by HsinHung on 2017/1/18.
 */

public class PropertyAnimationFragment extends AnimFragment {

    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_property_animation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mImageView = (ImageView) view.findViewById(R.id.icon_sunny);
    }

    @Override
    public void startAnim() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(mImageView, View.Y, 600);
        animator.setDuration(5000);
        animator.setInterpolator(new LinearInterpolator());

        animator.start();
    }
}

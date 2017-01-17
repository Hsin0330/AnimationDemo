package com.symhung.animationdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by HsinHung on 2017/1/17.
 */

public class ViewAnimationFragment extends AnimFragment {

    private View mView;

    //Draw
    private Paint mPaint;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);

        mView = new View(getContext()) {
            @Override
            protected void onDraw(Canvas canvas) {
                drawAnimation(canvas);
            }
        };

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    private void drawAnimation(Canvas canvas) {
        int x = canvas.getWidth() / 2;
        int y = canvas.getHeight() / 2;

        canvas.drawCircle(x, y, 50, mPaint);
    }

    @Override
    public void startAnim() {
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        mView.startAnimation(anim);
    }
}

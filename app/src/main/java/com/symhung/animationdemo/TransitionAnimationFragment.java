package com.symhung.animationdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

/**
 * Created by HsinHung on 2017/1/19.
 */

public class TransitionAnimationFragment extends AnimFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transition_animation, container, false);
    }

    @Override
    public void startAnim() {
//        Intent i = new Intent(getActivity(), SecondActivity.class);
//        getActivity().startActivity(i);
//
//        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

        Intent i = new Intent(getActivity(), SecondActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity());
        startActivity(i, options.toBundle());
    }
}

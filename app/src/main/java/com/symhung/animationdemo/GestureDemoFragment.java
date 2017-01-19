package com.symhung.animationdemo;

import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.symhung.animationdemo.gestures.OnDoubleTapListener;
import com.symhung.animationdemo.gestures.OnSwipeTouchListener;

/**
 * Created by SymPhoNy on 19/01/2017.
 */

public class GestureDemoFragment extends AnimFragment {

    private Button mBtnLongClick;
    private Button mBtnDoubleTap;
    private TextView mTvSwipeMe;
    private TextView mTvDragMe;
    private TextView mTvDropZone;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gesture_demo, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup views
        mBtnLongClick = (Button) view.findViewById(R.id.btn_long_click);
        mBtnDoubleTap = (Button) view.findViewById(R.id.btn_double_tap);
        mTvSwipeMe = (TextView) view.findViewById(R.id.tv_swipe_me);
        mTvDragMe = (TextView) view.findViewById(R.id.tv_drag_me);
        mTvDropZone = (TextView) view.findViewById(R.id.tv_drop_zone);
        // Setup gestures
        mBtnLongClick.setOnLongClickListener(longClickListener);
        mBtnDoubleTap.setOnTouchListener(new MyOnDoubleTapListener(getContext()));
        mTvSwipeMe.setOnTouchListener(new MyOnSwipeTouchListener(getContext()));
        mTvDragMe.setOnTouchListener(new MyTouchListener());
        mTvDropZone.setOnDragListener(new MyDragListener());
    }

    @Override
    public void startAnim() {

    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private View.OnLongClickListener longClickListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            showToast("Long clicked!");
            return true;
        }
    };

    private class MyOnDoubleTapListener extends OnDoubleTapListener {
        public MyOnDoubleTapListener(Context c) {
            super(c);
        }

        @Override
        public void onDoubleTap(MotionEvent e) {
            showToast("Double tapped!");
        }
    };

    private class MyOnSwipeTouchListener extends OnSwipeTouchListener {
        public MyOnSwipeTouchListener(Context c) {
            super(c);
        }

        @Override
        public void onSwipeDown() {
            showToast("Swiped down!");
        }

        @Override
        public void onSwipeLeft() {
            showToast("Swiped left!");
        }

        @Override
        public void onSwipeRight() {
            showToast("Swiped right!");
        }

        @Override
        public void onSwipeUp() {
            showToast("Swiped up!");
        }
    };

    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    private class MyDragListener implements View.OnDragListener {
        Drawable enterShape = getResources().getDrawable(R.drawable.shape_border_green);
        Drawable normalShape = getResources().getDrawable(R.drawable.shape_border_red);

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    v.setBackground(enterShape);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    v.setBackground(normalShape);
                    break;
                case DragEvent.ACTION_DROP:
                    // Handle the dragged view being dropped over a target view
                    View draggedTextView = (View) event.getLocalState();
                    // Get View dragged item is being dropped on
                    TextView dropTarget = (TextView) v;
                    dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                    dropTarget.setText("Dropped!");
                    // Get owner of the dragged view
                    ViewGroup owner = (ViewGroup) draggedTextView.getParent();
                    // Remove the dragged view
                    owner.removeView(draggedTextView);
                    // Display toast
                    showToast("Dropped into zone!");
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    if (event.getResult()) { // drop succeeded
                        v.setBackground(enterShape);
                    } else { // drop failed
                        final View draggedView = (View) event.getLocalState();
                        draggedView.post(new Runnable(){
                            @Override
                            public void run() {
                                draggedView.setVisibility(View.VISIBLE);
                            }
                        });
                        v.setBackground(normalShape);
                    }
                default:
                    break;
            }
            return true;
        }
    }
}

package com.symhung.animationdemo;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private FloatingActionButton mFab;

    private DemoAdapter mAdapter;

    private static class Demo {
        String name;
        Class clazz;

        public Demo(String name, Class clazz) {
            this.name = name;
            this.clazz = clazz;
        }
    }

    private static final Demo[] DEMOS = {
            new Demo("View Animation", ViewAnimationFragment.class),
            new Demo("Drawable Animation", DrawableAnimationFragment.class),
            new Demo("Property Animation", PropertyAnimationFragment.class)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new DemoAdapter(getSupportFragmentManager());

        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(mAdapter);
        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setTitle(mAdapter.getPageTitle(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mFab = (FloatingActionButton) findViewById(R.id.fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = mAdapter.getItem(mPager.getCurrentItem());
                ((AnimFragment) fragment).startAnim();
            }
        });

    }

    private class DemoAdapter extends FragmentPagerAdapter {

        private Fragment caches[] = new Fragment[getCount()];

        public DemoAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (caches[position] == null) {
                Class clazz = DEMOS[position].clazz;

                try {
                    caches[position] = (Fragment) clazz.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return caches[position];
        }

        @Override
        public int getCount() {
            return DEMOS.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return DEMOS[position].name;
        }
    }
}

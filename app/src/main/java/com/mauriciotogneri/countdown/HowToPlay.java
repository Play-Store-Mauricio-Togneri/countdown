package com.mauriciotogneri.countdown;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.WindowManager;

import com.mauriciotogneri.countdown.howtoplay.FirstFragment;
import com.mauriciotogneri.countdown.howtoplay.SecondFragment;
import com.mauriciotogneri.countdown.howtoplay.ThirdFragment;

public class HowToPlay extends FragmentActivity
{
    private View circle1;
    private View circle2;
    private View circle3;

    private static final int COLOR_CIRCLE_OFF = Color.argb(255, 204, 204, 204);
    private static final int COLOR_CIRCLE_ON = Color.argb(255, 140, 140, 140);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtoplay);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        this.circle1 = findViewById(R.id.circle_1);
        this.circle2 = findViewById(R.id.circle_2);
        this.circle3 = findViewById(R.id.circle_3);

        ViewPager pager = findViewById(R.id.view_pager);
        pager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
        pager.addOnPageChangeListener(new OnPageChangeListener()
        {
            @Override
            public void onPageSelected(int position)
            {
                updateCircles(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });

        updateCircles(0);
    }

    private void updateCircles(int index)
    {
        GradientDrawable background1 = (GradientDrawable) this.circle1.getBackground();
        background1.setColor((index == 0) ? HowToPlay.COLOR_CIRCLE_ON : HowToPlay.COLOR_CIRCLE_OFF);

        GradientDrawable background2 = (GradientDrawable) this.circle2.getBackground();
        background2.setColor((index == 1) ? HowToPlay.COLOR_CIRCLE_ON : HowToPlay.COLOR_CIRCLE_OFF);

        GradientDrawable background3 = (GradientDrawable) this.circle3.getBackground();
        background3.setColor((index == 2) ? HowToPlay.COLOR_CIRCLE_ON : HowToPlay.COLOR_CIRCLE_OFF);
    }

    private class CustomPagerAdapter extends FragmentPagerAdapter
    {
        public CustomPagerAdapter(FragmentManager fragmentManager)
        {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int index)
        {
            switch (index)
            {
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
                default:
                    return new ThirdFragment();
            }
        }

        @Override
        public int getCount()
        {
            return 3;
        }
    }

    public void startGame()
    {
        finish();
    }

    @Override
    public void onBackPressed()
    {
    }
}
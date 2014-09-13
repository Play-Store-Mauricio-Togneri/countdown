package com.mauriciotogneri.countdown;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import com.mauriciotogneri.countdown.howtoplay.FirstFragment;
import com.mauriciotogneri.countdown.howtoplay.SecondFragment;
import com.mauriciotogneri.countdown.howtoplay.ThirdFragment;

public class HowToPlay extends FragmentActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_howtoplay);
		
		ViewPager pager = (ViewPager)findViewById(R.id.view_pager);
		pager.setAdapter(new CustomPagerAdapter(getSupportFragmentManager()));
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
	
	// TODO: BLOCK BACK BUTTON
}
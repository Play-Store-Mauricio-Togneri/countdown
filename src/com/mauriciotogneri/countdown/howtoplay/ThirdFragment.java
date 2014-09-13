package com.mauriciotogneri.countdown.howtoplay;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.mauriciotogneri.countdown.HowToPlay;
import com.mauriciotogneri.countdown.R;

public class ThirdFragment extends Fragment
{
	private HowToPlay mainActivity;
	private Button startButton;

	@Override
	public void onAttach(Activity activity)
	{
		this.mainActivity = (HowToPlay)activity;
		
		super.onAttach(activity);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View layout = inflater.inflate(R.layout.howtoplay_3, container, false);

		this.startButton = (Button)layout.findViewById(R.id.start_game);
		this.startButton.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				startGame();
			}
		});

		return layout;
	}

	private void startGame()
	{
		this.mainActivity.startGame();
	}
}
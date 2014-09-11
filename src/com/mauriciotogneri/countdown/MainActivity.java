package com.mauriciotogneri.countdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity
{
	private TextView scoreUp;
	private TextView scoreDown;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

		initialize();
	}

	private void initialize()
	{
		this.scoreUp = (TextView)findViewById(R.id.score_up);
		this.scoreUp.setText("0");
		this.scoreDown = (TextView)findViewById(R.id.score_down);
		this.scoreDown.setText("0");
		
		Button buttonUp = (Button)findViewById(R.id.button_up);
		buttonUp.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						buttonUpPressed();
						break;

					case MotionEvent.ACTION_UP:
						buttonUpReleased();
						break;
				}

				return false;
			}
		});
		
		Button buttonDown = (Button)findViewById(R.id.button_down);
		buttonDown.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View view, MotionEvent event)
			{
				switch (event.getAction())
				{
					case MotionEvent.ACTION_DOWN:
						buttonDownPressed();
						break;

					case MotionEvent.ACTION_UP:
						buttonDownReleased();
						break;
				}

				return false;
			}
		});
	}

	private void buttonUpPressed()
	{

	}

	private void buttonUpReleased()
	{

	}
	
	private void buttonDownPressed()
	{

	}

	private void buttonDownReleased()
	{

	}
}
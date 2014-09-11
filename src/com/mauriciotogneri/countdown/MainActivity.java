package com.mauriciotogneri.countdown;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
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

	private TextView timerUp;
	private TextView timerDown;
	
	private Timer timer = new Timer();
	private int timerValue = 1000;

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

		this.timerUp = (TextView)findViewById(R.id.timer_up);
		this.timerUp.setText("1000");
		this.timerDown = (TextView)findViewById(R.id.timer_down);
		this.timerDown.setText("1000");
		
		Button buttonUp = (Button)findViewById(R.id.button_up);
		buttonUp.setOnTouchListener(new OnTouchListener()
		{
			@Override
			@SuppressLint("ClickableViewAccessibility")
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
			@SuppressLint("ClickableViewAccessibility")
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
		this.timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				updateTimerValue(5);
			}
		}, 0, 10);
	}

	private void buttonDownReleased()
	{
		this.timer.cancel();
		this.timer.purge();
		this.timer = new Timer();
	}

	private void updateTimerValue(final int value)
	{
		runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				MainActivity.this.timerValue -= value;
				MainActivity.this.timerDown.setText(String.valueOf(MainActivity.this.timerValue));
			}
		});
	}
}
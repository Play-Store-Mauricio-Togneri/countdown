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
	private TextView upScoreTextView;
	private TextView downScoreTextView;

	private TextView upTimerTextView;
	private TextView downTimerTextView;
	
	// ==================================
	
	private boolean upPressed = false;
	private boolean downPressed = false;
	
	private int upTimer = 0;
	private int downTimer = 0;
	
	private boolean gameStarted = false;

	private Timer timer = new Timer();

	// ==================================

	private static final int INITIAL_VALUE = 1000;

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
		this.upScoreTextView = (TextView)findViewById(R.id.score_up);
		this.upScoreTextView.setText("0");
		this.downScoreTextView = (TextView)findViewById(R.id.score_down);
		this.downScoreTextView.setText("0");
		
		this.upTimerTextView = (TextView)findViewById(R.id.timer_up);
		this.downTimerTextView = (TextView)findViewById(R.id.timer_down);

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
		
		restartGame();
	}

	private void restartGame()
	{
		this.upTimer = MainActivity.INITIAL_VALUE;
		updateUpTimerTextView();

		this.downTimer = MainActivity.INITIAL_VALUE;
		updatedownTimerTextView();
		
		this.gameStarted = false;
		this.timer = new Timer();
	}

	private void buttonUpPressed()
	{
		this.upPressed = true;
		
		checkStartGame();
	}

	private void buttonUpReleased()
	{
		this.upPressed = false;
	}
	
	private void buttonDownPressed()
	{
		this.downPressed = true;
		
		checkStartGame();
	}

	private void buttonDownReleased()
	{
		this.downPressed = false;

		checkEndGame();
	}

	private void checkStartGame()
	{
		if ((!this.gameStarted) && this.upPressed && this.downPressed)
		{
			this.gameStarted = true;

			this.timer.schedule(new TimerTask()
			{
				@Override
				public void run()
				{
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							updateTimer(5);
						}
					});
				}
			}, 0, 10);
		}
	}

	private void checkEndGame()
	{
		if ((!this.upPressed) && (!this.downPressed))
		{
			this.gameStarted = false;

			this.timer.cancel();
			this.timer.purge();
			this.timer = new Timer();
		}
	}

	private void updateTimer(int value)
	{
		if (this.upPressed)
		{
			this.upTimer -= value;
			updateUpTimerTextView();
		}
		
		if (this.downPressed)
		{
			this.downTimer -= value;
			updatedownTimerTextView();
		}
	}

	private void updateUpTimerTextView()
	{
		this.upTimerTextView.setText(String.valueOf(MainActivity.this.upTimer));
	}

	private void updatedownTimerTextView()
	{
		this.downTimerTextView.setText(String.valueOf(MainActivity.this.downTimer));
	}
}
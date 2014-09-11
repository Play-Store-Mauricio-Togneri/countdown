package com.mauriciotogneri.countdown;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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

	private boolean upBlocked = false;
	private boolean downBlocked = false;

	private int upTimer = 0;
	private int downTimer = 0;

	private int upScore = 0;
	private int downScore = 0;

	private boolean gameStarted = false;

	private Timer timer = new Timer();

	// ==================================

	private static final int INITIAL_VALUE = 1000;
	private static final int COUNTDOWN_RATE = 10;

	private static final int COLOR_TIMER_NORMAL = Color.argb(255, 80, 80, 80);
	private static final int COLOR_TIMER_UNDER = Color.argb(255, 160, 0, 0);

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
		this.downTimer = MainActivity.INITIAL_VALUE;
		updateTimersTextView();

		this.upBlocked = false;
		this.downBlocked = false;

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
		this.upBlocked = true;
		this.upPressed = false;

		checkEndGame();
	}

	private void buttonDownPressed()
	{
		this.downPressed = true;

		checkStartGame();
	}

	private void buttonDownReleased()
	{
		this.downBlocked = true;
		this.downPressed = false;

		checkEndGame();
	}

	private void checkStartGame()
	{
		if ((!this.gameStarted) && (this.upPressed || this.downPressed))
		{
			restartGame();
		}

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
						final int rate = (int)(MainActivity.COUNTDOWN_RATE * 0.7f);
						
						@Override
						public void run()
						{
							updateTimer(this.rate);
						}
					});
				}
			}, 0, MainActivity.COUNTDOWN_RATE);
		}
	}

	private void checkEndGame()
	{
		Log.e("", "");
		
		if (this.gameStarted && (!this.upPressed) && (!this.downPressed))
		{
			this.gameStarted = false;

			if ((this.upTimer >= 0) && ((this.upTimer < this.downTimer) || (this.downTimer < 0)))
			{
				this.upScore++;
			}

			if ((this.downTimer >= 0) && ((this.downTimer < this.upTimer) || (this.upTimer < 0)))
			{
				this.downScore++;
			}
			
			updateScoresTextView();
			
			this.timer.cancel();
			this.timer.purge();
			this.timer = new Timer();
		}
	}

	private void updateTimer(int value)
	{
		if ((this.upPressed) && (!this.upBlocked))
		{
			this.upTimer -= value;
		}

		if ((this.downPressed) && (!this.downBlocked))
		{
			this.downTimer -= value;
		}

		updateTimersTextView();
	}

	private void updateTimersTextView()
	{
		this.upTimerTextView.setText(String.valueOf(this.upTimer));
		
		if (this.upTimer < 0)
		{
			this.upTimerTextView.setTextColor(MainActivity.COLOR_TIMER_UNDER);
		}
		else
		{
			this.upTimerTextView.setTextColor(MainActivity.COLOR_TIMER_NORMAL);
		}
		
		this.downTimerTextView.setText(String.valueOf(this.downTimer));

		if (this.downTimer < 0)
		{
			this.downTimerTextView.setTextColor(MainActivity.COLOR_TIMER_UNDER);
		}
		else
		{
			this.downTimerTextView.setTextColor(MainActivity.COLOR_TIMER_NORMAL);
		}
	}
	
	private void updateScoresTextView()
	{
		this.upScoreTextView.setText(String.valueOf(this.upScore));
		this.downScoreTextView.setText(String.valueOf(this.downScore));
	}
}
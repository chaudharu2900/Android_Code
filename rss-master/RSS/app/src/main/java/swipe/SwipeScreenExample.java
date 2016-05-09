package swipe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import home.example.com.rss.MainActivity;
import home.example.com.rss.R;

import swipe.SimpleGestureFilter.SimpleGestureListener;
import tips.First_tip;


public class SwipeScreenExample extends Activity implements SimpleGestureListener {
	private SimpleGestureFilter detector;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tips_of_the_day);

		// Detect touched area
		detector = new SimpleGestureFilter(this, this);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent me) {
		// Call onTouchEvent of SimpleGestureFilter class
		this.detector.onTouchEvent(me);
		return super.dispatchTouchEvent(me);
	}

	@Override
	public void onSwipe(int direction) {

		switch (direction) {

			case SimpleGestureFilter.SWIPE_RIGHT:
				Intent intent2 = new Intent(SwipeScreenExample.this, MainActivity.class);
				startActivity(intent2);
				break;
			case SimpleGestureFilter.SWIPE_LEFT:
				Intent intent1 = new Intent(SwipeScreenExample.this, MainActivity.class);
				startActivity(intent1);
				break;
//			case SimpleGestureFilter.SWIPE_DOWN :
//				Intent intent0= new Intent(SwipeScreenExample.this, Second_tip.class);
//				startActivity(intent0);
//				break;
			case SimpleGestureFilter.SWIPE_UP:
				Intent intent3 = new Intent(SwipeScreenExample.this, First_tip.class);
				startActivity(intent3);
				Toast.makeText(this, "First Tip of the day ", Toast.LENGTH_SHORT).show();
				break;

		}

	}

	@Override
	public void onDoubleTap() {
	 Toast.makeText(this, "Double Tap", Toast.LENGTH_SHORT).show();

	}

}
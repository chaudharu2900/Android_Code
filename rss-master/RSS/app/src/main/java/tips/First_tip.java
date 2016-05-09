package tips;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import home.example.com.rss.MainActivity;
import home.example.com.rss.R;
import swipe.SwipeScreenExample;
import tips.SimpleGestureFilter.SimpleGestureListener;



public class First_tip extends Activity implements SimpleGestureListener{
 private SimpleGestureFilter detector;

    Context context;
    protected View mContent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tip_first);

        // Detect touched area
        detector = new SimpleGestureFilter(this,this);
    }



    @Override
    public boolean dispatchTouchEvent(MotionEvent me){
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }
    @Override
    public void onSwipe(int direction) {

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT :
                Intent intent2= new Intent(First_tip.this, MainActivity.class);
                startActivity(intent2);
                break;
            case SimpleGestureFilter.SWIPE_LEFT :
                Intent intent1= new Intent(First_tip.this, MainActivity.class);
                startActivity(intent1);
                break;
            case SimpleGestureFilter.SWIPE_DOWN :
                Intent intent0= new Intent(First_tip.this, SwipeScreenExample.class);
                startActivity(intent0);
                break;
            case SimpleGestureFilter.SWIPE_UP :
                Intent intent3= new Intent(First_tip.this, MainActivity.class);
                startActivity(intent3);
             //   Toast.makeText(this, "Last tip ", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onDoubleTap() {
        Toast.makeText(this, "You tapped double", Toast.LENGTH_SHORT).show();
        Intent intent4= new Intent(First_tip.this, MainActivity.class);
        startActivity(intent4);
    }


}




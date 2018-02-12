package nanosoft.azhar.me.viewflippertestme03;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class GestureActivity extends Activity {

    private ViewFlipper mViewFlipper;
    private GestureDetector mGestureDetector;

    int[] resources = {
            R.drawable.first,
            R.drawable.second,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth,
            R.drawable.sixth
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture);

        // Get the ViewFlipper
        mViewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        // Add all the images to the ViewFlipper
        for (int i = 0; i < resources.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(resources[i]);
            mViewFlipper.addView(imageView);
        }

        // Set in/out flipping animations
        mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
        mViewFlipper.setOutAnimation(this, android.R.anim.fade_out);

        CustomGestureDetector customGestureDetector = new CustomGestureDetector();
        mGestureDetector = new GestureDetector(this, customGestureDetector);
    }

    class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            // Swipe left (next)
            if (e1.getX() > e2.getX()) {
                mViewFlipper.showNext();
            }

            // Swipe right (previous)
            if (e1.getX() < e2.getX()) {
                mViewFlipper.showPrevious();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);

        return super.onTouchEvent(event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.gesture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

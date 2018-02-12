package nanosoft.azhar.me.viewflippertestme03;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

public class GestureActivityViewSwitcher extends AppCompatActivity {


        // private ViewFlipper mViewFlipper;
        private ViewSwitcher mViewSwitcher;
        private GestureDetector mGestureDetector;

        int[] resources = {
                R.drawable.first,
                R.drawable.second/*,
            R.drawable.third,
            R.drawable.fourth,
            R.drawable.fifth,
            R.drawable.sixth*/
        };

        private String TAG = GestureActivity.class.getSimpleName();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_gesture_view_switcher);

            // Get the ViewFlipper
            mViewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);

        /*mViewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(GestureActivity.this);
                imageView.setImageResource(resources[0]);

                Log.d(TAG, "setFactory#makeView");

                return imageView;
            }
        });*/

            // Add all the images to the ViewFlipper
            for (int i = 0; i < resources.length; i++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(resources[i]);
                mViewSwitcher.addView(imageView);
            }

            // Set in/out flipping animations
            //mViewFlipper.setInAnimation(this, android.R.anim.fade_in);
            //mViewFlipper.setOutAnimation(this, android.R.anim.fadeout);

            CustomGestureDetector customGestureDetector = new CustomGestureDetector();
            mGestureDetector = new GestureDetector(this, customGestureDetector);
        }

        class CustomGestureDetector extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

                // Swipe left (next)
                if (e1.getX() > e2.getX()) {
                    mViewSwitcher.setInAnimation(GestureActivityViewSwitcher.this, R.anim.left_in);
                    mViewSwitcher.setOutAnimation(GestureActivityViewSwitcher.this, R.anim.left_out);

                    mViewSwitcher.showNext();
                }

                // Swipe right (previous)
                if (e1.getX() < e2.getX()) {
                    mViewSwitcher.setInAnimation(GestureActivityViewSwitcher.this, R.anim.right_in);
                    mViewSwitcher.setOutAnimation(GestureActivityViewSwitcher.this, R.anim.right_out);

                    mViewSwitcher.showPrevious();
                }

            /*mViewSwitcher.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    Log.d(TAG, "Animation End");
                    Log.d(TAG, "Child Count: " + mViewSwitcher.getChildCount());
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });*/

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
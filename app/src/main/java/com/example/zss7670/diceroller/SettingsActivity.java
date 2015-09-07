package com.example.zss7670.diceroller;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

/**
 * About/credits screen that scrolls a random occupation + name upwards
 */
public class SettingsActivity extends AppCompatActivity {

    private Handler mCreditsHandler = new Handler();
    private CreditsThread mCreditsThread = new CreditsThread();
    private RelativeLayout mCreditsLayout;
    private Random mRand = new Random();
    private Point mDimens = new Point();
    private String[] mOccupations;

    /**
     * Runnable thread to create a new credit item and have it scroll every 3 sec
     */
    private class CreditsThread implements Runnable {

        public static final int DELAY_MILLIS = 3000;

        @Override
        public void run(){
            createCreditLine();
            mCreditsHandler.postDelayed(mCreditsThread, DELAY_MILLIS);
        }
    }

    /**
     * Create a new set for the credits scroll
     * Creates 2 textFields and applies upward animation to both
     */
    private void createCreditLine(){
        //create the credits here and display them
        Integer index = mRand.nextInt(mOccupations.length);
        String occupationString = mOccupations[index];
        final TextView occTextView = makeCreditsTextView(occupationString, 20);
        mCreditsLayout.addView(occTextView);

        final TextView nameTextView = makeCreditsTextView("Zachary Sherwin", 15);
        mCreditsLayout.addView(nameTextView);

        TranslateAnimation anim = getTranslateAnimation(occTextView, -100, 200);
        TranslateAnimation nameAnim = getTranslateAnimation(nameTextView, 0, 100);
        occTextView.startAnimation(anim);
        nameTextView.startAnimation(nameAnim);
    }

    /**
     *
     * @param view View to animate
     * @param sOffset starting position offset from bottom of screen
     * @param offset ending position offset from top of screen
     * @return Translate animation to apply
     */
    @NonNull
    private TranslateAnimation getTranslateAnimation(final TextView view, Integer sOffset, Integer offset) {
        TranslateAnimation anim = new TranslateAnimation(view.getX(), view.getX(), mDimens.y + sOffset, -offset);
        anim.setDuration(15000);
        anim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCreditsLayout.removeView(view);
            }
        });
        return anim;
    }

    /**
     * healper function to create TextView's with correct params
     * @param viewString String to set in the view
     * @param textSize Size of the font
     * @return TextView object
     */
    @NonNull
    private TextView makeCreditsTextView(String viewString, Integer textSize) {
        TextView textView = new TextView(this);
        textView.setText(viewString);
        textView.setTextSize(textSize);
        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mOccupations = getResources().getStringArray(R.array.occupations);
        mCreditsLayout = (RelativeLayout) findViewById(R.id.creditsLayout);

        getWindowManager().getDefaultDisplay().getSize(mDimens);

        final LinearLayout actualCredits = (LinearLayout) findViewById(R.id.actualCredits);

        TranslateAnimation anim = new TranslateAnimation(actualCredits.getX(), actualCredits.getX(),
                actualCredits.getY(), - mDimens.y);
        anim.setDuration(15000);
        anim.setAnimationListener(new TranslateAnimation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                startFakeCredits();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mCreditsHandler.post(mCreditsThread);
                mCreditsLayout.removeView(actualCredits);
            }
        });
        actualCredits.startAnimation(anim);
    }

    public void startFakeCredits(){
        final TextView occTextView = makeCreditsTextView("We would also like to thank...", 20);
        mCreditsLayout.addView(occTextView);

        TranslateAnimation anim = getTranslateAnimation(occTextView, -100, 200);
        occTextView.startAnimation(anim);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

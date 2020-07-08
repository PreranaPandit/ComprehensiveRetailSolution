package com.example.prerana.comprehensiveretailsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prerana.comprehensiveretailsolution.R;


/**
 * Created by Prerana Pandit on 27/06/2020.
 */

public class SplashActivity extends AppCompatActivity {

    //data referencing
    protected ImageView imageView;
    protected ImageView imageBlood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to remove the title feature from window
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //to hide the action bar
        getSupportActionBar().hide();
        //to make window fullscreen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //data binding
        imageView = findViewById(R.id.load_image);
        imageBlood = findViewById(R.id.splash_blood);

        //Animation drawable process begins for splash screen
//        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getDrawable();
//        //starts animation
//        animationDrawable.start();

        //handler process begins
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //intent begins to open another activity
                                    Intent intent = new Intent(getApplicationContext(), SliderImageActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },3500
        );

        startAnimation();

        Thread timerThread = new Thread()
        {
            public void run()
            {
                try{
                    sleep(3500);
                }
                catch (InterruptedException ex)
                {
                    ex.printStackTrace();
                }
                finally {
                    Intent intent = new Intent(SplashActivity.this, SliderImageActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        timerThread.start();
        startAnimation();
    }

    private void startAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        animation.reset();
        ImageView imageCart = findViewById(R.id.splash_blood);
        imageCart.clearAnimation();
        imageCart.startAnimation(animation);
    }
}

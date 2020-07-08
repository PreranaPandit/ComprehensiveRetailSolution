package com.example.prerana.comprehensiveretailsolution.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.prerana.comprehensiveretailsolution.OTPAuthActivity;
import com.example.prerana.comprehensiveretailsolution.R;
import com.example.prerana.comprehensiveretailsolution.adapters.SlidingImage_Adapter;
import com.example.prerana.comprehensiveretailsolution.model.ImageModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Prerana Pandit on 30/06/2020.
 */

public class SliderImageActivity extends AppCompatActivity {

    //referencing
    private TextView tvRetails;

    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private ArrayList<ImageModel> imageModelArrayList;

    private int[] myImageList = new int[] {R.drawable.oneslide, R.drawable.twoslide, R.drawable.threeslide, R.drawable.fourslide, R.drawable.fiveslide};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //to hide the title bar from login page
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //hides the title from activity
        getSupportActionBar().hide();
        //gets window full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_slider_image);

        //data binding
        imageModelArrayList = new ArrayList<>();
        imageModelArrayList = populateList();

        tvRetails = findViewById(R.id.tvRetails);

        init();

        tvRetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OTPAuthActivity.class);
                startActivity(intent);
            }
        });
    }

    private ArrayList<ImageModel> populateList() {

        ArrayList<ImageModel> list = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            ImageModel imageModel = new ImageModel();
            imageModel.setImage_drawable(myImageList[i]);
            list.add(imageModel);
        }

        return list;
    }


    private void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
//        mPager.setAdapter(new SlidingImage_Adapter(imageModelArrayList, SliderImageActivity.this));

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(SliderImageActivity.this,imageModelArrayList));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =imageModelArrayList.size();

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2000, 2000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }
}


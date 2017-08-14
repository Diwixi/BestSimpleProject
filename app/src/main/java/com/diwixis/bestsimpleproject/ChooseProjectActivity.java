package com.diwixis.bestsimpleproject;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.diwixis.bestsimpleproject.listProject.presentations.todoList.ListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseProjectActivity extends AppCompatActivity implements ViewSwitcher.ViewFactory, GestureDetector.OnGestureListener{
    private GestureDetector mGestureDetector;
    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_MAX_OFF_PATH = 250;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;
    int position = 0;
    private int[] images = { R.drawable.list_image, R.drawable.movie_image, R.drawable.map_image};

    @BindView(R.id.projectImageSwitcher)    ImageSwitcher imageSwitcher;

    View.OnClickListener openProjectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (position){
                case 0:{
                    onListProjectClick();
                    break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_project);
        ButterKnife.bind(this);
//        imageSwitcher = (ImageSwitcher) findViewById(R.id.projectImageSwitcher);


        mGestureDetector = new GestureDetector(this, this);
        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(R.drawable.list_image);
//        imageSwitcher.setImageResource(R.drawable.movie_image);
//        imageSwitcher.setImageResource(R.drawable.map_image);
//        imageSwitcher.setOnClickListener(openProjectListener);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mGestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        onClick();
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent ev1, MotionEvent ev2, float velX, float velY) {
        try {
            if (Math.abs(ev1.getY() - ev2.getY()) > SWIPE_MAX_OFF_PATH) {
                return false;
            }
            // справа налево
            if (ev1.getX() - ev2.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velY) > SWIPE_THRESHOLD_VELOCITY) {
                setPositionNext();
                imageSwitcher.setImageResource(images[position]);
            } else if (ev2.getX() - ev1.getX() > SWIPE_MIN_DISTANCE
                    && Math.abs(velX) > SWIPE_THRESHOLD_VELOCITY) {
                // слева направо
                setPositionPrev();
                imageSwitcher.setImageResource(images[position]);
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }

    public void setPositionNext() {
        position++;
        if (position > 2) {
            position = 0;
        }
    }

    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = 2;
        }
    }


    private void onClick(){
        switch (position){
            case 0:{
                onListProjectClick();
                break;
            }
        }
    }

    public void onListProjectClick() {
        ListActivity.startActivity(this);
    }

    public void onMovieProjectClick() {
    }

    public void onMapProjectClick() {
    }

    @Override
    public View makeView() {
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        imageView.setBackgroundColor(0xFF000000);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }
}

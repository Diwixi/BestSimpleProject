package com.diwixis.bestsimpleproject.picturesProject;

import android.app.Activity;
import android.content.Intent;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.diwixis.bestsimpleproject.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diwixis on 14.08.2017.
 */

public class PicturesActivity extends MvpAppCompatActivity{

//    @BindView(R.id.storageButton)    ImageButton storageButton;
//    @BindView(R.id.cameraButton)     ImageButton cameraButton;

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, PicturesActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_pictures_activity);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.cameraConstraint)
    void onClickCameraButton(){
        PictureViewerActivity.startActivity(this);
    }
}

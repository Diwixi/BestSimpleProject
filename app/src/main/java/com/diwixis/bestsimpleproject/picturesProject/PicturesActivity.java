package com.diwixis.bestsimpleproject.picturesProject;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraDevice;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

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
    int PERMISSION_REQUEST_CODE = 1929;

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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            PictureViewerActivity.startActivity(this);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @OnClick(R.id.storageConstraint)
    void onClickStorageButton(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
//            PictureViewerActivity.startActivity(this);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                PictureViewerActivity.startActivity(this);
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

package com.diwixis.bestsimpleproject.picturesProject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.diwixis.bestsimpleproject.R;
import com.diwixis.bestsimpleproject.picturesProject.camera.*;
import com.diwixis.bestsimpleproject.picturesProject.camera.AutoFitTextureView;
import com.diwixis.bestsimpleproject.picturesProject.image.ShowPhotoActivity;

import java.io.File;
import java.io.IOException;

/**
 * Created by Diwixis on 29.08.2017.
 */

public class PictureViewerActivity extends MvpAppCompatActivity {

    private static final String TAG = PictureViewerActivity.class.getName();

    private CameraController mRxCameraController21;

    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, PictureViewerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_viewer_activity);
//        ButterKnife.bind(this);

        File outputDir = getCacheDir(); // context being the Activity pointer
        File outputFile = null;
        try {
            outputFile = File.createTempFile("prefix", ".jpg", outputDir);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        findViewById(R.id.takePhoto).setOnClickListener(view -> mRxCameraController21.takePhoto());
        findViewById(R.id.switchCamera).setOnClickListener(view -> mRxCameraController21.switchCamera());

        mRxCameraController21 = new CameraController(
                this,
                mRxCamerController21Callback,
                outputFile.getAbsolutePath(),
                (AutoFitTextureView) findViewById(R.id.textureView),
                Configuration.ORIENTATION_PORTRAIT);
        mRxCameraController21.getLifecycle().onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRxCameraController21.getLifecycle().onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRxCameraController21.getLifecycle().onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRxCameraController21.getLifecycle().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRxCameraController21.getLifecycle().onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mRxCameraController21.getLifecycle().onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxCameraController21.getLifecycle().onDestroy();
    }

    private final CameraController.Callback mRxCamerController21Callback = new CameraController.Callback() {
        @Override
        public void onFocusStarted() {
//            mFocusIndicator.setVisibility(View.VISIBLE);
//            mFocusIndicator.setScaleX(1f);
//            mFocusIndicator.setScaleY(1f);
//            mFocusIndicator.animate()
//                    .scaleX(2f)
//                    .scaleY(2f)
//                    .setDuration(500)
//                    .start();
        }

        @Override
        public void onFocusFinished() {
//            mFocusIndicator.setVisibility(View.GONE);
        }

        @Override
        public void onPhotoTaken(@NonNull String photoUrl, @NonNull Integer photoSourceType) {
            Intent intent = ShowPhotoActivity.IntentHelper.createIntent(PictureViewerActivity.this, photoUrl);
            startActivity(intent);
        }

        @Override
        public void onCameraAccessException() {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        @Override
        public void onCameraOpenException(@Nullable OpenCameraException.Reason reason) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }

        @Override
        public void onException(Throwable throwable) {
            throwable.printStackTrace();
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    };
}

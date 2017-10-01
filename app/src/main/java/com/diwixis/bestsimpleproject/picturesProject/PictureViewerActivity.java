package com.diwixis.bestsimpleproject.picturesProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.widget.Button;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.diwixis.bestsimpleproject.R;
import com.diwixis.bestsimpleproject.picturesProject.camera.CameraHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Diwixis on 29.08.2017.
 */

public class PictureViewerActivity extends MvpAppCompatActivity {

    private CameraManager mCameraManager = null;
    private TextureView mImageView = null;


    public static void startActivity(Activity activity){
        Intent intent = new Intent(activity, PictureViewerActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private final int CAMERA1   = 0;
    private final int CAMERA2   = 1;
    private CameraHelper[] myCameras = null;
    private Button mButtonOpenCamera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pictures_viewer_activity);
//        ButterKnife.bind(this);
        mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        mImageView = (TextureView) findViewById(R.id.textureView);
        try{
            // Получение списка камер с устройства
            String[] cameraList = mCameraManager.getCameraIdList();
            //создаем место для наших камер
            myCameras = new CameraHelper[cameraList.length];

            // создаем обработчики для нашых камер и выводим информацию по камере
            for (String cameraID : cameraList) {
                Log.i("TAG", "cameraID: "+cameraID);
                int id = Integer.parseInt(cameraID);

                // создаем обработчик для камеры
                myCameras[id] = new CameraHelper(mCameraManager,cameraID);
                // устанавливаем текстуру для отображения

                // выводим инормацию по камере
                myCameras[id].viewFormatSize(ImageFormat.JPEG);
                myCameras[CAMERA1].openCamera(this);
                myCameras[id].setTextureView(mImageView);
            }
        }
        catch(CameraAccessException e){
            Log.e("TAG", e.getMessage());
            e.printStackTrace();
        }
    }
}

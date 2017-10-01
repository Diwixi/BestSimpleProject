package com.diwixis.bestsimpleproject.picturesProject.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.util.Size;
import android.view.Surface;
import android.view.TextureView;

import java.util.Arrays;

/**
 * Created by Diwixis on 30.09.2017.
 */

public class CameraHelper {

    private CameraManager mCameraManager = null;
    private String mCameraID = null;
    private CameraDevice mCameraDevice = null;
    private TextureView mTextureView = null;

    public CameraHelper(@NonNull CameraManager cameraManager, @NonNull String cameraID) {
        mCameraManager = cameraManager;
        mCameraID = cameraID;
    }

    public boolean isOpen() {
        if (mCameraDevice == null) {
            return false;
        } else {
            return true;
        }
    }

    public void setTextureView(TextureView textureView) {
        mTextureView = textureView;
    }

    public void openCamera(Activity context) {
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
                return;
            }
            mCameraManager.openCamera(mCameraID, mCameraCallback, null);
        } catch (CameraAccessException e) {
            Log.e("TAG",e.getMessage());
            //e.printStackTrace();
        }
    }

    public void closeCamera() {

        if (mCameraDevice != null) {
            mCameraDevice.close();
            mCameraDevice = null;
        }
    }


    public void viewFormatSize(int formatSize) {
        // Получения характеристик камеры
        CameraCharacteristics cc = null;
        try {
            cc = mCameraManager.getCameraCharacteristics(mCameraID);

            // Получения списка выходного формата, который поддерживает камера
            StreamConfigurationMap configurationMap =
                    cc.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);

            // Получения списка разрешений которые поддерживаются для формата jpeg
            Size[] sizesJPEG = configurationMap.getOutputSizes(ImageFormat.JPEG);

            if (sizesJPEG != null) {
                for (Size item:sizesJPEG) {
                    Log.i("TAG", "w:" + item.getWidth() + " h:" + item.getHeight());
                }
            } else {
                Log.e("TAG", "camera with id: "+mCameraID+" don`t support format: "+formatSize);
            }

        } catch (CameraAccessException e) {
            Log.e("TAG",e.getMessage());
        }
    }

    private void createCameraPreviewSession() {
        SurfaceTexture texture = mTextureView.getSurfaceTexture();
        texture.setDefaultBufferSize(1920,1080);
        Surface surface = new Surface(texture);

        try {
            final CaptureRequest.Builder builder =
                    mCameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);

            builder.addTarget(surface);

            mCameraDevice.createCaptureSession(
                    Arrays.asList(surface),
                    new CameraCaptureSession.StateCallback() {

                        @Override
                        public void onConfigured(CameraCaptureSession session) {
                            CameraCaptureSession mSession = session;
                            try {
                                mSession.setRepeatingRequest(builder.build(),null,null);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onConfigureFailed(CameraCaptureSession session) {
                        }

                    },
                    null

            );
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    private CameraDevice.StateCallback mCameraCallback = new CameraDevice.StateCallback() {

        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            createCameraPreviewSession();
            Log.i("TAG", "Open camera  with id:" + mCameraDevice.getId());
        }

        @Override
        public void onDisconnected(CameraDevice camera) {
            mCameraDevice.close();

            Log.i("TAG", "disconnect camera  with id:" + mCameraDevice.getId());
            mCameraDevice = null;
        }

        @Override
        public void onError(CameraDevice camera, int error) {
            Log.i("TAG", "error! camera id:" + camera.getId()+" error:"+error);
        }
    };

}

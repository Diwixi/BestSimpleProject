package com.diwixis.bestsimpleproject.picturesProject.image;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.diwixis.bestsimpleproject.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bluemobi.dylan.photoview.library.PhotoView;

/**
 * Created by Diwixis on 02.10.2017.
 */

public class ShowPhotoActivity extends MvpAppCompatActivity {

    @BindView(R.id.photo)   PhotoView photo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_photo_activity);
        ButterKnife.bind(this);
        String photoUrl = IntentHelper.getPhotoUrl(getIntent());
        Picasso.with(this)
                .load(new File(photoUrl))
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .placeholder(R.mipmap.back_icon)
                .error(R.mipmap.error_icon)
                .into(photo);
    }

    public static class IntentHelper {
        private static final String EXTRA_PHOTO_URL = "EXTRA_PHOTO_URL";

        @NonNull
        public static Intent createIntent(@NonNull Context context, @NonNull String photoUrl) {
            Intent intent = new Intent(context, ShowPhotoActivity.class);
            intent.putExtra(EXTRA_PHOTO_URL, photoUrl);
            return intent;
        }

        static String getPhotoUrl(@NonNull Intent intent) {
            return intent.getStringExtra(EXTRA_PHOTO_URL);
        }
    }
}

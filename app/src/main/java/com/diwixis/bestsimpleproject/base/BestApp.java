package com.diwixis.bestsimpleproject.base;

import android.app.Application;
import android.content.Context;

import com.diwixis.bestsimpleproject.di.DaggerListAppComponent;
import com.diwixis.bestsimpleproject.di.ListAppComponent;

import io.realm.Realm;

/**
 * Created by Diwixis on 08.08.2017.
 */

public class BestApp extends Application {
    private static ListAppComponent listComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        listComponent = DaggerListAppComponent.builder()
                .build();
    }

    public static ListAppComponent getListComponent(){
        return listComponent;
    }
}

package com.kurio.mvvmtesting;

import android.app.Application;

import com.kurio.mvvmtesting.di.AppComponent;
import com.kurio.mvvmtesting.di.AppModule;
import com.kurio.mvvmtesting.di.ContextModule;
import com.kurio.mvvmtesting.di.DaggerAppComponent;
import com.kurio.mvvmtesting.di.RoomModule;

public class NoteApp extends Application {
    private AppComponent appComponent;


    @Override
    public void onCreate() {
        super.onCreate();
//        initDagger();

    }

//    public void initDagger() {
//        appComponent = DaggerAppComponent.builder()
//                .contextModule(new ContextModule(getApplicationContext()))
//                .roomModule(new RoomModule(getApplicationContext()))
//                .appModule(new AppModule())
//                .build();
//
//
//    }
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }
}

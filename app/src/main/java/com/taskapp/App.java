package com.taskapp;

import android.app.Application;

import androidx.room.Room;

import com.taskapp.room.MyDatabase;

public class App extends Application {

    public static App instance;

    private MyDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this,MyDatabase.class,"database")
                .allowMainThreadQueries()
                .build();
    }
    public MyDatabase getDatabase() {
        return database;
    }

    public static App getInstance() {
        return instance;
    }


}

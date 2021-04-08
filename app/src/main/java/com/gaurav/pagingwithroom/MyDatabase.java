package com.gaurav.pagingwithroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.gaurav.pagingwithroom.interfaces.MyDao;
import com.gaurav.pagingwithroom.modals.Bike;
import com.gaurav.pagingwithroom.modals.Car;

@Database(entities = { Bike.class, Car.class}, exportSchema = false, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    private static final String dbName = "MyDatabase";
    private static MyDatabase myDatabase;

    public static synchronized MyDatabase getInstance(Context ctx){
        if (myDatabase == null){
            myDatabase = Room.databaseBuilder(ctx, MyDatabase.class, dbName)
                    .fallbackToDestructiveMigrationFrom(1)
                    .build();
        }
        return myDatabase;
    }

    public abstract MyDao myDao();
}

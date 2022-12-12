package com.course.test3;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static UserDatabase INSTANCE;
    public abstract UserDao userDao();


    public static UserDatabase getAppDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, UserDatabase.class , "user-db").allowMainThreadQueries().build();
        }
        return  INSTANCE;
    }

}

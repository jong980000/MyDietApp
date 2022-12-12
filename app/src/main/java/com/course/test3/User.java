package com.course.test3;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int id;


    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "age")
    public String age;

    @ColumnInfo(name = "phoneNumber")
    public String phoneNumber;

    @ColumnInfo(name = "place")
    public String place;

    @ColumnInfo(name = "calorie")
    public String calorie;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name= "uri")
    public String uri;

}

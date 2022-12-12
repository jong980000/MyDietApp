package com.course.test3;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User")
    List<User> getALl();

    @Query("SELECT name FROM User") //이름만 조회
    List<String> getNameAll();

    @Query("SELECT age FROM User") //소감만 조회
    List<String> getAgeAll();

    @Query("SELECT phoneNumber FROM User") //식사시간만 조회
    List<String> getPhoneAll();

    @Query("SELECT place FROM User") //장소 조회
    List<String> getPlaceAll();

    @Query("SELECT calorie FROM User") //칼로리 조회
    List<String> getCalorieAll();

    @Query("SELECT date FROM User") //칼로리 조회
    List<String> getDateAll();

    @Query("SELECT image FROM User") //칼로리 조회
    List<String> getimageAll();



    @Insert
    void insertAll(User... users);

    @Delete
    void deleteAll(User... users);


}

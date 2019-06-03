package com.example.webbiestest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Dao
public interface MyDao {

    @Insert
    public void addData(List<MyData> data);

    @Query("select * from users")
    public LiveData<List<MyData>> readData();

}
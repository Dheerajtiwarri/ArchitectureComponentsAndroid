package com.example.webbiestest;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Dao
public interface MyDao {

    @Insert
    public void addData(MyData data);

    @Query("select * from users ORDER BY name ASC")
    DataSource.Factory<Integer, MyData> readData();
    
}
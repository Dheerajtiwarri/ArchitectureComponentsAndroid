package com.example.archComponents.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.archComponents.model.MyData;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Dao
public interface MyDao {

    @Insert
     void addData(MyData data);

    @Query("select * from users ORDER BY name ASC")
     DataSource.Factory<Integer, MyData> readData();
    
}
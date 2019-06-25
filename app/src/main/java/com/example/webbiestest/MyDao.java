package com.example.webbiestest;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
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
    public void addData(MyData data);

    @Query("select * from users ORDER BY name ASC")
    //public LiveData<List<MyData>> readData();
    DataSource.Factory<Integer, MyData> readData();


}
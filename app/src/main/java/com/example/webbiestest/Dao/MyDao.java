package com.example.webbiestest.Dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.webbiestest.MyData;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Dao
public interface MyDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertData(MyData data);

  @Query("select * from users ORDER BY name ASC")
  DataSource.Factory<Integer, MyData> readData();

  @Query("delete from users")
  void deleteAll();

}
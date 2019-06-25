package com.example.webbiestest;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Entity(tableName = "users")
public class MyData {
    public static final String TAG="MyData";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;
    public String name;
    public String image;

    public MyData() {
        //this class is needed...
    }

    public MyData(String name, String image) {
        Log.v(TAG,"MyData()");

        this.name = name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

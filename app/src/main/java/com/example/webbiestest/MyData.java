package com.example.webbiestest;


import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.webbiestest.BR;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
@Entity(tableName = "users")
public class MyData extends BaseObservable {
   private static final String TAG = "MyData";

   @PrimaryKey(autoGenerate = true)
   @NonNull
   public int id;
   private String name;
   private String image;


   public MyData() {
      //this class is needed...
   }

   public MyData(String name, String image) {
      this.name = name;
      this.image = image;
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   @Bindable
   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
      notifyPropertyChanged(BR.image);
   }

   @Bindable
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
      notifyPropertyChanged(BR.name);
   }

}

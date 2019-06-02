package com.example.webbiestest;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;

    private LiveData<List<MyData>> myAllData;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);
        myAllData = repository.getAllData();

    }

   public LiveData<List<MyData>> getAllData() {
        return myAllData;
    }

    public void setData() {
        repository.setData();
    }
}

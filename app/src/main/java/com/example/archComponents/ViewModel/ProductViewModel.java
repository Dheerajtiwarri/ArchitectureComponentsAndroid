package com.example.archComponents.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.archComponents.Dao.MyDao;
import com.example.archComponents.MyData;
import com.example.archComponents.Repository.ProductRepository;


/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
public class ProductViewModel extends AndroidViewModel {

    public ProductRepository repository;
    private MyDao myDao;

    private final LiveData<PagedList<MyData>> myAllData;

    public ProductViewModel(@NonNull Application application) {
        super(application);

        repository = new ProductRepository(application);
        myAllData = repository.getAllData();


        /****
         * it's directly get the data from the DAO without including the Repository in between.
         * generate error RuntimeException when activity required to hit the viewModel
         ****/

        //myAllData = new LivePagedListBuilder<>(myDao.readData(), 10).build();
    }

    public LiveData<PagedList<MyData>> getAllData() {
        return myAllData;
    }


    public void saveData(MyData myDataList) {
        repository.saveData(myDataList);
    }


}

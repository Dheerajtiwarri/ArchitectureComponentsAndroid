package com.example.webbiestest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
 class ProductRepository {

    private MyDao myDao;
    private LiveData<List<MyData>> allMyData;

    ProductRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        myDao = db.myDao();
        allMyData = myDao.readData();
    }

    LiveData<List<MyData>> getAllData() {
        return allMyData;
    }


    void insert(MyData myData) {
        new insertAsyncTask(myDao).execute(myData);
    }

    private static class insertAsyncTask extends AsyncTask<MyData, Void, Void> {

        private MyDao mAsyncTaskDao;

        insertAsyncTask(MyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyData... params) {
            mAsyncTaskDao.addData(params[0]);
            return null;
        }
    }

}

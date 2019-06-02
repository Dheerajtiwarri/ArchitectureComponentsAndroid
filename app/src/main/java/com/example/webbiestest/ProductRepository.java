package com.example.webbiestest;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

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

       // new insertAsyncTask(myDao).execute();  //TODO try in this way get same error no. one
    }

    LiveData<List<MyData>> getAllData() {
        return allMyData;
    }


    void setData() {
        new insertAsyncTask(myDao).execute();   //we are not setting data from addDataActivity any more.
    }

    private static class insertAsyncTask extends AsyncTask<MyData, Void, Void> {

        private MyDao mAsyncTaskDao;

        insertAsyncTask(MyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyData... params) {

            //  mAsyncTaskDao.addData(params[0]);
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

          /*  firebaseFirestore.collection("products").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                    *//*
                     MyData myData = documentSnapshot.toObject(MyData.class); //data fetch from firestore and converted into MyData object.
                      mAsyncTaskDao.addData(myData);    //data added to dao
                      *//* //not working. //TODO error no.1.


                    }

                }
            });*/


            firebaseFirestore.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                       MyData myData = documentSnapshot.toObject(MyData.class);
                       mAsyncTaskDao.addData(myData);   //TODO handler looper issue.
                    }
                                 //TODO have to add data to dao.... :(
                }
            });

            return null;
        }
    }

}

package com.example.webbiestest;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
class ProductRepository {

    private MyDao myDao;
    //  private LiveData<List<MyData>> allMyData;
    private FirebaseFirestore firebaseFirestore;
    private DocumentSnapshot ds;
    private Query query;

    private int PAGE_SIZE = 5;

    ProductRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        myDao = db.myDao();
        // allMyData = myDao.readData();
        // allMyData=new LivePagedListBuilder<>(myDao.readData(),10).build();
        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    LiveData<PagedList<MyData>> getAllData() {
        // return allMyData;
        return new LivePagedListBuilder<>(
                myDao.readData(),
                PAGE_SIZE)
                .setBoundaryCallback(new BoundaryCallback<MyData>())
                .build();

    }

    //have to implement set boundary call back.

    void saveData(MyData myData) {
        //insert data direct to room
        new InsertDataInRoomAsyncTask(myDao).execute(myData);

        //send data to fireStore
        sendDataToFireStore(myData);

    }


    //fetch data from fireStore.
    void fetchDataFromFireStore() {

        if (ds == null) {
            query = firebaseFirestore.collection("products")
                    .orderBy("name")
                    .limit(PAGE_SIZE);
        } else {
            query = firebaseFirestore.collection("products")
                    .orderBy("name")
                    .startAfter(ds)
                    .limit(PAGE_SIZE);
        }


        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                ds=queryDocumentSnapshots.getDocuments()
                        .get(queryDocumentSnapshots.size() -1);

                for (QueryDocumentSnapshot dataSnapshot : queryDocumentSnapshots) {
                    MyData myData = dataSnapshot.toObject(MyData.class);
                    new InsertAsyncTask(myDao).execute(myData);
                }
            }
        });
    }

    //send data to fireStore.
    public void sendDataToFireStore(MyData myData) {
        firebaseFirestore.collection("products").add(myData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.v(TAG, "Data saved");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.v(TAG, "Fail to Save Data");
            }
        });

    }


    private static class InsertAsyncTask extends AsyncTask<MyData, Void, Void> {

        private MyDao mAsyncTaskDao;

        InsertAsyncTask(MyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MyData... params) {
            mAsyncTaskDao.addData(params[0]);
            return null;
        }
    }


    private static class InsertDataInRoomAsyncTask extends AsyncTask<MyData, Void, Void> {
        private MyDao myDao;

        public InsertDataInRoomAsyncTask(MyDao myDao) {
            this.myDao = myDao;
        }

        @Override
        protected Void doInBackground(MyData... lists) {
            myDao.addData(lists[0]);

            return null;
        }
    }

   /* public PagedList.BoundaryCallback<T> listOfPage=new PagedList.BoundaryCallback<T>() {
        @Override
        public void onZeroItemsLoaded() {
           // super.onZeroItemsLoaded();
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
        }
    };*/

    public class BoundaryCallback<T> extends PagedList.BoundaryCallback<T> {

        @Override
        public void onZeroItemsLoaded() {
            // super.onZeroItemsLoaded();
            fetchDataFromFireStore();

        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            //super.onItemAtEndLoaded(itemAtEnd);
            fetchDataFromFireStore();
        }
    }

}

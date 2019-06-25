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

    private final static String TAG = "ProductRepository";


    private MyDao myDao;
    //  private LiveData<List<MyData>> allMyData;
    private FirebaseFirestore firebaseFirestore;
    private DocumentSnapshot ds;
    private Query query;

    private int lastIndexId;

    private int PAGE_SIZE = 5;

    ProductRepository(Application application) {
        Log.v(TAG, "ProductRepository()");
        MyDatabase db = MyDatabase.getDatabase(application);
        myDao = db.myDao();

        /****
         * till now all the fetching data from operation will perform with the help of LivePagedListBuilder //pagination
         * ****/
        // allMyData = myDao.readData();

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    LiveData<PagedList<MyData>> getAllData() {
        Log.v(TAG, "getAllData()");
        // return allMyData;
        return new LivePagedListBuilder<>(
                myDao.readData(),
                PAGE_SIZE)
                .setBoundaryCallback(new BoundaryCallback<MyData>())   //setting the BoundaryCallback that will help to identify that what is current requirement of application regarding data.
                .build();
    }

    void saveData(MyData myData) {
        Log.v(TAG, "saveData()");

        //insert data direct to room
        new InsertDataInRoomAsyncTask(myDao).execute(myData);

        //send data to fireStore
        sendDataToFireStore(myData);

    }


    //fetch data from fireStore.
    void fetchDataFromFireStore() {
        Log.v(TAG, "fetchDataFromFireStore()");

        if (lastIndexId == 0) {

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

                if (lastIndexId > 0) {
                    lastIndexId = queryDocumentSnapshots.size() - 1;
                    ds = queryDocumentSnapshots.getDocuments()
                            .get(lastIndexId);

                }


                for (QueryDocumentSnapshot dataSnapshot : queryDocumentSnapshots) {
                    MyData myData = dataSnapshot.toObject(MyData.class);
                    new InsertAsyncTask(myDao).execute(myData);
                }
            }
        });
    }

    //send data to fireStore.
    public void sendDataToFireStore(MyData myData) {
        Log.v(TAG, "sendDataToFireStore()");

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
            Log.v(TAG, "InsertAsyncTask.doInBackground()");

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
            Log.v(TAG, "InsertDataInRoomAsyncTask.doInBackground()");

            myDao.addData(lists[0]);

            return null;
        }
    }


    public class BoundaryCallback<T> extends PagedList.BoundaryCallback<T> {

        @Override
        public void onZeroItemsLoaded() {
            Log.v(TAG, "onZeroItemsLoaded()");

            fetchDataFromFireStore();
        }

        @Override
        public void onItemAtEndLoaded(@NonNull T itemAtEnd) {
            Log.v(TAG, "onItemAtEndLoaded()");

            fetchDataFromFireStore();
        }
    }

}

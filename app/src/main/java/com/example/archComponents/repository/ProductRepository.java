package com.example.archComponents.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.archComponents.dao.MyDao;
import com.example.archComponents.database.MyDatabase;
import com.example.archComponents.model.MyData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
public class ProductRepository {

   private final static String TAG = "ProductRepository";


   private MyDao myDao;
   private FirebaseFirestore fb;
   private CollectionReference collectionReference;
   private DocumentSnapshot ds;

   private int lastIndexId;
   private int PAGE_SIZE = 6;

   public ProductRepository(Application application) {
      Log.v(TAG, "ProductRepository()");
      MyDatabase db = MyDatabase.getDatabase(application);
      myDao = db.myDao();
      fb = FirebaseFirestore.getInstance();
      collectionReference = fb.collection("products");
   }

   public LiveData<PagedList<MyData>> getAllData() {
      Log.v(TAG, "getAllData()");
      return new LivePagedListBuilder<>(
            myDao.readData(),
            PAGE_SIZE)
            .setBoundaryCallback(new BoundaryCallback<MyData>())
            .build();
   }

   public void saveData(MyData myData) {
      Log.v(TAG, "saveData()");
      //  new InsertDataInRoomAsyncTask(myDao).execute(myData);

      sendDataToFireStore(myData);

   }


   private void fetchDataFromFireStore() {
      Query query;
      if (lastIndexId <= 0) {
         query = fb.collection("products")
               .orderBy("name")
               .limit(PAGE_SIZE);
      } else {
         query = fb.collection("products")
               .orderBy("name")
               .startAfter(ds)
               .limit(PAGE_SIZE);
      }


      query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
         @Override
         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
            if (lastIndexId != 0) {

               lastIndexId = queryDocumentSnapshots.size() - 1;
            } else {
               lastIndexId = queryDocumentSnapshots.size() - 1;
            }
            // lastIndexId = queryDocumentSnapshots.size()-1;
            ds = queryDocumentSnapshots.getDocuments().get(lastIndexId);
            for (QueryDocumentSnapshot dataSnapshot : queryDocumentSnapshots) {
               MyData myData = dataSnapshot.toObject(MyData.class);
               new InsertAsyncTask(myDao).execute(myData);
            }
         }
      })
            .addOnFailureListener(new OnFailureListener() {
               @Override
               public void onFailure(@NonNull Exception e) {
                  Log.i(TAG, "onFailure: " + e.toString());
                  Log.i(TAG, "onFailure: " + "data not found");
               }
            });
   }

   //send data to fireStore.
   private void sendDataToFireStore(MyData myData) {
      Log.v(TAG, "sendDataToFireStore()");

      fb.collection("products").add(myData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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


   /*private static class InsertDataInRoomAsyncTask extends AsyncTask<MyData, Void, Void> {
      private MyDao mAsyncTaskDao;

      InsertDataInRoomAsyncTask(MyDao myDao) {
         this.mAsyncTaskDao = myDao;
      }

      @Override
      protected Void doInBackground(MyData... lists) {
         Log.v(TAG, "InsertDataInRoomAsyncTask.doInBackground()");
         mAsyncTaskDao.addData(lists[0]);
         return null;
      }
   }*/


   class BoundaryCallback<T> extends PagedList.BoundaryCallback<T> {

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

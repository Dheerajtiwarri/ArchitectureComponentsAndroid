package com.example.webbiestest;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
class ProductRepository {

    private MyDao myDao;
    private LiveData<List<MyData>> allMyData;
    private FirebaseFirestore firebaseFirestore;

    ProductRepository(Application application) {
        MyDatabase db = MyDatabase.getDatabase(application);
        myDao = db.myDao();
        allMyData = myDao.readData();

        firebaseFirestore = FirebaseFirestore.getInstance();
    }

    LiveData<List<MyData>> getAllData() {
        return allMyData;
    }


    void fetchDataFromFireStore() {

        firebaseFirestore.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                List<MyData> listOfMyData = queryDocumentSnapshots.toObjects(MyData.class);
                new insertAsyncTask(myDao).execute(listOfMyData);
            }
        });
    }

    public void sendDataToFireStore(final Context context, MyData myData) {
        firebaseFirestore.collection("products").add(myData).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(context, "Data send Successfully...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Error ", Toast.LENGTH_LONG).show();
            }
        });

    }

    private static class insertAsyncTask extends AsyncTask<List<MyData>, Void, Void> {

        private MyDao mAsyncTaskDao;

        insertAsyncTask(MyDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final List<MyData>... params) {

            mAsyncTaskDao.addData(params[0]);

            return null;
        }
    }

}

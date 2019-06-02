package com.example.webbiestest.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webbiestest.Adapter.RecyclerAdapter;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ProductViewModel;
import com.example.webbiestest.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG="MainActivity";

    public static final int ADD_DATA_ACTIVITY_REQUEST_CODE=1;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

  //  private DatabaseReference databaseReference;
    private ProductViewModel productViewModel;

    private List<MyData> myDataList;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"onCreate()");

      //  databaseReference= FirebaseDatabase.getInstance().getReference("Products");

       // firebaseFirestore=FirebaseFirestore.getInstance();   //TODO for testing of data... Successful

    //    myDataList=new ArrayList<>();


        setUserInterface();
    }

    public void setUserInterface()
    {
        Log.v(TAG,"setUserInterface()");

        recyclerView=findViewById(R.id.recyclerView);
        floatingActionButton=findViewById(R.id.floating_button);

        final RecyclerAdapter recyclerAdapter=new RecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*Todo remove after testing.
        * for testing purpose of data is coming or not.
        * got data successfully */

       /* firebaseFirestore.collection("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "list is empty", Toast.LENGTH_SHORT).show();
                }else
                {
                    for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots)
                    {
                        MyData myData=documentSnapshot.toObject(MyData.class);
                        myDataList.add(myData);
                        // myDao.addData(myData);
                    }
                }

            }
        });*/

/*
* for realtime database working successfully
* */

       /* databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot productSnapshot:dataSnapshot.getChildren())
                {
                 MyData myData=productSnapshot.getValue(MyData.class);
                    myDataList.add(myData);

                }
                //recyclerAdapter.setData(myDataList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

       //try with this.
        productViewModel.setData();    //TODO Error no. 1.Cannot access database on the main thread since it may potentially lock the UI for a long period of time.

        /*
        * also try with another thread class with handler and looper.
        * got same error. no.1.
        * */




        productViewModel.getAllData().observe(this, new Observer<List<MyData>>() {
            @Override
            public void onChanged(List<MyData> myData) {
                Log.v(TAG,"DataSetToRecyclerAdapter");
                recyclerAdapter.setData(myData);

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG,"floatingButtonCLicked");
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivityForResult(intent, ADD_DATA_ACTIVITY_REQUEST_CODE);
            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        /*if (requestCode == ADD_DATA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
          //  MyData myData = new MyData(data.getStringExtra(AddDataActivity.PRODUCT_NAME),data.getStringExtra(AddDataActivity.PRODUCT_IMAGE));
            productViewModel.setData();
            Log.v(TAG,"OnActivityResult"+"get the data");
        } else {
            Log.v(TAG,"OnActivityResult"+"Data is empty..");
            Toast.makeText(
                    getApplicationContext(), "Data is empty....",
                    Toast.LENGTH_LONG).show();
        }*/
    }
}

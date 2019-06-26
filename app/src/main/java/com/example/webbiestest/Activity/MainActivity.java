package com.example.webbiestest.Activity;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webbiestest.R;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate()");
    }

   /* public void setUserInterface() {
        Log.v(TAG, "setUserInterface()");

        recyclerView = findViewById(R.id.recyclerView);
        floatingActionButton = findViewById(R.id.floating_button);

       // final RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this);
       // recyclerView.setAdapter(recyclerAdapter);

        //with PagedListAdapter
        final DataAdapter dataAdapter=new DataAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

      // productViewModel.fetchDataFromFireStore();  //trigger view model for fetching the data.


        productViewModel.getAllData().observe(this, new Observer<PagedList<MyData>>() {
            @Override
            public void onChanged(PagedList<MyData> myData) {
                dataAdapter.submitList(myData);
            }
        });

        recyclerView.setAdapter(dataAdapter);

        *//*productViewModel.getAllData().observe(this, new Observer<List<MyData>>() {
            @Override
            public void onChanged(List<MyData> myData) {
                Log.v(TAG, "DataSetToRecyclerAdapter");
               // recyclerAdapter.setData(myData);


            }
        });*//*

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "floatingButtonCLicked");
                Intent intent = new Intent(MainActivity.this, AddDataActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }*/
}

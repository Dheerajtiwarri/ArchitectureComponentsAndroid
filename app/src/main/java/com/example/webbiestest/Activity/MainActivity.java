package com.example.webbiestest.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static  final String TAG="MainActivity";

    public static final int ADD_DATA_ACTIVITY_REQUEST_CODE=1;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;


    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG,"onCreate()");

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

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.getAllData().observe(this, new Observer<List<MyData>>() {
            @Override
            public void onChanged(List<MyData> myData) {
                recyclerAdapter.setData(myData);
                Log.v(TAG,"DataSetToRecyclerAdapter");
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


        if (requestCode == ADD_DATA_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            MyData myData = new MyData(data.getStringExtra(AddDataActivity.PRODUCT_NAME),data.getByteArrayExtra(AddDataActivity.PRODUCT_IMAGE));
            productViewModel.insert(myData);
            Log.v(TAG,"OnActivityResult"+"get the data");
        } else {
            Log.v(TAG,"OnActivityResult"+"Data is empty..");
            Toast.makeText(
                    getApplicationContext(), "Data is empty....",
                    Toast.LENGTH_LONG).show();
        }
    }
}

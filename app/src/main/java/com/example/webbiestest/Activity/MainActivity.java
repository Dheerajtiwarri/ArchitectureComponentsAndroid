package com.example.webbiestest.Activity;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.webbiestest.R;
import com.example.webbiestest.ViewModel.ProductViewModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "onCreate()");
     //   productViewModel=ViewModelProvider(this).get(ProductViewModel::class.java);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}

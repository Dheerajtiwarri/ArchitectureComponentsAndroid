package com.example.archComponents.Activity;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.archComponents.R;

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

package com.example.webbiestest.Activity;


import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webbiestest.R;

public class MainActivity extends AppCompatActivity {
  private static final String TAG = "MainActivity";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Log.v(TAG, "onCreate()");
    //   productViewModel=ViewModelProvider(this).get(ProductViewModel::class.java);
  }


}

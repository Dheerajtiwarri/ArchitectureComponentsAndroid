package com.example.webbiestest;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.webbiestest.Fragments.AddProductFragment;

/**
 * Created by Dheeraj on 29-06-2019.
 * dheerajtiwarri@gmail.com
 */
public class MyHandlers {
    
    public Context context;
     public MyData myData;


    public MyHandlers(Context context) {
        this.context = context;

    }

    public void onCheckClick(String url)
    {

    }
    
    public void onSaveButtonClick(MyData myData)
    {
        this.myData=myData;

        Toast.makeText(context, myData.getName()+" \n "+myData.getImage(), Toast.LENGTH_SHORT).show();



    }

    @BindingAdapter("imageUrl")
    public static void getImage(ImageView view,String url)
    {
        Context context=view.getContext();
        Log.v("test",url);

        Glide.with(context.getApplicationContext()).load(url).into(view);
    }
}

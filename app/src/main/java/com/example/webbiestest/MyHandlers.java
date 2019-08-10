package com.example.webbiestest;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Created by Dheeraj on 29-06-2019.
 * dheerajtiwarri@gmail.com
 */
public class MyHandlers {
    
    public Context context;
    private ProductViewModel productViewModel;



    public MyHandlers(Context context,ProductViewModel productViewModel) {
        this.context = context;
        this.productViewModel=productViewModel;

    }

    public void onCheckClick(String url)
    {


        Toast.makeText(context,url,Toast.LENGTH_LONG).show();
    }
    
    public void onSaveButtonClick(MyData myData)
    {

        productViewModel.saveData(myData);
        Toast.makeText(context, myData.getName()+myData.getImage()+" data saved...Thank god ", Toast.LENGTH_SHORT).show();

       // Navigation.findNavController().navigate(R.id.homeFragment);

    }

    @BindingAdapter("imageUrl")
    public static void getImage(ImageView view,String url)
    {
        Context context=view.getContext();
     //   Log.v("test",url);

        Glide.with(context.getApplicationContext()).load(url).into(view);
    }
}

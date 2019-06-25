package com.example.webbiestest.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Dheeraj on 14-05-2019.
 * dheerajtiwarri@gmail.com
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewAdapter> {


    private static final String TAG = "RecyclerAdapter";

    //DataAdapter which extends PagedListAdapter take place of this adapter for paging purpose.

    public List<MyData> dataList = Collections.emptyList();
    private final LayoutInflater mInflater;
    private Context context;

    public RecyclerAdapter(Context context) {

        Log.v(TAG, "RecyclerAdapter");
        this.context = context;
        mInflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Log.v(TAG, "ViewAdapter");
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);

        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        Log.v(TAG, "onBindViewHolder");
        MyData myData = dataList.get(position);
        String name = myData.getName();                  //at this point I can check the recyclerView is working properly.
        String image = myData.getImage();

        holder.textView.setText(name);
        Glide.with(context).load(image).into(holder.imageView);


    }


    public void setData(List<MyData> myData) {
        Log.v(TAG, "setData");
        dataList = myData;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewAdapter extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            Log.v(TAG, "viewAdapter()");

            textView = (TextView) itemView.findViewById(R.id.product_display_name);
            imageView = (ImageView) itemView.findViewById(R.id.product_display_image);
        }
    }
}


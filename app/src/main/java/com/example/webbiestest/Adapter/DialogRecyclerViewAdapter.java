package com.example.webbiestest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.transition.Transition;
import com.example.webbiestest.MyData;
import com.example.webbiestest.R;
import com.example.webbiestest.databinding.LayoutCheckTestBinding;

import java.util.ArrayList;
import java.util.List;

public class DialogRecyclerViewAdapter extends RecyclerView.Adapter<DialogRecyclerViewAdapter.CustomViewHolder>  {

    private Context context;
    private List<MyData> dataList=new ArrayList<>();

    public DialogRecyclerViewAdapter(Context context, List<MyData> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutCheckTestBinding layoutCheckTestBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_check_test,parent,false);

        CustomViewHolder customViewHolder=new CustomViewHolder(layoutCheckTestBinding);

        return customViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {


        MyData myData=dataList.get(position);
        if(myData!=null)
        {
            holder.itemBinding.setMyData(myData);
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder
   {
       LayoutCheckTestBinding itemBinding;

       public CustomViewHolder(LayoutCheckTestBinding itemBinding) {
           super(itemBinding.getRoot());

           this.itemBinding=itemBinding;
       }
   }
}

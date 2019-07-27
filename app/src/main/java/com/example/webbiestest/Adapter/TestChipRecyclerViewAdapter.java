package com.example.webbiestest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.webbiestest.TestChipModel;
import com.example.webbiestest.R;
import com.example.webbiestest.databinding.LayoutTestChipBinding;

import java.util.ArrayList;
import java.util.List;

public class TestChipRecyclerViewAdapter extends RecyclerView.Adapter<TestChipRecyclerViewAdapter.CustomViewHolder> {

     private static final String TAG = "TestChipRecyclerViewAda";

     private Context context;
     private List<TestChipModel> testChipModelList;

     public TestChipRecyclerViewAdapter() {
     }

     public TestChipRecyclerViewAdapter(Context context, List<TestChipModel> testChipModelList) {
          this.context = context;
          this.testChipModelList = testChipModelList;
     }

     @NonNull
     @Override
     public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

          LayoutTestChipBinding layoutTestChipBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_check_test,
                    parent, false);

          CustomViewHolder customViewHolder = new CustomViewHolder(layoutTestChipBinding);

          return customViewHolder;
     }

     @Override
     public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

          TestChipModel testChipModel = new TestChipModel();
          if (testChipModel != null) {
               holder.itemBinding.setChipData(testChipModel);
          }

     }

     public void setList(ArrayList<TestChipModel> testChipModelList)
     {
          this.testChipModelList=testChipModelList;
          notifyDataSetChanged();
     }

     @Override
     public int getItemCount() {
          return testChipModelList.size();
     }

     public class CustomViewHolder extends RecyclerView.ViewHolder
     {

          LayoutTestChipBinding itemBinding;

          public CustomViewHolder(@NonNull LayoutTestChipBinding itemBinding) {
               super(itemBinding.getRoot());

               this.itemBinding=itemBinding;
          }
     }
}

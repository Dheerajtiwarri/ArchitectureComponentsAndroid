package com.example.webbiestest.Fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.webbiestest.Adapter.TestChipRecyclerViewAdapter;
import com.example.webbiestest.TestChipModel;
import com.example.webbiestest.R;
import com.example.webbiestest.ChipTestDataViewModel;
import com.example.webbiestest.databinding.FragmentTestChipBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestChipFragment extends Fragment {

     private static final String TAG = "TestChipFragment";

     private ChipTestDataViewModel viewModel;

     private TestChipRecyclerViewAdapter adapter;

     private FragmentTestChipBinding mBinding;


     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
          // Inflate the layout for this fragment
          mBinding= DataBindingUtil.inflate(getLayoutInflater(),R.layout.fragment_test_chip,container,false);
          View testChipFragment=mBinding.getRoot();

          viewModel= ViewModelProviders.of(this).get(ChipTestDataViewModel.class);


          viewModel.getUpdatedData().observe(getViewLifecycleOwner(), new Observer<ArrayList<TestChipModel>>() {
               @Override
               public void onChanged(ArrayList<TestChipModel> testChipModels) {
                    Log.i(TAG, "onChanged: check the data coming from ViewModel" + testChipModels);
                  //  adapter.setList(testChipModels);
               }
          });

          init();

          return testChipFragment;
     }

     public void init()
     {


          ArrayList<TestChipModel> list=new ArrayList<>();
          for(int i=0;i<=5;i++)
          {
               list.add(new TestChipModel(i,"Dheeraj","Psycho is the gaming id of dheeraj"));
          }
          viewModel.setUpdatedData(list);

          mBinding.chipRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
          mBinding.chipRecyclerView.setHasFixedSize(true);
          adapter=new TestChipRecyclerViewAdapter(getContext(),list);
          mBinding.chipRecyclerView.setAdapter(adapter);
     }

}

package com.example.webbiestest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class ChipTestDataViewModel extends ViewModel {
     public MutableLiveData<ArrayList<TestChipModel>> updatedData=new MutableLiveData<>();

     public LiveData<ArrayList<TestChipModel>> getUpdatedData() {
          return updatedData;
     }

     public void setUpdatedData(ArrayList<TestChipModel> list) {

           updatedData.setValue(list);
     }
}

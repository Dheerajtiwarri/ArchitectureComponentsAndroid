package com.example.webbiestest.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.webbiestest.Adapter.DataAdapter;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ViewModel.ProductViewModel;
import com.example.webbiestest.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


   private static final String TAG = "HomeFragment";
   public Button button;
   private View viewHomeFragment;


   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
      viewHomeFragment = inflater.inflate(R.layout.fragment_home, container, false);
      setUserInterface();
      return viewHomeFragment;
   }

   @Override
   public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      super.onViewCreated(view, savedInstanceState);
   }


   private void setUserInterface() {
      Log.v(TAG, "setUserInterface()");
      RecyclerView recyclerView = viewHomeFragment.findViewById(R.id.recyclerView);
      FloatingActionButton floatingActionButton = viewHomeFragment.findViewById(R.id.floating_button);

      //with PagedListAdapter
      final DataAdapter dataAdapter = new DataAdapter(getActivity());
      recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

      ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
      productViewModel.getAllData().observe(this, new Observer<PagedList<MyData>>() {
         @Override
         public void onChanged(PagedList<MyData> myData) {
            dataAdapter.submitList(myData);
         }
      });

      recyclerView.setAdapter(dataAdapter);
      floatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

            Navigation.findNavController(viewHomeFragment).navigate(R.id.addProductFragment);
         }
      });
   }

}

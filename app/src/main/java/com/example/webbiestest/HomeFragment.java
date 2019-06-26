package com.example.webbiestest;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {



    public Button button;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        button=(Button)view.findViewById(R.id.goToAddNewProductBtn);
       // button.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.toAddProductFragment));   //First way to achieve navigation component

         button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Navigation.findNavController(view).navigate(R.id.toAddProductFragment);
           }
       });
    }
}

package com.example.webbiestest.Fragments;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.BindingEvents.AddProductFragmentEventHandlers;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ProductViewModel;
import com.example.webbiestest.R;
import com.example.webbiestest.databinding.FragmentAddProductBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    private static final String TAG = "AddProductFragment";

    private ImageView productImage;
    private EditText productName, imageUrl;
    private ProductViewModel productViewModel;
    private String url = "";


    private AddProductFragmentEventHandlers handlers;


    private View viewAddProductFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //viewAddProductFragment=inflater.inflate(R.layout.fragment_add_product, container, false);
        FragmentAddProductBinding fragmentAddProductBinding =DataBindingUtil.inflate(inflater,R.layout.fragment_add_product,container,false);

        viewAddProductFragment=fragmentAddProductBinding.getRoot();

        handlers=new AddProductFragmentEventHandlers(getActivity());

        fragmentAddProductBinding.setClickEvents(handlers);


       // setUserInterface();

        return viewAddProductFragment;
    }



    private void setUserInterface() {

        Log.v(TAG, "setUserInterface()");

        productImage = viewAddProductFragment.findViewById(R.id.product_image);
        productName = viewAddProductFragment.findViewById(R.id.product_name);
        Button productSave = viewAddProductFragment.findViewById(R.id.product_save);

        imageUrl = viewAddProductFragment.findViewById(R.id.image_url);
        Button checkImage = viewAddProductFragment.findViewById(R.id.check_image_button);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        checkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "checkImage");
                url = imageUrl.getText().toString();
                Glide.with(getActivity().getApplicationContext()).load(url).into(productImage);

            }
        });


        productSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "productSave");


                String nameProduct = productName.getText().toString();
                String imageProduct = imageUrl.getText().toString();
                //set data on my data.
                MyData myData = new MyData(nameProduct, imageProduct);

                productViewModel.saveData(myData);

                //getActivity().onBackPressed();


               /* NavOptions.Builder navBuilder = new NavOptions.Builder();   //not working, onBackPress it just refresh the homeFragment(passed on the below )
                NavOptions navOptions = navBuilder.setPopUpTo(R.id.homeFragment, false).build();*/



                Navigation.findNavController(viewAddProductFragment).navigate(R.id.homeFragment);
            }
        });
    }




}

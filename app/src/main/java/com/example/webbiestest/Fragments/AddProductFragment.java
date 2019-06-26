package com.example.webbiestest.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ProductViewModel;
import com.example.webbiestest.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddProductFragment extends Fragment {

    private static final String TAG = "AddProductFragment";

    private ImageView productImage;
    private EditText productName, imageUrl;
    private ProductViewModel productViewModel;
    private String url = "";
    // --Commented out by Inspection (26-06-2019 09:40 PM):private List<MyData> myDataList;

    private View viewAddProductFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewAddProductFragment= inflater.inflate(R.layout.fragment_add_product, container, false);

        setUserInterface();

        return viewAddProductFragment;
    }



    private void setUserInterface() {

        Log.v(TAG, "setUserInterface()");

        productImage = viewAddProductFragment.findViewById(R.id.product_image);
        productName = viewAddProductFragment.findViewById(R.id.product_name);
        Button productSave = viewAddProductFragment.findViewById(R.id.product_save);

        imageUrl = viewAddProductFragment.findViewById(R.id.image_url);
        Button checkImage = viewAddProductFragment.findViewById(R.id.check_image_button);

       // myDataList = new ArrayList<>();

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

                /*Intent intent = new Intent(AddDataActivity.this, MainActivity.class);
                String nameProduct = productName.getText().toString();
                String imageProduct = imageUrl.getText().toString();
                //set data on my data.
                MyData myData = new MyData(nameProduct, imageProduct);

                productViewModel.saveData(myData)

                startActivity(intent);
                finish();*/

                String nameProduct = productName.getText().toString();
                String imageProduct = imageUrl.getText().toString();
                //set data on my data.
                MyData myData = new MyData(nameProduct, imageProduct);

                productViewModel.saveData(myData);

                getActivity().onBackPressed();
            }
        });
    }

}
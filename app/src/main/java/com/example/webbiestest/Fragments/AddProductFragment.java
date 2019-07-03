package com.example.webbiestest.Fragments;


import android.content.Context;
import android.media.Image;
import android.graphics.Matrix;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyHandlers;
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
    public ProductViewModel productViewModel;
    private String url = "";
    private View viewAddProductFragment;
    private ScaleGestureDetector sgd;

    private FragmentAddProductBinding binding;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //viewAddProductFragment=inflater.inflate(R.layout.fragment_add_product, container, false);
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_add_product, container, false);
        viewAddProductFragment = binding.getRoot();
        context.getApplicationContext();

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        MyData myData = new MyData();
        MyHandlers handlers = new MyHandlers(getContext(),productViewModel);
        binding.setMyData(myData);
        binding.setClickEvents(handlers);

        viewAddProductFragment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                sgd.onTouchEvent(event);
                return true;
            }
        });


        // setUserInterface();

        return viewAddProductFragment;
    }


    @BindingAdapter("zoomPicTest")
    public void setImage(ImageView imageView,String url)
    {

        sgd=new ScaleGestureDetector(getContext(),new ScaleListener(imageView));
        Log.v(TAG,url);

    }



    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

       private ImageView imageView;

        public ScaleListener(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            Float  scale = detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 0.5f));
            Matrix matrix=new Matrix();
            matrix.postScale(scale, scale);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // handlers=new MyHandlers(getContext());
        //  binding.setClickEvents(handlers);
    }

   /* private void setUserInterface() {

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


               *//* NavOptions.Builder navBuilder = new NavOptions.Builder();   //not working, onBackPress it just refresh the homeFragment(passed on the below )
                NavOptions navOptions = navBuilder.setPopUpTo(R.id.homeFragment, false).build();*//*


                Navigation.findNavController(viewAddProductFragment).navigate(R.id.homeFragment);
            }
        });
    }*/


}

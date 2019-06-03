package com.example.webbiestest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.ProductViewModel;
import com.example.webbiestest.R;


public class AddDataActivity extends AppCompatActivity {

    private static final String TAG = "AddDataActivity";

    private ImageView productImage;
    private EditText productName, imageUrl;
    private ProductViewModel productViewModel;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Log.v(TAG, "onCreate()");

        setUserInterface();

    }

    public void setUserInterface() {

        Log.v(TAG, "setUserInterface()");

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        Button productSave = findViewById(R.id.product_save);

        imageUrl = findViewById(R.id.image_url);
        Button checkImage = findViewById(R.id.check_image_button);

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        checkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "checkImage");
                url = imageUrl.getText().toString();
                Glide.with(getApplicationContext()).load(url).into(productImage);

            }
        });


        productSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG, "productSave");

                Intent intent = new Intent(AddDataActivity.this, MainActivity.class);
                String nameProduct = productName.getText().toString();
                String imageProduct = imageUrl.getText().toString();
                //set data on my data.
                MyData myData = new MyData(nameProduct, imageProduct);
                //call viewModel to carry data.
                productViewModel.sendDataToFireStore(AddDataActivity.this, myData);

                startActivity(intent);
                finish();
            }
        });
    }
}

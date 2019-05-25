package com.example.webbiestest.Activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.webbiestest.MyData;
import com.example.webbiestest.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddDataActivity extends AppCompatActivity {

    private static final String TAG = "AddDataActivity";

    public static final String PRODUCT_NAME = "NameOfProduct";
    public static final String PRODUCT_IMAGE = "ImageOfProduct";

    private ImageView productImage;
    private EditText productName,imageUrl;
    private Button productSave,checkImage;


    private DatabaseReference databaseReference;


    private String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        Log.v(TAG, "onCreate()");

        databaseReference= FirebaseDatabase.getInstance().getReference();

        setUserInterface();

    }

    public void setUserInterface() {

        Log.v(TAG,"setUserInterface()");

        productImage = findViewById(R.id.product_image);
        productName = findViewById(R.id.product_name);
        productSave = findViewById(R.id.product_save);

        imageUrl=findViewById(R.id.image_url);
        checkImage=findViewById(R.id.check_image_button);

        checkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                url=imageUrl.getText().toString();
                Glide.with(getApplicationContext()).load(url).into(productImage);

            }
        });


        productImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (ActivityCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Log.v(TAG, "productImage clicked");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                2000);
                    }
                } else {
                    productImage.setBackgroundResource(android.R.color.transparent);
                    startGallery();
                    Log.i(TAG,"startGallery()");
                }*/
            }
        });


        productSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v(TAG,"productSave");

                Intent intentReply = new Intent();
                if (TextUtils.isEmpty(productName.getText()) || TextUtils.isEmpty(imageUrl.getText())) {
                    setResult(RESULT_CANCELED, intentReply);
                } else {
                    String nameProduct = productName.getText().toString();
                    String imageProduct =imageUrl.getText().toString();

                    sendData(nameProduct,imageProduct);

                    intentReply.putExtra(PRODUCT_NAME, nameProduct);
                    intentReply.putExtra(PRODUCT_IMAGE, imageProduct);
                    setResult(RESULT_OK, intentReply);
                }
                finish();


            }
        });
    }

    public void sendData(String nameProduct,String imageProduct)
    {

        String id=databaseReference.push().getKey();
        MyData myData=new MyData(nameProduct,imageProduct);
        String time= String.valueOf(System.currentTimeMillis());
        databaseReference.child("Products").setValue(myData);
    }


    private void startGallery() {
        Log.v(TAG,"startGallery()");
        Intent cameraIntent = new Intent(Intent.ACTION_GET_CONTENT);
        cameraIntent.setType("image/*");
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(cameraIntent, 1000);
        }
    }

    public byte[] imageInsert(ImageView image) {
        Log.v(TAG,"imageInsert()");
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        return bytes;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK) {
            if (requestCode == 1000) {
                Uri returnUri = data.getData();
                Bitmap bitmapImage = null;
                try {
                    bitmapImage = MediaStore.Images.Media.getBitmap(getContentResolver(), returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmapImage = getResizedBitmap(bitmapImage, 100);
                productImage.setImageBitmap(bitmapImage);
            }

        }
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        Log.v(TAG,"getResizedBitmap()");
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}

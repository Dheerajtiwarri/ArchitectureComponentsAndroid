package com.example.webbiestest.ExtraExperiment.ImageZoomTest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import com.example.webbiestest.R;
import com.example.webbiestest.ExtraExperiment.ZoomableImageView;

public class ImageZoom extends Activity {

    /*private ScaleGestureDetector SGD;
    private ImageView imageView;
    Matrix matrix;
    Float  scale=1.0f;*/

    private ScaleGestureDetector mScaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_zoom);

       /* mImageView = (ImageView) findViewById(R.id.abc);
        mScaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());*/




        //converting drawable into bitmap.
        Drawable myDrawable = getResources().getDrawable(R.drawable.download);
        Bitmap anImage      = ((BitmapDrawable) myDrawable).getBitmap();

        //TODO working only have to implement using direct drawable option.
        ZoomableImageView zoomableImageView=(ZoomableImageView)findViewById(R.id.image);
        zoomableImageView.setImageBitmap(anImage);

        //TODO need to add drawable option for it.
        //zoomableImageView.setImageResource(R.drawable.download);

        // working approach ...but using dependency
       /* PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.download);*/

       /* imageView=findViewById(R.id.imageView);
        matrix=new Matrix();
        SGD=new ScaleGestureDetector(this,new ScaleListener());*/
    }




    //Todo working perfect have to place image over dialog box that will help not to change the actual size of the image.
   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        return mScaleGestureDetector.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        // when a scale gesture is detected, use it to resize the image
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector){
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mImageView.setScaleX(mScaleFactor);
            mImageView.setScaleY(mScaleFactor);
            return true;
        }
    }*/















   /* @Override
    public boolean onTouchEvent(MotionEvent event) {
        SGD.onTouchEvent(event);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
           scale = detector.getScaleFactor();
            scale = Math.max(0.1f, Math.min(scale, 0.5f));
            matrix.setScale(scale, scale);
            imageView.setImageMatrix(matrix);
            return true;
        }
    }*/
}

package com.example.webbiestest.ImageZoomTest.SecondType;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.webbiestest.R;

public class SecondTestActivity extends AppCompatActivity implements View.OnClickListener {

    private Button enableZoomBtn;
   // private DrawableView drawbleView;  //this class help to draw lines on the display.
    private CustomImageView touchImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_test);
      //  drawbleView = (DrawableView) findViewById(R.id.drawble_view);
        enableZoomBtn = (Button) findViewById(R.id.enable_zoom);
        touchImageView = (CustomImageView) findViewById(R.id.zoom_iv);
        enableZoomBtn.setOnClickListener(this);
      //  drawbleView.setDrawingEnabled(false);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.enable_zoom:
                if(enableZoomBtn.getText().equals("disable zoom")){
                    touchImageView.setZoomEnable(false);
                  //  drawbleView.setDrawingEnabled(true);
                    enableZoomBtn.setText("enable zoom");
                } else{
                    touchImageView.setZoomEnable(true);
                 //   drawbleView.setDrawingEnabled(false);
                    enableZoomBtn.setText("disable zoom");
                }
                break;

            default:
                break;
        }
    }
}


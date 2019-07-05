package com.example.webbiestest.SpanAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import com.example.webbiestest.R;

public class SpanTestActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_test);

        init();
        //simpleSpanTest();
    }

    public void init()
    {
        textView=(TextView) findViewById(R.id.textView);
        String string ="I want This and This to be colored";
        SpannableString spannableString=new SpannableString(string);

        ForegroundColorSpan fgcRed= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            fgcRed = new ForegroundColorSpan(getColor(R.color.colorAccent));
        }
        ForegroundColorSpan fgcGreen=new ForegroundColorSpan(Color.GREEN);

        spannableString.setSpan(fgcRed,7,11, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(fgcGreen,16,20,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(string);
    }


    public void simpleSpanTest(String string)
    {






    }
}

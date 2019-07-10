package com.example.webbiestest.SpanAndroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.example.webbiestest.R;

public class SpanTestActivity extends AppCompatActivity {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_span_test);

        textView=(TextView) findViewById(R.id.textView);
        textView.setText("First I tried everything that I have read on stackoverflow...from updating gradle to XY version, to updating ConstraintLayout to XY version...I even update my SDK tools and Android Studio to the latest version...but nothing was working.\n" +
                "\n" +
                "The only solution that worked for me was that I delete ConstraintLayout library from gradle and SDK, then I opened random xml layout and in Design view under Palette section search for ConstraintLayout. If you have successfully deleted library from your project then you will be able to install the library from there if you double clicked on ConstraintLayout element.");
        makeTextViewResizable(textView,2,"See More",true);
        init();
        //simpleSpanTest();
    }

    public void init()
    {

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

    // this method contain definition of expandable textView

    public static void makeTextViewResizable(final TextView tv, final int maxLine, final String expandText, final boolean seeMore) {

        if (tv.getTag() == null) {
            tv.setTag(tv.getText());
        }
        ViewTreeObserver vto = tv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                String text;
                int lineEndIndex;
                ViewTreeObserver obs = tv.getViewTreeObserver();
                obs.removeGlobalOnLayoutListener(this);
                if (maxLine == 0) {
                    lineEndIndex = tv.getLayout().getLineEnd(0);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                    lineEndIndex = tv.getLayout().getLineEnd(maxLine - 1);
                    text = tv.getText().subSequence(0, lineEndIndex - expandText.length() + 1) + " " + expandText;
                } else {
                    lineEndIndex = tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1);
                    text = tv.getText().subSequence(0, lineEndIndex) + " " + expandText;
                }
                tv.setText(text);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
                tv.setText(
                        addClickablePartTextViewResizable(Html.fromHtml(tv.getText().toString()), tv, lineEndIndex, expandText,
                                seeMore), TextView.BufferType.SPANNABLE);
            }
        });

    }


    private static SpannableStringBuilder addClickablePartTextViewResizable(final Spanned strSpanned, final TextView tv,
                                                                            final int maxLine, final String spanableText, final boolean viewMore) {
        String str = strSpanned.toString();
        SpannableStringBuilder ssb = new SpannableStringBuilder(strSpanned);

        if (str.contains(spanableText)) {
            ssb.setSpan(new ClickableSpan() {

                @Override
                public void onClick(View widget) {
                    tv.setLayoutParams(tv.getLayoutParams());
                    tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE);
                    tv.invalidate();
                    if (viewMore) {
                        makeTextViewResizable(tv, -1, "...See Less", false);
                    } else {
                        makeTextViewResizable(tv, 3, "...See More", true);
                    }

                }
            }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length(), 0);

        }
        return ssb;

    }
}

package com.justin.drudgereader.customviews;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Justin on 5/1/2015.
 */
public class ReaderTextView extends TextView {
    public ReaderTextView(Context context) {
        super(context);
        setFont();
    }

    public ReaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont();
    }

    public ReaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont();
    }

    private void setFont() {
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/Lora-Regular.ttf");
        setTypeface(font, Typeface.NORMAL);
    }
}

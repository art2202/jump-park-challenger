package com.example.jumpparkchallenger.presentation.widget;

import android.content.Context;
import android.text.InputFilter;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyTextInputEditText extends TextInputEditText {
    public MyTextInputEditText(Context context) {
        super(context);
        init(null);
    }

    public MyTextInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MyTextInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public void init(@Nullable AttributeSet attrs) {
        InputFilter[] fs = getFilters();
        List<InputFilter> filters = new ArrayList<>();

        if(fs != null && fs.length > 0) filters.addAll(Arrays.asList(fs));

        filters.add(new InputFilter.AllCaps());

        setFilters(filters.toArray(new InputFilter[0]));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setAlpha(isEnabled() ? 1f : 0.6f);
    }
}
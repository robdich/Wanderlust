package com.robdich.wanderlust.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

/**
 * Created by robert on 2/4/2015.
 */
public class SpannableStringHelper{

    private SpannableStringBuilder mBuilder;

    private static final Character SPACE = ' ';

    public SpannableStringHelper(){
        mBuilder = new SpannableStringBuilder();
    }

    public SpannableStringHelper appendBold(String str){
        return appendBold(str, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper appendBold(String str, int flags){
        return append(str, new StyleSpan(android.graphics.Typeface.BOLD), flags);
    }

    public SpannableStringHelper appendItalic(String str){
        return appendItalic(str, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper appendItalic(String str, int flags){
        return append(str, new StyleSpan(Typeface.ITALIC), flags);
    }

    public SpannableStringHelper appendBoldItalic(String str){
        return appendBoldItalic(str, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper appendBoldItalic(String str, int flags){
        return append(str, new StyleSpan(Typeface.BOLD_ITALIC), flags);
    }

    public SpannableStringHelper appendColored(String str, int color){
        return appendColored(str, color, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper appendColored(String str, int color, int flags){
        return append(str, new ForegroundColorSpan(color), flags);
    }

    public SpannableStringHelper appendStyled(Context context, String str, int style){
        return appendStyled(context, str, style, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper appendStyled(Context context, String str, int style, int flags){
        return append(str, new TextAppearanceSpan(context, style), flags);
    }

    public SpannableStringHelper appendSpace(){
        mBuilder.append(SPACE);
        return  this;
    }

    public SpannableStringHelper append(String str){
        mBuilder.append(str);
        return this;
    }

    public SpannableStringHelper append(String str, Object span){
        return append(str, span, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    public SpannableStringHelper append(String str, Object span, int flags){
        int start = mBuilder.length();
        mBuilder.append(str);
        mBuilder.setSpan(span, start, mBuilder.length(), flags);
        return this;
    }

    public void setText(TextView textView){
        textView.setText(mBuilder);
    }

}

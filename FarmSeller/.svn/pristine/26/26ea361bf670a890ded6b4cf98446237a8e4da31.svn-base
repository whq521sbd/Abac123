package com.aotuo.vegetable.util;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

import com.aotuo.vegetable.R;

public class ShowPrice {
    
    public static void setText(Context context,TextView tv, String header, String price, String end){
    	if(!price.contains("."))
    		price += ".00";
    	int pos1 = header.length();
    	int pos2 = pos1 + price.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + price + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
    
    public static void setText(Context context,TextView tv, String header, double price, String end){
    	String p = String.format("%.2f", price);
    	int pos1 = header.length();
    	int pos2 = pos1 + p.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + p + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_gray_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
    
    public static void setTextWhite(Context context,TextView tv, String header, String price, String end){
    	if(!price.contains("."))
    		price += ".00";
    	int pos1 = header.length();
    	int pos2 = pos1 + price.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + price + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
    
    public static void setTextWhite(Context context,TextView tv, String header, double price, String end){
    	String p = String.format("%.2f", price);
    	int pos1 = header.length();
    	int pos2 = pos1 + p.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + p + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_white_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
    
    public static void setTextRed(Context context,TextView tv, String header, String price, String end){
    	if(!price.contains("."))
    		price += ".00";
    	int pos1 = header.length();
    	int pos2 = pos1 + price.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + price + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
    
    public static void setTextRed26(Context context,TextView tv, String header, String price, String end){
    	if(!price.contains("."))
    		price += ".00";
    	int pos1 = header.length();
    	int pos2 = pos1 + price.length() - 2;
    	int pos3 = pos2 + 2 + end.length();
    	SpannableString styledText = new SpannableString(header + price + end);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red26_h), 0, pos1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red26_m), pos1, pos2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	styledText.setSpan(new TextAppearanceSpan(context, R.style.price_red26_h), pos2, pos3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    	tv.setText(styledText, TextView.BufferType.SPANNABLE);
    }
}

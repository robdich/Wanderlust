package com.robdich.wanderlust.view;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.robdich.wanderlust.R;

public class AutoSizeImageView extends ImageView{
	
	private int mBaseWidth = 0;
	private int mBaseHeight = 0;

	public AutoSizeImageView(Context context) {
		super(context);
	}
	
	public AutoSizeImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttributes(attrs);
	}
	
	public AutoSizeImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initAttributes(attrs);
    }
	
	private void initAttributes(AttributeSet attrs){
		TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.AutoSizeImage, 0, 0);
		
		try{
			mBaseWidth = typedArray.getInt(R.styleable.AutoSizeImage_base_width, mBaseWidth);
			mBaseHeight = typedArray.getInt(R.styleable.AutoSizeImage_base_height, mBaseHeight);
		}finally{
			typedArray.recycle();
		}		
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
	    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	 
	    int width = getMeasuredWidth();
	    int adjustedHeight = getAdjustedHeight(mBaseWidth, mBaseHeight, width);
	 
	    setMeasuredDimension(width, adjustedHeight);
	}
	
	private int getAdjustedHeight(int baseWidth, int baseHeight, int imageWidth){		
		return (int) ((imageWidth * baseHeight) / baseWidth);
	}

}

package com.robdich.wanderlust.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ClippedLinearLayout extends LinearLayout {

	public ClippedLinearLayout(Context context) {
		super(context);
	}
	
	public ClippedLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int orientation = getOrientation();
		
		int measuredDimension = 0;
		for (int i = 0; i < getChildCount(); i++) {
			
			final View child = getChildAt(i);
			LayoutParams lp = (LayoutParams) child.getLayoutParams();

            if(orientation == LinearLayout.HORIZONTAL){
            	
            	int childWidth = lp.width + lp.leftMargin + lp.rightMargin + 
            			child.getPaddingLeft() + child.getPaddingRight();
            	measuredDimension += childWidth;
            	
            	if(measuredDimension > getMeasuredWidth()) child.setVisibility(GONE);
            	
            }else {
            	
    			int childHeight = lp.height + lp.topMargin + lp.bottomMargin + 
            			child.getPaddingTop() + child.getPaddingBottom();
            	measuredDimension += childHeight;
            	
            	if(measuredDimension > getMeasuredHeight()) child.setVisibility(GONE);
            	
			}
            
        }
		
	}

}
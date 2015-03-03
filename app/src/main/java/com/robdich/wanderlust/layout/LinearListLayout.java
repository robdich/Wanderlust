package com.robdich.wanderlust.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

public class LinearListLayout extends LinearLayout implements View.OnClickListener {

	private OnClickListener onCLickListener;
	
	public LinearListLayout(Context context) {
		super(context);
		initialize();
	}
	
	public LinearListLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
    }
	
	public LinearListLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initialize();
    }
	
	private void initialize(){
		setOrientation(VERTICAL);
	}
	
	public void setAdapter(ArrayAdapter<?> list) {		
		ArrayAdapter<?> mList = list;
		View view;
		//Popolute list
		if (mList != null) {
		    for (int i = 0; i < mList.getCount(); i++) {
		        view = mList.getView(i, null, null);
		        this.addView(view);
		    }
		}
	}
	
	public void setListener(OnClickListener listener) {
		this.onCLickListener = listener;
	}

	@Override
	public void onClick(View v) {
		
	}

}

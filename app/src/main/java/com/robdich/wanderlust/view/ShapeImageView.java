package com.robdich.wanderlust.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.robdich.wanderlust.R;
import com.robdich.wanderlust.utils.ImageUtils;
import com.robdich.wanderlust.utils.ImageUtils.ScalingLogic;


public class ShapeImageView extends ImageView {

    public enum ResourceType{
    	NONE(0), PATH(1), RES_ID(2);

        private int type;

        ResourceType(int type){
            this.type = type;
        }

        public int toInt(){
            return type;
        }

        public static ResourceType getResourceType(int type){
            for(ResourceType item : values()){
                if(item.toInt() == type){
                    return item;
                }
            }
            return ResourceType.NONE;
        }

    }

    public enum Shape{
        NONE(0), CIRCLE(1), ROUNDRECT(2);

        private int type;

        Shape(int type){
            this.type = type;
        }

        public int toInt(){
            return type;
        }

        public static Shape getShape(int type){
            for(Shape item : values()){
                if(item.toInt() == type){
                    return item;
                }
            }
            return Shape.NONE;
        }

    }

    private String mImageUrl;
    private int mImageRes = 0;
    private int mImageWidth;
    private int mImageHeight;
    private Paint mPaint = new Paint();
    private ResourceType mResourceType = ResourceType.NONE;
    private ScalingLogic mScalingLogic = ScalingLogic.CROP;
    private Shape mShape = Shape.NONE;
    private int mRadiusX;
    private int mRadiusY;

    public ShapeImageView(Context context) {
        super(context);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
    }

    public ShapeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initAttributes(attrs);
    }
    
    private void initAttributes(AttributeSet attrs){
		TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ShapedImage, 0, 0);

		try{
            mImageRes = typedArray.getResourceId(R.styleable.ShapedImage_image_res, mImageRes);
            mImageUrl = typedArray.getString(R.styleable.ShapedImage_image_url);
            mScalingLogic = ScalingLogic.getScalingLogic(typedArray.getInt(
                    R.styleable.ShapedImage_scaling_logic, 1));
            mResourceType = ResourceType.getResourceType(typedArray.getInt(
                    R.styleable.ShapedImage_resource_type, 0));
            mShape = Shape.getShape(typedArray.getInt(
                    R.styleable.ShapedImage_shape, 0));
            mRadiusX = typedArray.getInt(R.styleable.ShapedImage_radius_x, 0);
            mRadiusY = typedArray.getInt(R.styleable.ShapedImage_radius_y, 0);

        }finally{
			typedArray.recycle();
		}
		
		switch (mResourceType) {
		case PATH:
			setImagePath(mImageUrl);
			break;
		case RES_ID:
			setImageRes(mImageRes);
			break;
	    default:
	    	break;
		}
	}

    public ShapeImageView setImagePath(String path){
        this.mImageUrl = path;
        mResourceType = ResourceType.PATH;
        return this;
    }

    public ShapeImageView setImageRes(int resId){
        this.mImageRes = resId;
        mResourceType = ResourceType.RES_ID;
        return this;
    }

    public ShapeImageView setShape(Shape shape){
        this.mShape = shape;
        return this;
    }

    public ShapeImageView setRadius(int rx, int ry){
        mRadiusX = rx;
        mRadiusY = ry;
        return this;
    }

    public void setScalingLogic(ScalingLogic scalingLogic){
        this.mScalingLogic = scalingLogic;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        mImageWidth = getMeasuredWidth();
        mImageHeight = getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(mResourceType == ResourceType.NONE){
            super.onDraw(canvas);
            return;
        }

        Bitmap unscaledBitmap = null;
        if(mResourceType == ResourceType.PATH){
            unscaledBitmap = ImageUtils.decodeFile(mImageUrl, mImageWidth, mImageHeight, mScalingLogic);
        } else if(mResourceType == ResourceType.RES_ID){
            unscaledBitmap = ImageUtils.decodeFile(getContext(), mImageRes, mImageWidth, mImageHeight, mScalingLogic);
        }

        Bitmap scaledBitmap = ImageUtils.getShapedBitmap(unscaledBitmap, mImageWidth, mImageHeight,
                mScalingLogic, mShape, mRadiusX, mRadiusY);
        canvas.drawBitmap(scaledBitmap, 0, 0, mPaint);
    }

}

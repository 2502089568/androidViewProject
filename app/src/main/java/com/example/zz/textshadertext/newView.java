package com.example.zz.textshadertext;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;


/**
 * Created by zz on 2018/3/14.
 */

@SuppressLint("AppCompatCustomView")
public class newView extends TextView {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    private int mViewWidth;

    public newView(Context context,AttributeSet attrs) {
        super(context,attrs);
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh){
        super.onSizeChanged(w,h,oldw,oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0) {
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(
                        0,
                        0,
                        mViewWidth,
                        0,
                        new int[]{
                                Color.BLUE, 0xffffffff,
                                Color.BLUE},
                        null,
                        Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate+=mViewWidth/5;
            if(mTranslate>2*mViewWidth){
                mTranslate=-mViewWidth;
            }
        }
        mGradientMatrix.setTranslate(mTranslate,0);
        mLinearGradient.setLocalMatrix(mGradientMatrix);
        postInvalidateDelayed(100);
    }
}

package com.example.myanimationset.MyView;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/10/10
 * desc:
 */
public class CoffeeDrawable extends Drawable {

    private int mWidth; //总宽
    private int mHeight; //总高
    private Paint mPaint;

    public CoffeeDrawable(int width, int height) {
        mPaint = new Paint();
        initPaint();
        updateSize(width, height);
    }

    private void initPaint() {
        mPaint.setAntiAlias(true); //设置画笔抗锯齿
        mPaint.setStrokeCap(Paint.Cap.ROUND); //设置为圆形线冒
        mPaint.setStrokeJoin(Paint.Join.ROUND);
    }

    private void updateSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void setAlpha(int alpha) {
        mPaint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        mPaint.setColorFilter(colorFilter);
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}

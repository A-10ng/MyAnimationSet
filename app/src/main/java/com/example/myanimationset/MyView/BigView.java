package com.example.myanimationset.MyView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

import java.io.IOException;
import java.io.InputStream;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/21
 * desc:
 */
public class BigView extends View implements GestureDetector.OnGestureListener, View.OnTouchListener {


    private Rect mRect;
    private BitmapFactory.Options mOptions;
    private GestureDetector mGestureDetector;
    private Scroller mScroller;
    private int mImageWidth;//原图的宽度
    private int mImageHeight;//原图的高度
    private BitmapRegionDecoder mDecoder;
    private int mViewWidth;//该view的宽度
    private int mViewHeight;//该view的高度
    private float mScale;
    private Bitmap mBitmap;
    private Matrix matrix;
    private static final String TAG = "BigView";

    public BigView(Context context) {
        this(context, null);
    }

    public BigView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BigView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /**
         * 第一步：设置BigView所需要的成员变量
         */
        mRect = new Rect();
        //内存复用
        mOptions = new BitmapFactory.Options();
        //手势识别
        mGestureDetector = new GestureDetector(context, this);
        //滚动类
        mScroller = new Scroller(context);
        setOnTouchListener(this);
    }

    /**
     * 第二步：设置图片，得到图片的信息
     *
     * @param is
     */
    public void setImage(InputStream is) {
        //获取图片的宽高，但是没有将图片加载进内存
        mOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(is, null, mOptions);
        mImageWidth = mOptions.outWidth;
        mImageHeight = mOptions.outHeight;

        //开启复用
        mOptions.inMutable = true;
        //设置格式RGB565
        mOptions.inPreferredConfig = Bitmap.Config.RGB_565;

        mOptions.inJustDecodeBounds = false;
        //区域解码器
        try {
            mDecoder = BitmapRegionDecoder.newInstance(is, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestLayout();//会使onMeasure,onLayout和onDraw方法被调用，invalidate只会使onDraw方法被调用。
    }

    /**
     * 第三步，开始测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        //确定图片的加载区域
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mImageWidth;
        //得到图片加载的具体高度
        //根据图片的宽度，以及view的宽度
        mScale = mViewWidth / (float) mImageWidth;
        mRect.bottom = (int) (mViewHeight / mScale);
    }

    /**
     * 第四步，画出具体内容
     *
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDecoder == null) return;
        //内存复用(复用的bitmap必须跟即将解码的bitmap尺寸一致)
        mOptions.inBitmap = mBitmap;
        //指定解码区域
        mBitmap = mDecoder.decodeRegion(mRect, mOptions);

        //得到矩阵进行缩放，得到view的大小
        matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        canvas.drawBitmap(mBitmap, matrix, null);
    }

    /**
     * 第五步，处理点击事件
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        //直接将事件交给手势事件
        return mGestureDetector.onTouchEvent(event);
    }

    /**
     * 第六步，手按下去
     * @param e
     * @return
     */
    @Override
    public boolean onDown(MotionEvent e) {
        //如果移动没有停止，强行停止
        if (!mScroller.isFinished()){
            mScroller.forceFinished(true);
        }
        return true;
    }

    /**
     * 第七步，处理滑动事件
     * @param e1 开始事件，手指按下去，获取坐标
     * @param e2 获取当前事件坐标
     * @param distanceX X轴移动距离
     * @param distanceY y轴移动距离
     * @return
     */
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //上下移动时，mRect需要改变显示区域
        mRect.offset(0, (int) distanceY);
        //移动的时候
        if (mRect.bottom > mImageHeight){
            mRect.bottom = mImageHeight;
            mRect.top = mImageHeight - (int)(mViewHeight / mScale);
            Log.i(TAG, "onScroll---->bottom:"+mRect.bottom+" top:"+mRect.top);
        }
        if (mRect.top < 0){
            mRect.top = 0;
            mRect.bottom = (int) (mViewHeight / mScale);
        }
        invalidate();
        return false;
    }

    /**
     * 第八步，处理惯性事件
     * @param e1
     * @param e2
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        mScroller.fling(0,mRect.top,0,(int)-velocityY,0,0,0,mImageHeight - (int)(mViewHeight / mScale));
        return false;
    }

    /**
     * 第九步，处理计算结果
     */
    @Override
    public void computeScroll() {
        if (mScroller.isFinished()){
            return;
        }
        //为true，滑动还没有结束
        if (mScroller.computeScrollOffset()){
            mRect.top = mScroller.getCurrY();
            mRect.bottom =  mRect.top + (int)(mViewHeight / mScale);
            invalidate();
        }
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
}

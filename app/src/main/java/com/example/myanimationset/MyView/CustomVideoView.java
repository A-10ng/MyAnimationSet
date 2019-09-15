package com.example.myanimationset.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.VideoView;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/09/15
 * desc:
 */
public class CustomVideoView extends VideoView {

    private static final String TAG = "CustomVideoView";
    int defaultWidth = 1920;
    int defaultHeight = 1080;

    public CustomVideoView(Context context) {
        super(context);
    }

    public CustomVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getDefaultSize(defaultWidth,widthMeasureSpec);
        int height = getDefaultSize(defaultWidth,widthMeasureSpec);

        Log.i(TAG, "width:"+width+" height:"+height);
        setMeasuredDimension(width,height);
    }
}

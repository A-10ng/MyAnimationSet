package com.example.myanimationset.MyView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2020/03/24
 * desc:
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }

    //反射
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //主题style
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //测量孩子的大小
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            LayoutParams childLayoutParams = childView.getLayoutParams();
            int childWidthMeasureSpec = getChildMeasureSpec(widthMeasureSpec,
                    getPaddingLeft()+getPaddingRight(),
                    childLayoutParams.width);
            int childHeightMeasureSpec = getChildMeasureSpec(heightMeasureSpec,
                    getPaddingLeft()+getPaddingRight(),
                    childLayoutParams.height);
            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
        }

        //测量自己的大小
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}

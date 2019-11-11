package com.example.myanimationset.MyView.LaunchingViewPackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.myanimationset.R;
import com.example.myanimationset.Utils.Utils;

import android.os.Handler;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/10
 * desc:
 */
public class LaunchingView extends RelativeLayout {

    private int mHeight, mWidth;
    private int dp80 = Utils.dp2px(getContext(), 80);
    private ImageView red, purple, yellow, blue;
    private ViewPath redPath1, purplePath1, bluePath1, yellowPath1;
    private AnimatorSet redAll, purpleAll, yellowAll, blueAll;

    public LaunchingView(Context context) {
        super(context);
    }

    public LaunchingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LaunchingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        initPath();
    }

    private void initPath() {
        redPath1 = new ViewPath();//偏移坐标
        redPath1.moveTo(0, 0);
        redPath1.lineTo(mWidth / 5 - mWidth / 2, 0);
        redPath1.curveTo(-700, -mHeight / 2, mWidth / 3 * 2,
                -mHeight / 3 * 2, 0, -dp80);

        purplePath1 = new ViewPath();
        purplePath1.moveTo(0, 0);
        purplePath1.lineTo(mWidth / 5 * 2 - mWidth / 2, 0);
        purplePath1.curveTo(-300, -mHeight / 2, mWidth,
                -mHeight / 9 * 5, 0, -dp80);

        yellowPath1 = new ViewPath();
        yellowPath1.moveTo(0, 0);
        yellowPath1.lineTo(mWidth / 5 * 3 - mWidth / 2, 0);
        yellowPath1.curveTo(300, mHeight, -mWidth,
                -mHeight / 9 * 5, 0, -dp80);

        bluePath1 = new ViewPath();
        bluePath1.moveTo(0, 0);
        bluePath1.lineTo(mWidth / 5 * 4 - mWidth / 2, 0);
        bluePath1.curveTo(700, mHeight / 3 * 2, -mWidth / 2,
                mHeight / 2, 0, -dp80);
    }

    public void start() {
        removeAllViews();
        init();
        redAll.start();
        purpleAll.start();
        yellowAll.start();
        blueAll.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showLogo();
            }
        }, 2400);
    }

    private void init() {
        //设置子view的宽，高，布局和边距
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_HORIZONTAL, TRUE);//设置子View水平居中
        layoutParams.addRule(CENTER_VERTICAL, TRUE);//设置子view垂直居中
        layoutParams.setMargins(0, 0, 0, dp80);//设置子view距离父view底部80dp

        //添加四个imageview进去
        purple = new ImageView(getContext());
        purple.setLayoutParams(layoutParams);
        purple.setImageResource(R.drawable.shape_circle_purple);//紫色圆点图标
        addView(purple);

        yellow = new ImageView(getContext());
        yellow.setLayoutParams(layoutParams);
        yellow.setImageResource(R.drawable.shape_circle_yellow);
        addView(yellow);

        blue = new ImageView(getContext());
        blue.setLayoutParams(layoutParams);
        blue.setImageResource(R.drawable.shape_circle_blue);
        addView(blue);

        red = new ImageView(getContext());
        red.setLayoutParams(layoutParams);
        red.setImageResource(R.drawable.shape_circle_red);
        addView(red);

        //为四个imageview设置动画
        setAnimation(red, redPath1);
        setAnimation(blue, bluePath1);
        setAnimation(yellow, yellowPath1);
        setAnimation(purple, purplePath1);
    }

    private void showLogo() {
        View view = View.inflate(getContext(), R.layout.widget_laod_view, this);
        View logo = view.findViewById(R.id.iv_logo);
        final View slogo = view.findViewById(R.id.iv_slogo);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logo, View.ALPHA, 0f, 1f);
        alpha.setDuration(800);
        alpha.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ObjectAnimator alpha = ObjectAnimator.ofFloat(slogo, View.ALPHA, 0f, 1f);
                alpha.setDuration(200);
                alpha.start();
            }
        }, 400);
    }

    private void setAnimation(final ImageView targetIV, ViewPath path) {
        ObjectAnimator animator = ObjectAnimator.ofObject(new ViewObj(targetIV), "fabLoc", new ViewPathEvaluator(),
                path.getPoints().toArray());
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(2600);
        //组合添加缩放透明效果
        addAnimation(animator, targetIV);
    }

    private void addAnimation(ObjectAnimator animator, final ImageView targetIV) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, 1000);
        valueAnimator.setDuration(1800);
        valueAnimator.setStartDelay(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                float alpha = 1 - value / 2000;
                float scale = getScale(targetIV) - 1;
                if (value <= 500) {
                    scale = 1 + (value / 500) * scale;
                } else {
                    scale = 1 + ((1000 - value) / 500) * scale;
                }
                targetIV.setScaleX(scale);
                targetIV.setScaleY(scale);
                targetIV.setAlpha(alpha);
            }
        });
        valueAnimator.addListener(new AnimEndLsitener(targetIV));
        if (targetIV == red) {
            redAll = new AnimatorSet();
            redAll.playTogether(animator, valueAnimator);
        }
        if (targetIV == blue) {
            blueAll = new AnimatorSet();
            blueAll.playTogether(animator, valueAnimator);
        }
        if (targetIV == yellow) {
            yellowAll = new AnimatorSet();
            yellowAll.playTogether(animator, valueAnimator);
        }
        if (targetIV == purple) {
            purpleAll = new AnimatorSet();
            purpleAll.playTogether(animator, valueAnimator);
        }
    }

    private float getScale(ImageView targetIV) {
        if (targetIV == red) return 3f;
        if (targetIV == yellow) return 4.5f;
        if (targetIV == purple) return 2f;
        if (targetIV == blue) return 3.5f;
        return 2f;
    }

    private class AnimEndLsitener extends AnimatorListenerAdapter {
        private View targetView;

        public AnimEndLsitener(View targetView) {
            this.targetView = targetView;
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            super.onAnimationEnd(animation);
            removeView(targetView);
        }
    }

    public class ViewObj {
        private final ImageView imageView;

        public ViewObj(ImageView imageView) {
            this.imageView = imageView;
        }

        public void setFabLoc(ViewPoint newLoc) {
            imageView.setTranslationX(newLoc.x);
            imageView.setTranslationY(newLoc.y);
        }
    }
}

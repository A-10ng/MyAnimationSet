package com.example.myanimationset.MyView.LaunchingViewPackage;

import android.animation.TypeEvaluator;
import android.util.Log;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/10
 * desc:
 */
public class ViewPathEvaluator implements TypeEvaluator<ViewPoint> {

    public ViewPathEvaluator() {
    }

    @Override
    public ViewPoint evaluate(float fraction, ViewPoint startValue, ViewPoint endValue) {
        /** fraction: 表示线段的长度从0到1 */
        Log.i("ViewPathEvaluator", "evaluate: fraction>>>"+fraction);

        float x, y;
        float startX, startY;

        switch (endValue.operation) {
            case ViewPath.LINE:
                startX = (startValue.operation == ViewPath.QUAD)
                        ? startValue.x1
                        : startValue.x;
                startX = (startValue.operation == ViewPath.CURVE)
                        ? startValue.x2
                        : startValue.x;
                startY = (startValue.operation == ViewPath.QUAD)
                        ? startValue.y1
                        : startValue.y;
                startY = (startValue.operation == ViewPath.CURVE)
                        ? startValue.y2
                        : startValue.y;

                x = startX + fraction * (endValue.x - startX);
                y = startY + fraction * (endValue.y - startY);
                break;
            case ViewPath.MOVE:
                x = endValue.x;
                y = endValue.y;
                break;
            case ViewPath.QUAD:
                startX = (startValue.operation == ViewPath.CURVE)
                        ? startValue.x2
                        : startValue.x;
                startY = (startValue.operation == ViewPath.CURVE)
                        ? startValue.y2
                        : startValue.y;

                float NUM = 1 - fraction;
                x = NUM * NUM * startX +
                    2 * NUM * fraction * endValue.x +
                    fraction * fraction * endValue.x1;
                y = NUM * NUM * startY +
                        2 * NUM * fraction * endValue.y +
                        fraction * fraction * endValue.y1;
                break;
            case ViewPath.CURVE:
                startX = (startValue.operation == ViewPath.QUAD)
                        ? startValue.x1
                        : startValue.x;
                startY = (startValue.operation == ViewPath.QUAD)
                        ? startValue.y1
                        : startValue.y;

                float NUM1 = 1 - fraction;
                /**
                 * 三次贝塞尔曲线公式：
                 * x = (1 - t)³ * tº * x0 + 3 *（1 - t）² * t¹ * x1 + 3 *（1 - t）¹ * t² * x2 + t³ * x3
                 * y = (1 - t)³ * tº * y0 + 3 *（1 - t）² * t¹ * y1 + 3 *（1 - t）¹ * t² * y2 + t³ * y3
                 */
                x = NUM1 * NUM1 * NUM1 * startX +
                        3 * NUM1 * NUM1 * fraction * endValue.x +
                        3 * NUM1 * fraction * fraction * endValue.x1 +
                        fraction * fraction * fraction * endValue.x2;
                y = NUM1 * NUM1 * NUM1 * startY +
                        3 * NUM1 * NUM1 * fraction * endValue.y +
                        3 * NUM1 * fraction * fraction * endValue.y1 +
                        fraction * fraction * fraction * endValue.y2;
                break;
            default:
                x = endValue.x;
                y = endValue.y;
                break;
        }
        return new ViewPoint(x,y);
    }
}

package com.example.myanimationset.MyView.TreeView;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import java.util.LinkedList;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/25
 * desc:
 */
public class Branch {
    //颜色
    public static int branchColor = 0xff773322;
    //分枝
    LinkedList<Branch> childList;
    //形状（3个控制点）
    private PointF[] cp = new PointF[3];
    //粗细
    private float radius;
    //长度
    private int maxLength;//10
    private int currentLength;
    private float part;//一根树枝每一次绘制的长度
    private float growX,growY;

    public Branch(int[] data) {
        //id , parentId , bezier control points , radius , maxLength
        //{ 0, -1, 217, 490, 252, 60, 182, 10, 30, 100}
        cp[0] = new PointF(data[2], data[3]);
        cp[1] = new PointF(data[4], data[5]);
        cp[2] = new PointF(data[6], data[7]);
        radius = data[8];
        maxLength = data[9];
        part = 1f / maxLength;
    }

    //添加树枝
    public void addChild(Branch branch) {
        if (childList == null) {
            childList = new LinkedList<>();
        }
        childList.add(branch);
    }

    //判断当前树枝是否正在绘制
    public boolean isGrowing(Canvas canvas, Paint paint, int scaleFactor) {
        if (currentLength < maxLength) {
            //计算当前绘制点的位置
            bezier(part * currentLength);
            //绘制
            draw(canvas, paint, scaleFactor);
            currentLength++;
            radius *= 0.97;
            return true;
        } else {
            return false;
        }
    }

    private void bezier(float t) {
        float c0 = (1 - t) * (1 - t);
        float c1 = 2 * t * (1 - t);
        float c2 = t * t;
        growX = c0 * cp[0].x + c1 * cp[1].x + c2 * cp[2].x;
        growY = c0 * cp[0].y + c1 * cp[1].y + c2 * cp[2].y;
    }

    private void draw(Canvas canvas, Paint paint, int scaleFactor) {
        paint.setColor(branchColor);
        canvas.save();
        canvas.scale(scaleFactor,scaleFactor);
        canvas.drawCircle(growX,growY,radius,paint);
        canvas.restore();
    }
}

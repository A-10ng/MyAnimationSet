package com.example.myanimationset.MyView.LaunchingViewPackage;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/10
 * desc:
 */
public class ViewPoint {
    float x,y;//用于平移动画
    float x1,y1;//用于二次贝塞尔曲线
    float x2,y2;//用于三次贝塞尔曲线
    int operation;//标记位，用于识别是哪种操作

    public ViewPoint(float x,float y){
        this.x = x;
        this.y = y;
    }

    public ViewPoint(float x,float y,int operation){
        this.x = x;
        this.y = y;
        this.operation = operation;
    }

    public ViewPoint(float x,float y,float x1,float y1,int operation){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.operation = operation;
    }

    public ViewPoint(float x,float y,float x1,float y1,float x2,float y2,int operation){
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.operation = operation;
    }

    public static ViewPoint moveTo(float x,float y,int operation){
        return new ViewPoint(x,y,operation);
    }

    public static ViewPoint lineTo(float x,float y,int operation){
        return new ViewPoint(x,y,operation);
    }

    public static ViewPoint quadTo(float x,float y,float x1,float y1,int operation){
        return new ViewPoint(x,y,x1,y1,operation);
    }

    public static ViewPoint curveTo(float x,float y,float x1,float y1,float x2,float y2,int operation){
        return new ViewPoint(x,y,x1,y1,x2,y2,operation);
    }
}

package com.example.library;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc:
 */
public class RViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;//当前view控件的集合
    private View mConvertView;//当前的条目view

    private RViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mConvertView = itemView;
        mViews = new SparseArray<>();
    }

    //静态的初始化对象入口
    public static RViewHolder createViewHolder(Context context, ViewGroup parent,int layoutId){
        //动态载入
        View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
        return new RViewHolder(itemView);
    }

    public <T extends View> T getView(int viewId){
        //通过R.id.xxx的常量值作为key，寻找某view控件
        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            //键：R.id.xx 值：TextView
            mViews.put(viewId,view);
        }
        return (T) view;
    }

    //对外开放的API
    public View getmConvertView(){
        return mConvertView;
    }
}

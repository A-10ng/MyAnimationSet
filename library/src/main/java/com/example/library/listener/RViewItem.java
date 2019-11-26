package com.example.library.listener;

import com.example.library.RViewHolder;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc: 某一条目封装
 */
public interface RViewItem<T> {

    //layout布局
    int getItemLayout();

    //是否支持点击
    boolean clickable();

    //校验布局
    boolean isItemView(T entity,int position);

    //数据绑定
    void convert(RViewHolder holder, T entity, int position);
}

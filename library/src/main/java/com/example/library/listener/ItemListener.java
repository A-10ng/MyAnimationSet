package com.example.library.listener;

import android.view.View;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc: 条目点击
 */
public interface ItemListener<T> {

    void onItemClick(View view,T entity,int position);

    boolean onItemLongClick(View view,T entity,int position);
}

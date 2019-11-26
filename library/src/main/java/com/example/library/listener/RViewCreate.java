package com.example.library.listener;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.example.library.base.RViewAdapter;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/23
 * desc: 创建RViewHelper所需要的数据，它的实现类很方便创建RViewHelper对象
 */
public interface RViewCreate<T> {

    Context getContext();

    //创建SwipeRefresh下拉
    SwipeRefreshLayout createSwipeRefresh();

    //创建RecyclerView
    RecyclerView createRecyclerView();

    //创建创建RecyclerView.Adapter
    RViewAdapter<T> createRecyclerViewAdapter();

    //是否支持分页
    boolean isSupportPaging();
}

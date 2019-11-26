package com.example.library;

import android.support.v4.widget.SwipeRefreshLayout;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/23
 * desc: 下拉刷新的帮助类
 */
public class SwipeRefreshHelper {

    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshListener swipeRefreshListener;

    private SwipeRefreshHelper(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        init();
    }

    private void init() {
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_orange_dark,
                android.R.color.holo_green_dark,android.R.color.holo_blue_dark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeRefreshListener != null){
                    swipeRefreshListener.onRefresh();
                }
            }
        });
    }

    static SwipeRefreshHelper createSwipeRefreshHelper(SwipeRefreshLayout swipeRefreshLayout) {
        return new SwipeRefreshHelper(swipeRefreshLayout);
    }

    void setSwipeRefreshListener(SwipeRefreshListener swipeRefreshListener) {
        this.swipeRefreshListener = swipeRefreshListener;
    }

    public interface SwipeRefreshListener {
        void onRefresh();
    }
}

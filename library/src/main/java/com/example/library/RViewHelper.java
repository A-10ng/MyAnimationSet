package com.example.library;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.library.base.RViewAdapter;
import com.example.library.listener.RViewCreate;

import java.util.List;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc: 辅助类（和开发者的桥梁）
 */
public class RViewHelper<T> {

    private static final String TAG = "RViewHelper";
    private Context context;//上下文
    private SwipeRefreshLayout swipeRefreshLayout;      //下拉控件
    private SwipeRefreshHelper swipeRefreshHelper;      //下拉刷新的工具类
    private RecyclerView recyclerView;      //RecyclerView
    private RViewAdapter<T> adapter;        //适配器
    private int startPageNumber = 1;        //开始页码
    private boolean isSupportPaging;        //是否支持加载更多
    private SwipeRefreshHelper.SwipeRefreshListener listener;       //下拉刷新、加载更多监听
    private int currentPageNum;     //当前页数

    private RViewHelper(Builder<T> builder) {
        this.swipeRefreshLayout = builder.create.createSwipeRefresh();
        this.recyclerView = builder.create.createRecyclerView();
        this.adapter = builder.create.createRecyclerViewAdapter();
        this.context = builder.create.getContext();
        this.isSupportPaging = builder.create.isSupportPaging();
        this.listener = builder.listener;

        this.currentPageNum = this.startPageNumber;
        if (swipeRefreshLayout != null){
            swipeRefreshHelper = SwipeRefreshHelper.createSwipeRefreshHelper(swipeRefreshLayout);
        }

        init();
    }

    private void init() {
        //RV的初始化
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //下拉刷新的处理
        if (swipeRefreshHelper != null){
            swipeRefreshHelper.setSwipeRefreshListener(new SwipeRefreshHelper.SwipeRefreshListener() {
                @Override
                public void onRefresh() {
                    //当前正在刷新
                    if (swipeRefreshLayout != null && swipeRefreshLayout.isRefreshing()){
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    if (listener != null){
                        listener.onRefresh();
                    }
                }
            });
        }
    }

    public void notifyAdapterDataSetChanged(List<T> datas){
        //首次加载或者下拉刷新后都要重置密码
        if (currentPageNum == startPageNumber){
            adapter.updateDatas(datas);
        }else {
            adapter.addDatas(datas);
        }

        recyclerView.setAdapter(adapter);

        //省略功能
        if (isSupportPaging){
            Log.i(TAG, "更多功能待补充...");
        }
    }

    //构建者方式
    public static class Builder<T>{

        private RViewCreate<T> create; //初始化接口，由开发者实现
        private SwipeRefreshHelper.SwipeRefreshListener listener; // 下拉刷新监听

        public Builder(RViewCreate<T> create, SwipeRefreshHelper.SwipeRefreshListener listener){
            this.create = create;
            this.listener = listener;
        }

        public RViewHelper build(){
            return new RViewHelper<>(this);
        }
    }
}

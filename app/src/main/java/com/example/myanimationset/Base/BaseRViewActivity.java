package com.example.myanimationset.Base;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.library.RViewHelper;
import com.example.library.SwipeRefreshHelper;
import com.example.library.listener.RViewCreate;
import com.example.myanimationset.R;

import java.util.List;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/23
 * desc:
 */
public abstract class BaseRViewActivity extends AppCompatActivity implements RViewCreate, SwipeRefreshHelper.SwipeRefreshListener {

    protected RViewHelper helper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        helper = new RViewHelper.Builder(this,this).build();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean isSupportPaging() {
        return false;
    }

    @Override
    public SwipeRefreshLayout createSwipeRefresh() {
        return findViewById(R.id.swipeRefreshLayout);
    }

    @Override
    public RecyclerView createRecyclerView() {
        return findViewById(R.id.recyclerView);
    }

    protected void notifyAdapterDataSetChanged(List datas){
        helper.notifyAdapterDataSetChanged(datas);
    }
}

package com.example.myanimationset.ViewHolder.ExpandableViewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myanimationset.R;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2020/03/30
 * desc:
 */
public class DescHolder extends RecyclerView.ViewHolder {
    public TextView descView;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        descView = (TextView) itemView.findViewById(R.id.tv_desc);
    }
}

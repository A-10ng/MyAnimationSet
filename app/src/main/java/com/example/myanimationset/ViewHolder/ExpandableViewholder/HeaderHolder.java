package com.example.myanimationset.ViewHolder.ExpandableViewholder;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myanimationset.R;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2020/03/30
 * desc:
 */
public class HeaderHolder extends RecyclerView.ViewHolder {
    public TextView titleView;
    public TextView openView;
    public HeaderHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        titleView = (TextView) itemView.findViewById(R.id.tv_title);
        openView = (TextView) itemView.findViewById(R.id.tv_open);
    }
}

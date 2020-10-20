package com.example.library.manager;


import androidx.collection.SparseArrayCompat;

import com.example.library.RViewHolder;
import com.example.library.listener.RViewItem;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc:
 */
public class RViewItemManager<T>{

    //key：viewType      value：RViewItem
    private SparseArrayCompat<RViewItem<T>> styles = new SparseArrayCompat<>();

    public void addStyles(RViewItem<T> item) {
        if (item != null){
            //第一次（第一种布局，key:0）
            styles.put(styles.size(),item);
        }
    }

    public int getItemViewStylesCount() {
        return styles.size();
    }

    //根据数据源和位置返回某item类型的viewType，即为key
    public int getItemViewType(T entity, int position) {
        for (int i = styles.size() - 1; i >= 0; i--){ //倒序，避免remove的时候抛出越界异常问题
            RViewItem<T> item = styles.valueAt(i);
            //是否为当前样式，显示（由开发者实现）
            if (item.isItemView(entity,position)){
                return styles.keyAt(i);
            }
        }

        throw new NullPointerException("getItemViewType中不存在这样的item类型");
    }

    //根据key拿value，根据显示的viewType返回对应的RViewItem条目对象
    public RViewItem getRViewItem(int viewType) {
        return styles.get(viewType);
    }

    //视图和数据源的绑定
    public void convert(RViewHolder rViewHolder, T entity, int position) {
        for (int i = 0; i < styles.size(); i++) {
            RViewItem<T> item = styles.valueAt(i);

            if (item.isItemView(entity,position)){
                item.convert(rViewHolder,entity,position);
                return;
            }
        }

        throw new NullPointerException("convert中不存在这样的item类型");
    }
}

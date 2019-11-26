package com.example.library.base;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.RViewHolder;
import com.example.library.listener.ItemListener;
import com.example.library.listener.RViewItem;
import com.example.library.manager.RViewItemManager;

import java.util.ArrayList;
import java.util.List;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/22
 * desc:
 */
public class RViewAdapter<T> extends RecyclerView.Adapter<RViewHolder> {

    private ItemListener<T> itemListener;//条目点击监听
    private List<T> datas;//数据源
    private RViewItemManager<T> itemStyle;//item类型管理

    //初始化BaseAdapter的时候，可以加入单一布局，也可以加入多布局
    public RViewAdapter(List<T> datas, RViewItem<T> item){
        if (datas == null) this.datas = new ArrayList<>();
        this.datas = datas;
        itemStyle = new RViewItemManager<>();
        if (item == null) return;

        //将item类型加入
        addItemStyles(item);
    }

    private void addItemStyles(RViewItem<T> item) {
        itemStyle.addStyles(item);
    }

    //是否有多样式布局
    private boolean hasMultiStyle(){
        return itemStyle.getItemViewStylesCount() > 1;
    }

    //根据position获取当前Item的布局类型
    @Override
    public int getItemViewType(int position) {

        if (hasMultiStyle()) return itemStyle.getItemViewType(datas.get(position),position);

        return super.getItemViewType(position);
    }

    //根据布局类型viewType的不同创建ViewHolder对象
    @Override
    public RViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        RViewItem item = itemStyle.getRViewItem(viewType);

        int layoutId = item.getItemLayout();

        RViewHolder holder = RViewHolder.createViewHolder(viewGroup.getContext(),viewGroup,layoutId);

        //点击监听
        if (item.clickable()) setListener(holder);

        return holder;
    }

    private void setListener(final RViewHolder holder) {

        holder.getmConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemListener != null){
                    int position = holder.getAdapterPosition();
                    itemListener.onItemClick(v,datas.get(position),position);
                }
            }
        });

        holder.getmConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemListener != null){
                    int position = holder.getAdapterPosition();
                    return itemListener.onItemLongClick(v,datas.get(position),position);
                }
                return false;
            }
        });
    }

    //将数据绑定到item的ViewHolder控件
    @Override
    public void onBindViewHolder(@NonNull RViewHolder rViewHolder, int position) {
        convert(rViewHolder,datas.get(position));
    }

    private void convert(RViewHolder rViewHolder, T entity) {
        itemStyle.convert(rViewHolder,entity,rViewHolder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        return datas.size() == 0 ? 0 : datas.size();
    }

    public void setItemListener(ItemListener<T> itemListener) {
        this.itemListener = itemListener;
    }

    //修改整个数据集合
    public void updateDatas(List<T> datas) {
        if (datas == null) return;
        this.datas = datas;
        notifyDataSetChanged();
    }

    //添加数据集合
    public void addDatas(List<T> datas) {
        if (datas == null) return;
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }
}
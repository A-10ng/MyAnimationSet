package com.example.myanimationset.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myanimationset.Bean.TestItem;
import com.example.myanimationset.R;

import java.util.List;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/10/17
 * desc:
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<TestItem> recordList;

    public MyRecyclerViewAdapter(List<TestItem> recordList){
        this.recordList = recordList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_recyclerview_animation,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        TestItem item = recordList.get(i);
        ((ViewHolder)viewHolder).imageView.setImageResource(item.getPicture());
        ((ViewHolder)viewHolder).tv_title.setText(item.getTitle());
        ((ViewHolder)viewHolder).tv_content.setText(item.getContent());
        ((ViewHolder)viewHolder).tv_value.setText(item.getValue());
    }

    @Override
    public int getItemCount() {
        return recordList == null ? 0 : recordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView tv_title;
        TextView tv_content;
        TextView tv_value;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_value = itemView.findViewById(R.id.tv_value);
        }
    }
}

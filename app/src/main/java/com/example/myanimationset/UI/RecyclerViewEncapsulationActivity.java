package com.example.myanimationset.UI;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.RViewHolder;
import com.example.library.base.RViewAdapter;
import com.example.library.listener.ItemListener;
import com.example.library.listener.RViewItem;
import com.example.myanimationset.Base.BaseRViewActivity;
import com.example.myanimationset.Bean.UserInfo;
import com.example.myanimationset.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewEncapsulationActivity extends BaseRViewActivity {

    private List<UserInfo> datas = new ArrayList<>();
    private RViewAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = this;
        initDatas();
        setListener();
    }

    private void initDatas() {
        if (datas.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                datas.add(new UserInfo("LongSh1z "+(i+1), ": 123456"));
            }
        }
        notifyAdapterDataSetChanged(datas);
    }

    private void setListener() {
        adapter.setItemListener(new ItemListener<UserInfo>() {
            @Override
            public void onItemClick(View view, UserInfo entity, int position) {
                Toast.makeText(context, "onItemClick Position >>> " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onItemLongClick(View view, UserInfo entity, int position) {
                Toast.makeText(context, "onItemLongClick Position >>> " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onRefresh() {
        initDatas();
    }

    @Override
    public RViewAdapter createRecyclerViewAdapter() {
        adapter = new RViewAdapter<>(datas, new RViewItem<UserInfo>() {
            @Override
            public int getItemLayout() {
                return R.layout.item_list;
            }

            @Override
            public boolean clickable() {
                return true;
            }

            @Override
            public boolean isItemView(UserInfo entity, int position) {
                //如果是单一布局
                return true;
            }

            @Override
            public void convert(RViewHolder holder, UserInfo entity, int position) {
                TextView tv = holder.getView(R.id.item_tv);
                tv.setText(entity.toString());
            }
        });
        return adapter;
    }
}

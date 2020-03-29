package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.myanimationset.Adapter.ExpandableAdapter.ExpandableRecyclerviewAdapter;
import com.example.myanimationset.Adapter.ExpandableAdapter.SectionedSpanSizeLookup;
import com.example.myanimationset.Bean.HotelEntity;
import com.example.myanimationset.R;
import com.example.myanimationset.Utils.JsonUtils;

public class ExpandableRecyclerviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ExpandableRecyclerviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_recyclerview);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new ExpandableRecyclerviewAdapter(this);
        GridLayoutManager manager = new GridLayoutManager(this,4);

        //设置header
        manager.setSpanSizeLookup(new SectionedSpanSizeLookup(adapter,manager));
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        HotelEntity entity = JsonUtils.analysisJsonFile(this,"data");
        adapter.setData(entity.allTagsList);
    }
}

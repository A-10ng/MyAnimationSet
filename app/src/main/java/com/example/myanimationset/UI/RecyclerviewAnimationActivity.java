package com.example.myanimationset.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myanimationset.Adapter.MyRecyclerViewAdapter;
import com.example.myanimationset.Bean.TestItem;
import com.example.myanimationset.MyView.MyRecyclerView;
import com.example.myanimationset.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerviewAnimationActivity extends AppCompatActivity {

    private List<TestItem> list;
    private MyRecyclerView recyclerView;
    private TestItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_animation);

        init();

        initRecyclerView();
    }

    private void init() {
        recyclerView = findViewById(R.id.recyclerView);

        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            item = new TestItem();
            item.setPicture(R.mipmap.error);
            item.setTitle("This is LongSh1z's Title!");
            item.setContent("I am content index：");
            item.setValue(i+"");
            list.add(item);
        }
    }

    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(new MyRecyclerViewAdapter(list));
    }
}

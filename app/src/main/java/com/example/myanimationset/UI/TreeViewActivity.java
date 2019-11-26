package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.myanimationset.MyView.TreeView.TreeView;
import com.example.myanimationset.R;

public class TreeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new TreeView(this));
    }
}

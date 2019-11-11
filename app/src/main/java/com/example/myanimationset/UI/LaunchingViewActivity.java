package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myanimationset.MyView.LaunchingViewPackage.LaunchingView;
import com.example.myanimationset.R;

public class LaunchingViewActivity extends AppCompatActivity {

    private LaunchingView launchingView;
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching_view);

        launchingView = findViewById(R.id.launchingView);
        btn_start = findViewById(R.id.btn_start);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchingView.start();
            }
        });
    }
}

package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myanimationset.MyView.SimpleLoadingBar;
import com.example.myanimationset.R;

import java.util.Timer;
import java.util.TimerTask;

public class SimpleLoadingBarActivity extends AppCompatActivity {

    private static final String TAG = "SimpleLoadingBarActivit";
    private Button btn_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_loading_bar);

        btn_start = findViewById(R.id.btn_start);

        final SimpleLoadingBar simpleLoadingBar = new SimpleLoadingBar(this, R.style.hintDialog);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                simpleLoadingBar.show();

                //设置两秒之后取消simpleLoadingBar
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        simpleLoadingBar.cancel();
                    }
                };
                timer.schedule(timerTask,2000);
            }
        });
    }
}

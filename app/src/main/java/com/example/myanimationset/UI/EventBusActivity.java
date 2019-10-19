package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myanimationset.Events.MyMessage;
import com.example.myanimationset.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class EventBusActivity extends AppCompatActivity {

    private Button btn_increaseNumber;
    private TextView tv_number;
    private int num = 1;
    private View view;
    private static final String TAG = "EventBusActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        initViews();

        btn_increaseNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                EventBus.getDefault().post(new MyMessage("当前数量为"+ num));
            }
        });
    }

    private void initViews() {
        btn_increaseNumber = findViewById(R.id.btn_increaseNumber);
        tv_number = findViewById(R.id.tv_number);
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void increaseNumber(MyMessage message){
        tv_number.setText(message.getName());
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

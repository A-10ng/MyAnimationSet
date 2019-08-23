package com.example.myanimationset.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.example.myanimationset.R;

import static com.example.myanimationset.utils.Utils.dp2px;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_ratingBar;
    private Button btn_acceleratingBall;
    private Button btn_redCarp;
    private Button btn_eventBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViews();

        btn_ratingBar.setOnClickListener(this);
        btn_acceleratingBall.setOnClickListener(this);
        btn_redCarp.setOnClickListener(this);
        btn_eventBus.setOnClickListener(this);
    }

    private void findAllViews() {
        btn_ratingBar = findViewById(R.id.btn_ratingBar);
        btn_acceleratingBall = findViewById(R.id.btn_acceleratingBall);
        btn_redCarp = findViewById(R.id.btn_redCarp);
        btn_eventBus = findViewById(R.id.btn_eventBus);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ratingBar:
                startActivity(new Intent(this, RatingBarActivity.class));
                break;
            case R.id.btn_acceleratingBall:
                startActivity(new Intent(this,AcceleratingBallActivity.class));
                break;
            case R.id.btn_redCarp:
                startActivity(new Intent(this,RedCarpActivity.class));
                break;
            case R.id.btn_eventBus:
                startActivity(new Intent(this,EventBusActivity.class));
                break;
        }
    }
}

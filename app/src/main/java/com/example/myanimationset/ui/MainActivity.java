package com.example.myanimationset.ui;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RatingBar;

import com.example.myanimationset.R;

import static com.example.myanimationset.utils.Utils.dp2px;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_ratingBar;
    private Button btn_acceleratingBall;
    private Button btn_redCarp;
    private Button btn_eventBus;
    private Button btn_hintDialog;
    private Button btn_simpleLoadingBar;
    private Button btn_staticFragment;
    private Button btn_videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findAllViews();

        btn_ratingBar.setOnClickListener(this);
        btn_acceleratingBall.setOnClickListener(this);
        btn_redCarp.setOnClickListener(this);
        btn_eventBus.setOnClickListener(this);
        btn_hintDialog.setOnClickListener(this);
        btn_simpleLoadingBar.setOnClickListener(this);
        btn_staticFragment.setOnClickListener(this);
        btn_videoPlayer.setOnClickListener(this);
    }

    private void findAllViews() {
        btn_ratingBar = findViewById(R.id.btn_ratingBar);
        btn_acceleratingBall = findViewById(R.id.btn_acceleratingBall);
        btn_redCarp = findViewById(R.id.btn_redCarp);
        btn_eventBus = findViewById(R.id.btn_eventBus);
        btn_hintDialog = findViewById(R.id.btn_hintDialog);
        btn_simpleLoadingBar = findViewById(R.id.btn_simpleLoadingBar);
        btn_staticFragment = findViewById(R.id.btn_staticFragment);
        btn_videoPlayer = findViewById(R.id.btn_videoPlayer);
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
            case R.id.btn_hintDialog:
                startActivity(new Intent(this,HintDialogActivity.class));
                break;
            case R.id.btn_simpleLoadingBar:
                startActivity(new Intent(this,SimpleLoadingBarActivity.class));
                break;
            case R.id.btn_staticFragment:
                startActivity(new Intent(this,FragmentTestActivity.class));
                break;
            case R.id.btn_videoPlayer:
                startActivity(new Intent(this,VideoPlayerActivity.class));
                break;
        }
    }
}

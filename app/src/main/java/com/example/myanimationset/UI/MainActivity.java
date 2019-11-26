package com.example.myanimationset.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myanimationset.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_ratingBar;
    private Button btn_acceleratingBall;
    private Button btn_redCarp;
    private Button btn_eventBus;
    private Button btn_hintDialog;
    private Button btn_simpleLoadingBar;
    private Button btn_staticFragment;
    private Button btn_videoPlayer;
    private Button btn_recyclerviewAnimation;
    private Button btn_launchingView;
    private Button btn_bigPicture;
    private Button btn_recyclerViewEncapsulation;
    private Button btn_treeView;
    private Button btn_workFlow;

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
        btn_recyclerviewAnimation.setOnClickListener(this);
        btn_launchingView.setOnClickListener(this);
        btn_bigPicture.setOnClickListener(this);
        btn_recyclerViewEncapsulation.setOnClickListener(this);
        btn_treeView.setOnClickListener(this);
        btn_workFlow.setOnClickListener(this);
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
        btn_recyclerviewAnimation = findViewById(R.id.btn_recyclerviewAnimation);
        btn_launchingView = findViewById(R.id.btn_launchingView);
        btn_bigPicture = findViewById(R.id.btn_bigPicture);
        btn_recyclerViewEncapsulation = findViewById(R.id.btn_recyclerViewEncapsulation);
        btn_treeView = findViewById(R.id.btn_treeView);
        btn_workFlow = findViewById(R.id.btn_workFlow);
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
            case R.id.btn_recyclerviewAnimation:
                startActivity(new Intent(MainActivity.this,RecyclerviewAnimationActivity.class));
                break;
            case R.id.btn_launchingView:
                startActivity(new Intent(MainActivity.this,LaunchingViewActivity.class));
                break;
            case R.id.btn_bigPicture:
                startActivity(new Intent(MainActivity.this,BigPictureActivity.class));
                break;
            case R.id.btn_recyclerViewEncapsulation:
                startActivity(new Intent(MainActivity.this,RecyclerViewEncapsulationActivity.class));
                break;
            case R.id.btn_treeView:
                startActivity(new Intent(MainActivity.this,TreeViewActivity.class));
                break;
            case R.id.btn_workFlow:
                startActivity(new Intent(MainActivity.this,WorkFlowActivity.class));
                break;
        }
    }
}

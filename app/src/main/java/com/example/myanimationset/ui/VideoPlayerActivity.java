package com.example.myanimationset.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myanimationset.R;

public class VideoPlayerActivity extends AppCompatActivity {

    private VideoView videoView;
    private static final String TAG = "VideoPlayerActivity";
    private LinearLayout controllerLayout;
    private ImageView play_controller_img,screen_img;
    private TextView time_current_tv,time_total_tv;
    private SeekBar play_seek,volumn_seek;
    private static final int UPDATE_UI = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        initUI();
        setPlayerEvent();

        //判断有无读取内存卡的权限
        if (ContextCompat.checkSelfPermission(VideoPlayerActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(VideoPlayerActivity.this,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else {
            playVideo();
        }
    }

    private void updateTextViewWithTimeFormat(TextView textView,int millisecond){
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;

        String str = null;

        //如果视频总时间超过一小时
        if (hh != 0){
            str = String.format("%02d:%02d:%02d",hh,mm,ss);
        }
        else {
            str = String.format("%02d:%02d",mm,ss);
        }

        textView.setText(str);
    }

    private Handler UIHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UPDATE_UI){
                //获取视频当前的播放时间
                int currentPosition = videoView.getCurrentPosition();
                //获取视频播放的总时间
                int totalDuration = videoView.getDuration();

                //格式化视频播放时间
                updateTextViewWithTimeFormat(time_current_tv,currentPosition);
                updateTextViewWithTimeFormat(time_total_tv,totalDuration);

                play_seek.setMax(totalDuration);
                play_seek.setProgress(currentPosition);

                UIHandler.sendEmptyMessageDelayed(UPDATE_UI,500);
            }
        }
    };

    //控制视频的暂停和播放
    private void setPlayerEvent() {
        play_controller_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    play_controller_img.setImageResource(R.drawable.play_btn_style);

                    //暂停播放
                    videoView.pause();
                    UIHandler.removeMessages(UPDATE_UI);
                }
                else {
                    play_controller_img.setImageResource(R.drawable.pause_btn_style);

                    //继续播放
                    videoView.start();
                    UIHandler.sendEmptyMessage(UPDATE_UI);
                }
            }
        });

        play_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTextViewWithTimeFormat(time_current_tv,progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                UIHandler.removeMessages(UPDATE_UI);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = seekBar.getProgress();
                videoView.seekTo(progress);
                UIHandler.sendEmptyMessage(UPDATE_UI);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        UIHandler.removeMessages(UPDATE_UI);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UIHandler.removeMessages(UPDATE_UI);
    }

    private void initUI() {
        videoView = findViewById(R.id.videoView);
        controllerLayout = findViewById(R.id.controllerbar_layout);
        play_controller_img = findViewById(R.id.pause_iv);
        screen_img = findViewById(R.id.screen_img);
        time_current_tv = findViewById(R.id.time_current_tv);
        time_total_tv = findViewById(R.id.time_total_tv);
        play_seek = findViewById(R.id.play_seek);
        volumn_seek = findViewById(R.id.volumn_seek);
    }

    private void playVideo() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/teach.mp4";

        //本地视频播放
        //videoView.setVideoPath(path);

        //网络视频播放
        videoView.setVideoURI(Uri.parse("http://www.longsh1z.top/resources/teach.mp4"));

        videoView.start();
        UIHandler.sendEmptyMessage(UPDATE_UI);

//        //使用mediacontroller控制视频的播放
//        MediaController controller = new MediaController(this);
//
//        //建立关联
//        videoView.setMediaController(controller);
//        controller.setMediaPlayer(videoView);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.i(TAG, "允许了该权限");
                    playVideo();
                }else {
                    Log.i(TAG, "拒绝了该权限");
                }
        }
    }
}

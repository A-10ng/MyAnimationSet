package com.example.myanimationset.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.media.AudioManager;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.myanimationset.MyView.CustomVideoView;
import com.example.myanimationset.R;
import com.example.myanimationset.utils.Utils;

public class VideoPlayerActivity extends AppCompatActivity {

    private static final String TAG = "VideoPlayerActivity";
    private static final int UPDATE_UI = 1;
    private CustomVideoView videoView;
    private LinearLayout controllerLayout;
    private ImageView play_controller_img, screen_img, volume_img;
    private TextView time_current_tv, time_total_tv;
    private SeekBar play_seek, volumn_seek;
    private RelativeLayout videoLayout;
    private AudioManager mAudioManager;
    private boolean isFullScreen = false;
    private boolean isAdjust = false;
    private int threshold = 54;
    private float lastX = 0,lastY = 0;
    private float mBrightness;
    private ImageView operation_bg,operation_percent;
    private RelativeLayout progress_layout;


    private int screen_width, screen_height;
    private Handler UIHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UPDATE_UI) {
                //获取视频当前的播放时间
                int currentPosition = videoView.getCurrentPosition();
                //获取视频播放的总时间
                int totalDuration = videoView.getDuration();

                //格式化视频播放时间
                updateTextViewWithTimeFormat(time_current_tv, currentPosition);
                updateTextViewWithTimeFormat(time_total_tv, totalDuration);

                play_seek.setMax(totalDuration);
                play_seek.setProgress(currentPosition);

                UIHandler.sendEmptyMessageDelayed(UPDATE_UI, 500);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        initUI();

        setPlayerEvent();

        //判断有无读取内存卡的权限
        if (ContextCompat.checkSelfPermission(VideoPlayerActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(VideoPlayerActivity.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        } else {
            playVideo();
        }
    }

    //监听屏幕方向的改变
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //当屏幕方向为横屏时
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setVideoViewScale(screen_height, screen_width);

            screen_img.setImageResource(R.drawable.screen_min);

            //显示音量图标和音量大小
            volume_img.setVisibility(View.VISIBLE);
            volumn_seek.setVisibility(View.VISIBLE);

            isFullScreen = true;

            //移除半屏状态
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            //设置全屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        //当屏幕方向为竖屏时
        else {
            setVideoViewScale(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dp2px(this, 240));

            screen_img.setImageResource(R.drawable.screen);

            //隐藏音量图标和音量大小
            volume_img.setVisibility(View.GONE);
            volumn_seek.setVisibility(View.GONE);

            isFullScreen = false;
            //移除全屏状态
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            //设置半屏状态
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        }
    }

    private void setVideoViewScale(int width, int height) {
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        videoView.setLayoutParams(layoutParams);

        ViewGroup.LayoutParams layoutParams1 = videoLayout.getLayoutParams();
        layoutParams1.width = width;
        layoutParams1.height = height;
        videoLayout.setLayoutParams(layoutParams1);
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
        videoLayout = findViewById(R.id.videoLayout);
        volume_img = findViewById(R.id.volume_img);
        operation_bg = findViewById(R.id.operation_bg);
        operation_percent = findViewById(R.id.operation_percent);
        progress_layout = findViewById(R.id.progress_layout);

        screen_width = getResources().getDisplayMetrics().widthPixels;
        screen_height = getResources().getDisplayMetrics().heightPixels;

        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //获取设备的最大音量
        int streamMaxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //获取设备当前的音量
        int streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumn_seek.setMax(streamMaxVolume);
        volumn_seek.setProgress(streamVolume);
    }

    //控制视频的暂停和播放
    private void setPlayerEvent() {
        play_controller_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    play_controller_img.setImageResource(R.drawable.play_btn_style);

                    //暂停播放
                    videoView.pause();
                    UIHandler.removeMessages(UPDATE_UI);
                } else {
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
                updateTextViewWithTimeFormat(time_current_tv, progress);
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

        volumn_seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        screen_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFullScreen){
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                }
            }
        });

        //控制videoview的手势事件
        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x = event.getX();
                float y = event.getY();

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        lastX = x;
                        lastY = y;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        float detlaX = x - lastX;
                        float detlaY = y - lastY;
                        float absdetlaX = Math.abs(detlaX);
                        float absdetlaY = Math.abs(detlaY);
                        if (absdetlaX > threshold && absdetlaY > threshold){
                            if (absdetlaX < absdetlaY){
                                isAdjust = true;
                            }else {
                                isAdjust = false;
                            }
                        }else if (absdetlaX < threshold && absdetlaY > threshold){
                            isAdjust = true;
                        }else if (absdetlaX > threshold && absdetlaY < threshold){
                            isAdjust = false;
                        }

                        Log.i(TAG, "手势是否合法："+isAdjust);
                        if (isAdjust){
                            //如果触摸点在左边，即为调节亮度
                            if (x < screen_width/2){
                                //如果是向下滑，则降低亮度
                                if (detlaY > 0){
                                    Log.i(TAG, "降低亮度："+detlaY);
                                }else {
                                    Log.i(TAG, "提高亮度："+detlaY);
                                }
                                changeBrightness(-detlaY);
                            }
                            //否则为调节音量
                            else {
                                //如果是向下滑，则降低音量
                                if (detlaY > 0){
                                    Log.i(TAG, "降低音量："+detlaY);
                                }else {
                                    Log.i(TAG, "提高音量："+detlaY);
                                }
                                changeVolume(-detlaY);
                            }
                        }

                        lastX = x;
                        lastY = y;

                        break;
                    case MotionEvent.ACTION_UP:
                        progress_layout.setVisibility(View.GONE);
                        break;
                }

                return true;
            }
        });

    }

    private void changeVolume(float detlaY){
        int max = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int current = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int index = (int)(detlaY / screen_height * max * 3);
        int volume = Math.max(current+index,0);
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volume,0);

        operation_bg.setImageResource(R.drawable.video_volumn_bg);

        if (progress_layout.getVisibility() == View.GONE){
            progress_layout.setVisibility(View.VISIBLE);
        }

        ViewGroup.LayoutParams layoutParams = operation_percent.getLayoutParams();
        layoutParams.width = (int) (Utils.dp2px(this,94)*(float)volume/max);
        operation_percent.setLayoutParams(layoutParams);

        volumn_seek.setProgress(volume);
    }

    private void changeBrightness(float detlaY){
        //拿到屏幕当前的亮度
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        mBrightness = attributes.screenBrightness;

        //计算变化值
        float index = detlaY / screen_height;

        //设置亮度
        if (mBrightness > 1.0f){
            mBrightness = 1.0f;
        }
        if (mBrightness < 0.0f){
            mBrightness = 0.0f;
        }
        mBrightness += index;
        attributes.screenBrightness = mBrightness;

        operation_bg.setImageResource(R.drawable.video_brightness_bg);

        if (progress_layout.getVisibility() == View.GONE){
            progress_layout.setVisibility(View.VISIBLE);
        }

        ViewGroup.LayoutParams layoutParams = operation_percent.getLayoutParams();
        layoutParams.width = (int) (Utils.dp2px(this,94)*mBrightness);
        operation_percent.setLayoutParams(layoutParams);

        getWindow().setAttributes(attributes);
    }

    private void playVideo() {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/teach.mp4";

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

    private void updateTextViewWithTimeFormat(TextView textView, int millisecond) {
        int second = millisecond / 1000;
        int hh = second / 3600;
        int mm = second % 3600 / 60;
        int ss = second % 60;

        String str = null;

        //如果视频总时间超过一小时
        if (hh != 0) {
            str = String.format("%02d:%02d:%02d", hh, mm, ss);
        } else {
            str = String.format("%02d:%02d", mm, ss);
        }

        textView.setText(str);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UIHandler.removeMessages(UPDATE_UI);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "允许了该权限");
                    playVideo();
                } else {
                    Log.i(TAG, "拒绝了该权限");
                }
        }
    }
}

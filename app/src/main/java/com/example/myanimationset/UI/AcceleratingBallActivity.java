package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myanimationset.MyView.AcceleratingBallView;
import com.example.myanimationset.R;

public class AcceleratingBallActivity extends AppCompatActivity {

    private AcceleratingBallView mAcceleratingBallView;
    private EditText et_progress;
    private Button btn_setProgress;
    private Button btn_setProgressWithAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerating_ball);

        //初始化控件
        mAcceleratingBallView = findViewById(R.id.AcceleratingBallView);
        et_progress = findViewById(R.id.et_progress);
        btn_setProgress = findViewById(R.id.btn_setProgress);
        btn_setProgressWithAnim = findViewById(R.id.btn_setProgressWithAnim);

        btn_setProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float progress = Float.parseFloat(et_progress.getText().toString());
                    mAcceleratingBallView.setProgress(progress);
                }catch (Exception e){
                    Toast.makeText(AcceleratingBallActivity.this, "请输入进度！", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_setProgressWithAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    float progress = Float.parseFloat(et_progress.getText().toString());
                    mAcceleratingBallView.setProgressWithAnim(progress);
                }catch (Exception e){
                    Toast.makeText(AcceleratingBallActivity.this, "请输入进度！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myanimationset.MyView.HintDialog;
import com.example.myanimationset.R;

public class HintDialogActivity extends AppCompatActivity {

    private Button btn_showHintDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint_dialog);

        btn_showHintDialog = findViewById(R.id.btn_showHintDialog);
        btn_showHintDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HintDialog hintDialog = new HintDialog(HintDialogActivity.this,R.style.hintDialog);
                hintDialog.setContent("hahahah");
                hintDialog.setOnClickBtnListener(new HintDialog.yesOnClickListener() {
                    @Override
                    public void clickYes() {
                        hintDialog.dismiss();
                    }
                });
                hintDialog.show();
            }
        });
    }
}

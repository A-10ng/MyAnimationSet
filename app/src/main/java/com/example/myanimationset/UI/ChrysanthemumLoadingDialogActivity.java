package com.example.myanimationset.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myanimationset.MyView.ChrysanthemumLoadingDialog;
import com.example.myanimationset.R;

public class ChrysanthemumLoadingDialogActivity extends AppCompatActivity {

    private Button btn_loadDialog;
    private ChrysanthemumLoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrysanthemum_loading_dialog);

        btn_loadDialog = findViewById(R.id.btn_loadDialog);
        btn_loadDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ChrysanthemumLoadingDialog(ChrysanthemumLoadingDialogActivity.this);
                dialog.show();
            }
        });
    }
}

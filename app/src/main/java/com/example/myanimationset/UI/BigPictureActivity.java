package com.example.myanimationset.UI;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myanimationset.MyView.BigView;
import com.example.myanimationset.R;

import java.io.IOException;
import java.io.InputStream;

public class BigPictureActivity extends AppCompatActivity {

    private BigView bigView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_picture);

        bigView = findViewById(R.id.bigView);
        InputStream is = null;
        try {
            is = getAssets().open("bigview.jpg");
            bigView.setImage(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

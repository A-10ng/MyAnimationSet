package com.example.myanimationset.UI;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myanimationset.R;

public class RatingBarActivity extends AppCompatActivity {

    private RatingBar mRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_bar);

        mRatingBar = findViewById(R.id.ratingBar);
        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast toast = Toast.makeText(RatingBarActivity.this,rating+"",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.show();
            }
        });
    }
}

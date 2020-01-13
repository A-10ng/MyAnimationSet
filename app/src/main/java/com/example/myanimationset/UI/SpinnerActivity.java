package com.example.myanimationset.UI;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.myanimationset.Bean.SpinnerBean;
import com.example.myanimationset.MyView.SpinnerPopupWindow;
import com.example.myanimationset.R;

import java.util.ArrayList;
import java.util.List;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, PopupWindow.OnDismissListener {

    private static final String TAG = "SpinnerActivity";
    private SpinnerPopupWindow spinnerPopupWindow;
    private TextView tv_spinner;
    private List<SpinnerBean> spinnerData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        initSpinner();
    }

    private void initSpinner() {
        tv_spinner = findViewById(R.id.spinner);
        tv_spinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSpinner();
            }
        });
        initSpinnerData();
        spinnerPopupWindow = new SpinnerPopupWindow(spinnerData, this, this);
        spinnerPopupWindow.setOnDismissListener(this);
    }

    private void setSpinner() {
        spinnerPopupWindow.setWidth(tv_spinner.getWidth());
        spinnerPopupWindow.showAsDropDown(tv_spinner);
        setTextViewRightImage(R.drawable.publish_arrow_up);
        tv_spinner.setBackgroundResource(R.drawable.shape_spinner_title_gray);
    }

    private void initSpinnerData() {
        spinnerData = new ArrayList<>();
        SpinnerBean bean = new SpinnerBean();
        bean.setIcon(R.drawable.shopping);
        bean.setTypeText("代购");
        spinnerData.add(bean);

        SpinnerBean bean1 = new SpinnerBean();
        bean1.setIcon(R.drawable.deliverly);
        bean1.setTypeText("代拿快递");
        spinnerData.add(bean1);

        SpinnerBean bean2 = new SpinnerBean();
        bean2.setIcon(R.drawable.others);
        bean2.setTypeText("其他代办");
        spinnerData.add(bean2);
    }

    //设置下拉框右边的图标：向上或者向下的箭头
    private void setTextViewRightImage(int resId) {
        Drawable drawable = getResources().getDrawable(resId);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        tv_spinner.setCompoundDrawables(null, null, drawable, null);
    }

    //下拉框spinner的item点击事件
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i(TAG, "onItemClick: " + spinnerData.get(position).getTypeText());

        spinnerPopupWindow.dismiss();
        tv_spinner.setText(spinnerData.get(position).getTypeText());
    }

    //下拉框spinner的取消事件
    @Override
    public void onDismiss() {
        setTextViewRightImage(R.drawable.publish_arrow_down);
        tv_spinner.setBackgroundResource(R.drawable.shape_spinner_title_white);
    }
}

package com.example.myanimationset.MyView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myanimationset.R;
import com.example.myanimationset.utils.Utils;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/08/25
 * desc:
 */
public class SimpleLoadingBar extends Dialog {
    private Context context;

    public SimpleLoadingBar( Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = View.inflate(context, R.layout.layout_simple_loading_bar,null);
        setContentView(view);

        setCanceledOnTouchOutside(false);

        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = Utils.dp2px(context,60);
        lp.width = Utils.dp2px(context,60);
        window.setAttributes(lp);
    }
}

package com.example.myanimationset.MyView;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.myanimationset.R;
import com.example.myanimationset.Utils.Utils;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2020/01/08
 * desc:
 */
public class ChrysanthemumLoadingDialog extends Dialog {


    private Context context;

    public ChrysanthemumLoadingDialog(Context context) {
        this(context,0);
    }

    public ChrysanthemumLoadingDialog(Context context, int themeResId) {
        this(context, true,null);
    }

    protected ChrysanthemumLoadingDialog(Context context, boolean cancelable,DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        View view = View.inflate(context, R.layout.layout_chrysanthemum_loadingdialog,null);
        setContentView(view);

        Window window = getWindow();
        window.setBackgroundDrawableResource(R.color.transparent);
//        window.setLayout(Utils.dp2px(context,60),Utils.dp2px(context,60));
    }
}

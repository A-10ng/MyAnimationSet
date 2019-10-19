package com.example.myanimationset.MyView;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.myanimationset.R;
import com.example.myanimationset.Utils.Utils;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/08/25
 * desc:
 */
public class HintDialog extends Dialog {

    private Context context;
    private Button btn_sure;
    private TextView tv_content;
    private View view;
    private yesOnClickListener listener;
    private String content;

    public HintDialog( Context context) {
        super(context);
        this.context = context;
    }

    public HintDialog( Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        Log.i("HintDialog", "Constructor: ");
    }

    protected HintDialog(Context context, boolean cancelable, DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("HintDialog", "onCreate: ");
        super.onCreate(savedInstanceState);
        view = View.inflate(context, R.layout.layout_hint_dialog,null);
        setContentView(view);

        //设置宽高为xml文件中设置的宽高
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = Utils.dp2px(context,190);
        lp.width = Utils.dp2px(context,180);
        window.setAttributes(lp);

        //设置点击对话框外的区域时对话框不消失
        setCanceledOnTouchOutside(false);

        btn_sure = view.findViewById(R.id.btn_sure);
        tv_content = view.findViewById(R.id.tv_content);

        if (!TextUtils.isEmpty(content)){
            tv_content.setText(content);
        }

        Log.i("HintDialog", "onCreate: after setText");

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickYes();
            }
        });
    }

    public void setContent(String content){
        this.content = content;
    }

    public void setOnClickBtnListener(yesOnClickListener listener){
        this.listener = listener;
    }

    public interface yesOnClickListener{
        void clickYes();
    }
}

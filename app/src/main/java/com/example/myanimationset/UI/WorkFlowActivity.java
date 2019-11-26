package com.example.myanimationset.UI;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.myanimationset.DesignMode.WorkFlow.Node;
import com.example.myanimationset.DesignMode.WorkFlow.WorkFlow;
import com.example.myanimationset.DesignMode.WorkFlow.WorkNode;
import com.example.myanimationset.DesignMode.WorkFlow.Worker;
import com.example.myanimationset.R;

public class WorkFlowActivity extends AppCompatActivity {

    public static final int AD_NODEID = 1;
    public static final int DIALOG_NODEID = 10;
    public static final int REGISTER_PROTOCOL_NODEID = 20;
    private static final String TAG = "WorkFlowActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_flow);

        WorkFlow workFlow = new WorkFlow.Builder()
                .withNode(getADWorkNode())
                .withNode(getDialogWorkNode())
                .withNode(getRegisterProtocolWorkNode())
                .create();
        workFlow.start();
    }

    private WorkNode getADWorkNode() {
        return WorkNode.build(AD_NODEID, new Worker() {
            @Override
            public void doWork(final Node currentNode) {
                Log.i(TAG, "this is getADWorkNode!");
                currentNode.onCompleted();
            }
        });
    }

    private WorkNode getDialogWorkNode() {
        return WorkNode.build(DIALOG_NODEID, new Worker() {
            @Override
            public void doWork(final Node currentNode) {
                AlertDialog dialog = new AlertDialog.Builder(WorkFlowActivity.this)
                        .setTitle("This is title")
                        .setMessage("message 1 ")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                currentNode.onCompleted();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }

    private WorkNode getRegisterProtocolWorkNode() {
        return WorkNode.build(REGISTER_PROTOCOL_NODEID, new Worker() {
            @Override
            public void doWork(final Node currentNode) {
                AlertDialog dialog = new AlertDialog.Builder(WorkFlowActivity.this)
                        .setTitle("This is 协议")
                        .setMessage("用户注册协议 ")
                        .setPositiveButton("确定",null)
                        .setOnDismissListener(new DialogInterface.OnDismissListener() {
                            @Override
                            public void onDismiss(DialogInterface dialog) {
                                currentNode.onCompleted();
                            }
                        })
                        .create();
                dialog.show();
            }
        });
    }
}

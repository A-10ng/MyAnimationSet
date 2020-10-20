package com.example.myanimationset.DesignMode.WorkFlow;

import android.util.Log;
import android.util.SparseArray;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/26
 * desc:
 */
public class WorkFlow {
    private SparseArray<WorkNode> flowNodes;
    private static final String TAG = "WorkFlow";

    public WorkFlow(SparseArray<WorkNode> flowNodes){
        this.flowNodes = flowNodes;
    }

    public void start(){
        startWithNode(flowNodes.keyAt(0));
    }

    private void startWithNode(int startNodeId) {
        final int startIndex = flowNodes.indexOfKey(startNodeId);
        Log.i(TAG, "startWithNode >>> startNodeId:"+startNodeId+ " startIndex:"+startIndex);
        WorkNode startNode = flowNodes.valueAt(startIndex);
        startNode.doWork(new WorkNode.WorkCallback() {
            @Override
            public void onWorkCompleted() {
                findAndExecuteNextNodeIfExist(startIndex);
            }
        });
    }

    private void findAndExecuteNextNodeIfExist(final int startIndex) {
        final int nextIndex = startIndex + 1;
        Log.i(TAG, "fAENodeIfExist >>> startIndex:"+startIndex+ " nextIndex:"+nextIndex);
        WorkNode nextNode = flowNodes.valueAt(nextIndex);
        if (nextNode != null){
            nextNode.doWork(new WorkNode.WorkCallback() {
                @Override
                public void onWorkCompleted() {
                    findAndExecuteNextNodeIfExist(nextIndex);
                }
            });
        }
    }

    public static class Builder{

        private SparseArray<WorkNode> f;

        public Builder(){
            f = new SparseArray<>();
        }

        public Builder withNode(WorkNode node){
            f.append(node.getId(),node);
            return this;
        }

        public WorkFlow create(){
            return new WorkFlow(f);
        }
    }
}

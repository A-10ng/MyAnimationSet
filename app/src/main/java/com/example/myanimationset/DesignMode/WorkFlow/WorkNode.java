package com.example.myanimationset.DesignMode.WorkFlow;

/**
 * author：LongSh1z
 * email：2674461089@qq.com
 * time：2019/11/26
 * desc:
 */
public class WorkNode implements Node {

    private int nodeId;
    private Worker worker;
    private WorkCallback workCallback;

    @Override
    public int getId() {
        return nodeId;
    }

    public static WorkNode build(int nodeId,Worker worker){
        return new WorkNode(nodeId,worker);
    }

    private WorkNode(int nodeId,Worker worker){
        this.nodeId = nodeId;
        this.worker = worker;
    }

    //供WorkFlow调用
    public void doWork(WorkCallback workCallback){
        this.workCallback = workCallback;
        worker.doWork(this);
    }

    @Override
    public void onCompleted() {
        if (workCallback != null){
            workCallback.onWorkCompleted();
        }
    }

    public interface WorkCallback{
        void onWorkCompleted();
    }
}

package com.hrishikeshmishra.jc.kmeans.concurrent;

import com.hrishikeshmishra.jc.kmeans.commons.DocumentCluster;

import java.util.concurrent.RecursiveAction;

/**
 * Created by hrishikesh.mishra on 13/10/16.
 */
public class UpdateTask extends RecursiveAction {
    private DocumentCluster[] clusters;
    private int start;
    private int end;
    private int maxSize;

    public UpdateTask(DocumentCluster[] clusters, int start, int end, int maxSize) {
        this.clusters = clusters;
        this.start = start;
        this.end = end;
        this.maxSize = maxSize;
    }


    public DocumentCluster[] getClusters() {
        return clusters;
    }

    public void setClusters(DocumentCluster[] clusters) {
        this.clusters = clusters;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    protected void compute(){
        if(end - start <= maxSize){
            for (int i=start; i<end; i++){
                DocumentCluster cluster = clusters[i];
                cluster.calculateCentroid();
            }
        }else {
            int mid = (start + end) /2;
            UpdateTask task1 = new UpdateTask(clusters, start, mid, maxSize);
            UpdateTask task2 = new UpdateTask(clusters, mid, end, maxSize);
            invokeAll(task1, task2);
        }
    }
}

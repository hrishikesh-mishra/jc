package com.hrishikeshmishra.jc.kmeans.concurrent;

import com.hrishikeshmishra.jc.kmeans.commons.DistanceMeasurer;
import com.hrishikeshmishra.jc.kmeans.commons.Document;
import com.hrishikeshmishra.jc.kmeans.commons.DocumentCluster;

import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hrishikesh.mishra on 13/10/16.
 */
public class AssignmentTask extends RecursiveAction {

    private DocumentCluster[] clusters;
    private Document[] documents;
    private int start;
    private int end;
    private AtomicInteger numChanges;
    private int maxSize;

    public AssignmentTask(DocumentCluster[] clusters, Document[] documents, int start, int end, AtomicInteger numChanges, int maxSize) {
        this.clusters = clusters;
        this.documents = documents;
        this.start = start;
        this.end = end;
        this.numChanges = numChanges;
        this.maxSize = maxSize;
    }

    public DocumentCluster[] getClusters() {
        return clusters;
    }

    public void setClusters(DocumentCluster[] clusters) {
        this.clusters = clusters;
    }

    public Document[] getDocuments() {
        return documents;
    }

    public void setDocuments(Document[] documents) {
        this.documents = documents;
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

    public AtomicInteger getNumChanges() {
        return numChanges;
    }

    public void setNumChanges(AtomicInteger numChanges) {
        this.numChanges = numChanges;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    protected void compute(){
        if(end - start <= maxSize ){
            for (int i=start; i<end; i++){
                Document document = documents[i];
                double distance = Double.MAX_VALUE;
                DocumentCluster selectedCluster = null;
                for (DocumentCluster cluster : clusters){
                    double curDistance = DistanceMeasurer.euclideanDistance(document.getWords(), cluster.getCentroid());
                    if(curDistance < distance){
                        distance = curDistance;
                        selectedCluster = cluster;
                    }
                }

                selectedCluster.addDocument(document);
                boolean result = document.setCluster(selectedCluster);
                if(result)
                    numChanges.incrementAndGet();
            }
        }else {
            int mid = (start + end) /2;
            AssignmentTask task1 = new AssignmentTask(clusters, documents, start, mid, numChanges, maxSize);
            AssignmentTask task2 = new AssignmentTask(clusters, documents,  mid, end,  numChanges, maxSize);

            invokeAll(task1, task2);
        }
    }
}

package com.hrishikeshmishra.jc.kmeans.concurrent;

import com.hrishikeshmishra.jc.kmeans.commons.Document;
import com.hrishikeshmishra.jc.kmeans.commons.DocumentCluster;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by hrishikesh.mishra on 13/10/16.
 */
public class ConcurrentKMeans {

    public static DocumentCluster[] calculate(Document []documents, int numCluster, int vocSize, int seed, int maxSize){

        DocumentCluster[] clusters = new DocumentCluster[numCluster];
        Random random = new Random(seed);
        for (int i=0 ; i<numCluster; i++){
            clusters[i] = new DocumentCluster(vocSize);
            clusters[i].initialize(random);
        }

        boolean change = true;
        ForkJoinPool pool = new ForkJoinPool();
        int numSteps = 0;
        while (change){
            change = assignment(clusters, documents, maxSize, pool);
            update(clusters, maxSize, pool);
            numSteps++;
        }

        pool.shutdown();
        System.out.println("Number of steps:"  + numSteps);
        return clusters;
    }

    private static void update(DocumentCluster[] clusters, int maxSize, ForkJoinPool pool) {
        UpdateTask updateTask = new UpdateTask(clusters, 0, clusters.length, maxSize);
        pool.execute(updateTask);
        updateTask.join();
    }

    private static boolean assignment(DocumentCluster[] clusters, Document[] documents, int maxSize, ForkJoinPool pool) {
        boolean change = false;

        for (DocumentCluster cluster: clusters){
            cluster.clearClusters();
        }

        AtomicInteger numChanges = new AtomicInteger(0);
        AssignmentTask task = new AssignmentTask(clusters, documents, 0, documents.length, numChanges, maxSize);
        pool.execute(task);
        task.join();

        System.out.println("Number of changes : " + numChanges);
        return  numChanges.get()>0;
    }
}

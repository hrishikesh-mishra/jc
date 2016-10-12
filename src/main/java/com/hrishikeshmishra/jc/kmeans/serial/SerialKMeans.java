package com.hrishikeshmishra.jc.kmeans.serial;


import com.hrishikeshmishra.jc.kmeans.commons.DistanceMeasurer;

import java.util.Random;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class SerialKMeans {

    public static DocumentCluster[] calculate(Document[] documents, int clusterCount, int vocSize, int seed ){
        DocumentCluster clusters [] = new DocumentCluster[clusterCount];
        Random random = new Random(seed);

        for (int i=0; i< clusterCount; i++){
            clusters[i] = new DocumentCluster(vocSize);
            clusters[i].initialize(random);
        }

        boolean change = true;
        int numStep =0;
        while (change){
            change = assignment(clusters, documents);
            update(clusters);
            numStep++;
        }

        System.out.println("Num of steps: " + numStep);
        return clusters;

    }

    private static void update(DocumentCluster[] clusters) {
        for (DocumentCluster cluster: clusters){
            cluster.calculateCentroid();
        }
    }

    private static boolean assignment(DocumentCluster[] clusters, Document[] documents) {


        for (DocumentCluster cluster: clusters){
            cluster.clearClusters();
        }

        int numChanges = 0;
        for (Document document : documents){
            double distance = Double.MAX_VALUE;
            DocumentCluster selectedCluster = null;

            for (DocumentCluster cluster : clusters){
                double curDistance = DistanceMeasurer.euclideanDistance(document.getWords(), cluster.getCentroid());

                if (curDistance < distance){
                    distance = curDistance;
                    selectedCluster = cluster;
                }
            }

            selectedCluster.addDocument(document);
            boolean result = document.setCluster(selectedCluster);
            if(result)
                numChanges++;

        }

        System.out.println("Num of changes :" + numChanges);
        return  numChanges >0;


    }
}

package com.hrishikeshmishra.jc.kmeans.commons;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class DistanceMeasurer {
    public static double euclideanDistance(Word[] words, double [] centroid){
        double distance = 0;

        int wordIndex = 0;
        for (int i = 0; i < centroid.length; i++) {
            if ((wordIndex < words.length)
                    && (words[wordIndex].getIndex() == i)) {
                distance += Math.pow(
                        (words[wordIndex].getTfIdf() - centroid[i]), 2);
                wordIndex++;
            } else {
                distance += centroid[i] * centroid[i];
            }
        }

        return Math.sqrt(distance);
    }
}

package com.hrishikeshmishra.jc.kmeans.commons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class DocumentCluster {

    private Collection<Document> documents ;
    private double centroid [];

    public DocumentCluster( int size) {
        this.documents = new ArrayList<>();
        this.centroid = new double[size];
    }

    public DocumentCluster(Collection<Document> documents, int size) {
        this.documents = documents;
        this.centroid = new double[size];
    }

    public void addDocument (Document document){
        documents.add(document);
    }

    public void clearClusters(){
        documents.clear();
    }

    public Collection<Document> getDocuments() {
        return documents;
    }

    public double[] getCentroid() {
        return centroid;
    }

    public void calculateCentroid(){
        Arrays.fill(centroid, 0);

        for (Document document : documents) {
            Word vector[] = document.getWords();

            for (Word word : vector) {
                centroid[word.getIndex()] += word.getTfIdf();
            }
        }

        for (int i = 0; i < centroid.length; i++) {
            centroid[i] /= documents.size();
        }
    }

    public void  initialize(Random random){
        for (int i=0; i<centroid.length; i++){
            centroid[i] = random.nextDouble();
        }
    }

    public int getDocumentCount() {
        return documents.size();
    }
}

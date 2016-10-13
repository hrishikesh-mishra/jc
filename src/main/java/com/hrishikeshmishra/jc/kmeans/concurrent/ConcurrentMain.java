package com.hrishikeshmishra.jc.kmeans.concurrent;

import com.hrishikeshmishra.jc.kmeans.commons.Document;
import com.hrishikeshmishra.jc.kmeans.commons.DocumentCluster;
import com.hrishikeshmishra.jc.kmeans.commons.VocabularyLoader;
import com.hrishikeshmishra.jc.kmeans.serial.DocumentLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hrishikesh.mishra on 13/10/16.
 */
public class ConcurrentMain {

    public static void main(String[] args) throws IOException {
        Path pathVoc = Paths.get("data/movies.words");
        Map<String, Integer> vocIndex= VocabularyLoader.load(pathVoc);
        System.out.println("Voc Size: "+vocIndex.size());

        Path pathDocs = Paths.get("data/movies.data");
        Document[] documents= DocumentLoader.load(pathDocs, vocIndex);
        System.out.println("Document Size: "+documents.length);

        int K = 3;
        int SEED = 10;
        int MAX_SIZE = 20;

        Date start, end;
        start = new Date();
        DocumentCluster[] clusters = ConcurrentKMeans.calculate(documents, K, vocIndex.size(), SEED, MAX_SIZE );
        end = new Date();
        System.out.println("K: " + K + "; SEED: " + SEED+"; MAX_SIZE: "+MAX_SIZE);
        System.out.println("Execution Time: " + (end.getTime() - start.getTime()));

        System.out.println(
                Arrays.stream(clusters).map(DocumentCluster::getDocumentCount).sorted(Comparator.reverseOrder())
                        .map(Object::toString).collect(Collectors.joining(", ", "Cluster sizes: ", "")));
    }
}

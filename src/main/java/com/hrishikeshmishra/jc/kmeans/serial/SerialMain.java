package com.hrishikeshmishra.jc.kmeans.serial;

import com.hrishikeshmishra.jc.kmeans.commons.Document;
import com.hrishikeshmishra.jc.kmeans.commons.DocumentCluster;
import com.hrishikeshmishra.jc.kmeans.commons.VocabularyLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by hrishikesh.mishra on 12/10/16.
 */
public class SerialMain {

    public static void main(String[] args) throws IOException {
        Path pathVoc = Paths.get("data", "movies.words");
        Map<String , Integer> vocIndex = VocabularyLoader.load(pathVoc);
        System.out.println("Voc size: " + vocIndex.size());
        Path pathDoc = Paths.get("data", "movies.data");
        Document[] documents = DocumentLoader.load(pathDoc, vocIndex);

        int K = 3;
        int SEED = 10;

        Date start, end;
        start = new Date();
        DocumentCluster[] clusters = SerialKMeans.calculate(documents, K, vocIndex.size(), SEED);
        end = new Date();



        System.out.println("K : " + K + ", SEED:" + SEED);
        System.out.println("Execution Time : " + (end.getTime() - start.getTime()));
        System.out.println(
                Arrays.stream(clusters).map(DocumentCluster::getDocumentCount).sorted(Comparator.reverseOrder())
                        .map(Object::toString).collect(Collectors.joining(", ", "Cluster sizes: ", "")));

    }
}

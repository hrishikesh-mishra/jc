package com.hrishikeshmishra.jc.datafilter.serial;

import com.hrishikeshmishra.jc.datafilter.commons.CensusData;
import com.hrishikeshmishra.jc.datafilter.commons.CensusDataLoader;
import com.hrishikeshmishra.jc.datafilter.commons.FilterData;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by hrishikesh.mishra on 14/10/16.
 */
public class SerialSearchMain {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("data","census-income.data");

        CensusData data[] = CensusDataLoader.load(path);
        System.out.println("Number of items: " + data.length);

        Date start, end;

        // Test 1: findFirst, exists, in the first ones
        List<FilterData> filters = new ArrayList<>();
        FilterData filter = new FilterData();
        filter.setAttributeId(32);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(31);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(1);
        filter.setAttributeValue("Not in universe");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(14);
        filter.setAttributeValue("Not in universe");
        filters.add(filter);

        start = new Date();
        CensusData result = SerialSearch.findAny(data, filters);
        System.out.println("Test 1 - Result: " + (!Objects.isNull(result) ?result.getReasonForUnemployment() : null));
        end = new Date();
        System.out.println("Test 1- Execution Time: " + (end.getTime() - start.getTime()));

        // Test 2: findFirst, exists, in the last ones
        filters = new ArrayList<>();
        filter = new FilterData();
        filter.setAttributeId(32);
        filter.setAttributeValue("United-States");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(31);
        filter.setAttributeValue("Greece");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(1);
        filter.setAttributeValue("Private");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(14);
        filter.setAttributeValue("Not in universe");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(0);
        filter.setAttributeValue("62");
        filters.add(filter);

        start = new Date();
        result = SerialSearch.findAny(data, filters);
        System.out.println("Test 2 - Result: "  + (!Objects.isNull(result) ?result.getReasonForUnemployment() : null));
        end = new Date();
        System.out.println("Test 2- Execution Time: "  + (end.getTime() - start.getTime()));

        // Test 3 Doesn't exists
        filters = new ArrayList<>();
        filter = new FilterData();
        filter.setAttributeId(32);
        filter.setAttributeValue("XXXX");
        filters.add(filter);

        start = new Date();
        result = SerialSearch.findAny(data, filters);
        if (result == null) {
            System.out.println("Test 3 - Result: " + result);
        } else {
            System.out.println("Test 3 - Result: " +(!Objects.isNull(result) ?result.getReasonForUnemployment() : null));
        }
        end = new Date();
        System.out.println("Test 3 - Execution Time: "  + (end.getTime() - start.getTime()));

        // Test 4: Error Individual
        filters = new ArrayList<>();
        filter = new FilterData();
        filter.setAttributeId(0);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);

        start = new Date();
        try {
            result = SerialSearch.findAny(data, filters);
            System.out.println("Test 4 - Results: " + (!Objects.isNull(result) ?result.getCitizenship() : null));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        end = new Date();
        System.out.println("Test 4 - Execution Time: "
                + (end.getTime() - start.getTime()));

        // Test 5: List
        filters = new ArrayList<>();
        filter = new FilterData();
        filter.setAttributeId(32);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(31);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(1);
        filter.setAttributeValue("Not in universe");
        filters.add(filter);
        filter = new FilterData();
        filter.setAttributeId(14);
        filter.setAttributeValue("Not in universe");
        filters.add(filter);

        start = new Date();
        List<CensusData> results = SerialSearch.findAll(data, filters);
        System.out.println("Test 5 - Results: " + results.size());
        end = new Date();
        System.out.println("Test 5 - Execution Time: "  + (end.getTime() - start.getTime()));

        // Test 6: Error List
        filters = new ArrayList<FilterData>();
        filter = new FilterData();
        filter.setAttributeId(0);
        filter.setAttributeValue("Dominican-Republic");
        filters.add(filter);

        start = new Date();
        try {
            results = SerialSearch.findAll(data, filters);
            System.out.println("Test 6 - Results: " + results.size());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        end = new Date();
        System.out.println("Test 6 - Execution Time: "  + (end.getTime() - start.getTime()));
    }
}

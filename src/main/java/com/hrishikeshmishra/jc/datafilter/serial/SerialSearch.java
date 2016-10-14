package com.hrishikeshmishra.jc.datafilter.serial;

import com.hrishikeshmishra.jc.datafilter.commons.CensusData;
import com.hrishikeshmishra.jc.datafilter.commons.Filter;
import com.hrishikeshmishra.jc.datafilter.commons.FilterData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrishikesh.mishra on 14/10/16.
 */
public class SerialSearch {

    public static CensusData findAny(CensusData[] data, List<FilterData> filters){
        int index =0 ;
        for (CensusData censusData : data){
            if (Filter.filter(censusData, filters)){
                System.out.println("Found : " + index);
                return censusData;
            }
            index++;
        }

        return null;
    }

    public static List<CensusData> findAll(CensusData[] data, List<FilterData> filters){
        List<CensusData> result = new ArrayList<>();
        for (CensusData censusData : data){
            if (Filter.filter(censusData, filters)){
                result.add(censusData);
            }
        }

        return result;
    }
}

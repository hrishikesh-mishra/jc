package com.hrishikeshmishra.jc.periodictasks;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
//@// TODO: 09/10/16 implmenent this class

public class RSSDataCapturer {

    private String name;

    public RSSDataCapturer(String name) {
        this.name = name;
    }

    public List<CommonInformationItem> load(String url) {
        List<CommonInformationItem> list = new ArrayList<>();
        list.add(new CommonInformationItem(String.valueOf(getRandId()), "Source :" + getRandId() , name ));
        list.add(new CommonInformationItem(String.valueOf(getRandId()), "Source :" + getRandId() , name ));
        list.add(new CommonInformationItem(String.valueOf(getRandId()), "Source :" + getRandId() , name ));
        list.add(new CommonInformationItem(String.valueOf(getRandId()), "Source :" + getRandId() , name ));
        return list;
    }

    public int getRandId(){
        return ThreadLocalRandom.current().nextInt(0, 900000);
    }


}

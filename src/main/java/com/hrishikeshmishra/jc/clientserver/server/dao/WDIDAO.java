package com.hrishikeshmishra.jc.clientserver.server.dao;

/**
 * <p>
 *     @link http://data.worldbank.org./data-catalog/world-development-indicators
 * </p>
 */
public class WDIDAO {

    private static WDIDAO instance = new WDIDAO();
    private WDIDAO() {
    }

    public static WDIDAO getDAO(){
        return instance;
    }

    public String query (String codCountry, String codIndicator){
        return "DummyQuery Response.";
    }

    public String query (String codCountry, String codIndicator, short year){
        return "DummyQuery Response.";
    }

    public String report (String codIndicator){
        return "Dummy Report";
    }


}

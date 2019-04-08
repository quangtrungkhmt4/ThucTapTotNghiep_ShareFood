package com.example.sharefood.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class API {

    public static final String API_LOCATION = "https://geocoder.api.here.com/6.2/geocode.json?app_id=crNVEMg0fda61lPd8Xqp&app_code=heHRyrE91izOL_Vpo_YFRw&searchtext=";
    private static final String IP = "http://192.168.15.100:9999/v1/";
    //price
    public static final String COUNT_PRICE = IP + "prices/count";
    public static final String PRICE_WITH_PAGE = IP + "prices";

}

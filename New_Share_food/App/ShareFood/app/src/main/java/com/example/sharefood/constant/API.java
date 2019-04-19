package com.example.sharefood.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class API {

    public static final String API_LOCATION = "https://geocoder.api.here.com/6.2/geocode.json?app_id=crNVEMg0fda61lPd8Xqp&app_code=heHRyrE91izOL_Vpo_YFRw&searchtext=";
    private static final String IP = "http://192.168.2.97:9998/v1/";
    public static final String STORAGE = "http://192.168.2.97/storage_share_food/";
    //price
    public static final String COUNT_PRICE = IP + "prices/count";
    public static final String SEARCH_PRICE = IP + "prices/search";
    public static final String PRICE_WITH_PAGE = IP + "prices";

    //province
    public static final String GET_ALL_PROVINCE = IP + "provinces";

    //user
    public static final String LOGIN = IP + "users";

    //category
    public static final String GET_ALL_CATEGORY = IP + "categories";

}

package com.example.sharefoodmanagement.constant;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class API {

    public static final String API_LOCATION = "https://geocoder.api.here.com/6.2/geocode.json?app_id=crNVEMg0fda61lPd8Xqp&app_code=heHRyrE91izOL_Vpo_YFRw&searchtext=";
    private static final String IP = "http://192.168.15.107:9999/v1/";
    public static final String STORAGE = "http://192.168.15.107/storage_share_food/";
    //user
    public static final String LOGIN = IP + "users";
    public static final String GET_ALL_USER = IP + "users/all";

    //restaurant
    public static final String GET_ALL_RESTAURANT = IP + "restaurants/getAll";
    public static final String DELETE_RESTAURANT = IP + "restaurants/delete";
    public static final String BLOCK_RESTAURANT = IP + "restaurants/lock";

    //image
    public static final String GET_ALL_IMAGE = IP + "images";
}

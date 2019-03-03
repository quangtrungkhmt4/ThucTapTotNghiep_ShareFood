package com.example.share_food.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Restaurant {

    private int id;
    private String name;
    private String image;
    private String latLong;
    private int province;
}

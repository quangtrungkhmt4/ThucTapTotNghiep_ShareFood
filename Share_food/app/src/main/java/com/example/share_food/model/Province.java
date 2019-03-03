package com.example.share_food.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Province {
    private int id;
    private String name;
    private String info;
    private String latLong;
}

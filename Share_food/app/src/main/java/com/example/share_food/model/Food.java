package com.example.share_food.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Food implements Serializable {

    private int id;
    private String name;
    private String image;
    private String recipe;
    private int idCate;
    private String desc;

}

package com.example.sharefood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food extends AbstractModel{
    private int id_food;
    private String name;
    private String description;
    private String image;
    private String recipe;
    private Category category;
}

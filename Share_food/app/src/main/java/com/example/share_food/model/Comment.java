package com.example.share_food.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Comment {

    private int id;
    private String name;
    private String text;
}

package com.example.share_food.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int id;
    private String userName;
    private String password;
    private String name;
    private int permission;
    private int isOnline;
}

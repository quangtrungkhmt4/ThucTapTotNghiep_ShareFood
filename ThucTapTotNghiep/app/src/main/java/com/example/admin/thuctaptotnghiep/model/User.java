package com.example.admin.thuctaptotnghiep.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private int idUser;
    private String userName;
    private String password;
    private String name;
    private int permission;
    private int isOnline;
}

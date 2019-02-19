package com.example.admin.thuctaptotnghiep.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Category implements Serializable {
    private int id;
    private String name;
}

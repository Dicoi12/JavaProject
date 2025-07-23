package com.example.ImcBeProj.models;

import org.springframework.data.annotation.Id;

public class Category {
    @Id
    private Short id;
    private String name;

    public Category(String name, Short id) {
        this.name = name;
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

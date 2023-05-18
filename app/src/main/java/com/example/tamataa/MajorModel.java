package com.example.tamataa;

public class MajorModel {
    private String nameEng;
    private String name;
    private String description;

    public MajorModel(String nameEng, String name, String description) {
        this.nameEng = nameEng;
        this.name = name;
        this.description = description;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
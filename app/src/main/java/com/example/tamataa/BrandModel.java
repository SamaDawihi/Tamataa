package com.example.tamataa;

public class BrandModel {
    private int brandId;
    private String name;

    public BrandModel(int brandId, String name) {
        this.brandId = brandId;
        this.name = name;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


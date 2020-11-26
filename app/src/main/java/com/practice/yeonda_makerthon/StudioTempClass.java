package com.practice.yeonda_makerthon;

import java.io.Serializable;

public class StudioTempClass implements Serializable {

    private String name, location, introduce, price;

    public StudioTempClass() {
    }

    public StudioTempClass(String name, String location, String introduce, String price) {
        this.name = name;
        this.location = location;
        this.introduce = introduce;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.example.onlinecourses;

public class homeitemmodel {
    String as,image,name,id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAs() {
        return as;
    }

    public void setAs(String as) {
        this.as = as;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public homeitemmodel(String as, String image, String name, String id) {
        this.image = image;
        this.name = name;
        this.as=as;
        this.id=id;
    }
}

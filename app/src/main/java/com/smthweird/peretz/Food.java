package com.smthweird.peretz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Food {

    @SerializedName("id")
    private Integer id;
    @SerializedName("date")
    private String date;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("new")
    private Boolean _new;
    @SerializedName("variations")
    private List<Object> variations;
    @SerializedName("price")
    private Integer price;
    @SerializedName("image_app")
    private String imageApp;
    @SerializedName("image")
    private String image;
    @SerializedName("sort")
    private Integer sort;


    public Food(Integer id, String date, String name, String description, Boolean _new, List<Object> variations, Integer price, String imageApp, String image, Integer sort) {
        super();
        this.id = id;
        this.date = date;
        this.name = name;
        this.description = description;
        this._new = _new;
        this.variations = variations;
        this.price = price;
        this.imageApp = imageApp;
        this.image = image;
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getNew() {
        return _new;
    }

    public List<Object> getVariations() {
        return variations;
    }

    public Integer getPrice() {
        return price;
    }


    public String getImageApp() {
        return imageApp;
    }


    public String getImage() {
        return image;
    }


    public Integer getSort() {
        return sort;
    }


}



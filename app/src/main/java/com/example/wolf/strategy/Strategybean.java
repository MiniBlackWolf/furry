package com.example.wolf.strategy;

import java.io.Serializable;

public class Strategybean implements Serializable {
    private Integer images;
    private String text;

    public Integer getImages() {
        return images;
    }

    public void setImages(Integer images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

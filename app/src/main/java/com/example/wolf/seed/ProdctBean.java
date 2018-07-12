package com.example.wolf.seed;

import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;

import java.util.Map;

public class ProdctBean {
    private int sid;
    private String image;
    private String name;
    private String jiage;
    private String shuliang;
    private String description;
    private int count;
    private Button b1;
    private Button b2;
    private Map<String, TextView> map;

    public ProdctBean() {
    }

    public Map<String, TextView> getMap() {
        return map;
    }

    public void setMap(Map<String, TextView> map) {
        this.map = map;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public String getJiage() {
        return jiage;
    }

    public void setJiage(String jiage) {
        this.jiage = jiage;
    }

    public String getShuliang() {
        return shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public Button getB1() {
        return b1;
    }

    public void setB1(Button b1) {
        this.b1 = b1;
    }

    public Button getB2() {
        return b2;
    }

    public void setB2(Button b2) {
        this.b2 = b2;
    }
}

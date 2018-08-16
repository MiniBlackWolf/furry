package com.example.wolf.pointshop;

import java.io.Serializable;

public class pointbean implements Serializable{


    /**
     * id : 1
     * sname : 油麦菜种子3㎡
     * sprice : 3000.00
     * scontent : 营养价值高水分足
     * snum : 30
     * stype : 7
     * simgui : http://www.cnepltd.com:80/res/seed/45cf2e7b09d74455902feafcb986e7ac20180622110515.png
     * shelf : 1
     * sid : 2
     */

    private int id;
    private String sname;
    private String sprice;
    private String scontent;
    private int snum;
    private int stype;
    private String simgui;
    private int shelf;
    private int sid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSprice() {
        return sprice;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public String getScontent() {
        return scontent;
    }

    public void setScontent(String scontent) {
        this.scontent = scontent;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public int getStype() {
        return stype;
    }

    public void setStype(int stype) {
        this.stype = stype;
    }

    public String getSimgui() {
        return simgui;
    }

    public void setSimgui(String simgui) {
        this.simgui = simgui;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
}

package com.example.wolf.seed;

import java.io.Serializable;

public class myseedbean2 implements Serializable{


    /**
     * fid : A0001
     * uid : 1
     * sfid : null
     * validityPeriod : 2019-07-02 15:06:37.0
     * cameraUrl : null
     * remainM : 7
     * myid : 3
     * sid : 73
     * growDate : 73
     * swoingdate : 1530518243
     * lol : 0
     */

    private String fid;
    private int uid;
    private Object sfid;
    private String validityPeriod;
    private Object cameraUrl;
    private int remainM;
    private int myid;
    private int sid;
    private int growDate;
    private int swoingdate;
    private int lol;

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public Object getSfid() {
        return sfid;
    }

    public void setSfid(Object sfid) {
        this.sfid = sfid;
    }

    public String getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(String validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public Object getCameraUrl() {
        return cameraUrl;
    }

    public void setCameraUrl(Object cameraUrl) {
        this.cameraUrl = cameraUrl;
    }

    public int getRemainM() {
        return remainM;
    }

    public void setRemainM(int remainM) {
        this.remainM = remainM;
    }

    public int getMyid() {
        return myid;
    }

    public void setMyid(int myid) {
        this.myid = myid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getGrowDate() {
        return growDate;
    }

    public void setGrowDate(int growDate) {
        this.growDate = growDate;
    }

    public int getSwoingdate() {
        return swoingdate;
    }

    public void setSwoingdate(int swoingdate) {
        this.swoingdate = swoingdate;
    }

    public int getLol() {
        return lol;
    }

    public void setLol(int lol) {
        this.lol = lol;
    }
}

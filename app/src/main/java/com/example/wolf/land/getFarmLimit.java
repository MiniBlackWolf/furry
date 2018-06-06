package com.example.wolf.land;

public class getFarmLimit {


    /**
     * fid : A0005
     * uid : -1
     * sfid : null
     */

    private String fid;
    private int uid;
    private Object sfid;

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

    @Override
    public String toString() {
        return "getFarmLimit{" +
                "fid='" + fid + '\'' +
                ", uid=" + uid +
                ", sfid=" + sfid +
                '}';
    }
}

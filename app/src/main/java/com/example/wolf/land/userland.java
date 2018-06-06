package com.example.wolf.land;

public class userland {

    /**
     * fid : A0001
     * uid : 2
     * sfid : 1
     * validityperiod : 1589782308000
     */

    private String fid;
    private int uid;
    private String sfid;
    private long validityperiod;

    public userland() {
    }

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

    public String getSfid() {
        return sfid;
    }

    public void setSfid(String sfid) {
        this.sfid = sfid;
    }

    public long getValidityperiod() {
        return validityperiod;
    }

    public void setValidityperiod(long validityperiod) {
        this.validityperiod = validityperiod;
    }
}

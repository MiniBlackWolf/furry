package com.example.wolf.land;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class userland implements  Parcelable {

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
    private String cameraurl;
    private String remainm;

    public userland() {
    }

    public String getCameraurl() {
        return cameraurl;
    }

    public void setCameraurl(String cameraurl) {
        this.cameraurl = cameraurl;
    }

    public String getRemainm() {
        return remainm;
    }

    public void setRemainm(String remainm) {
        this.remainm = remainm;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.fid);
        dest.writeInt(this.uid);
        dest.writeString(this.sfid);
        dest.writeLong(this.validityperiod);
        dest.writeString(this.cameraurl);
        dest.writeString(this.remainm);
    }

    protected userland(Parcel in) {
        this.fid = in.readString();
        this.uid = in.readInt();
        this.sfid = in.readString();
        this.validityperiod = in.readLong();
        this.cameraurl = in.readString();
        this.remainm = in.readString();
    }

    public static final Parcelable.Creator<userland> CREATOR = new Parcelable.Creator<userland>() {
        @Override
        public userland createFromParcel(Parcel source) {
            return new userland(source);
        }

        @Override
        public userland[] newArray(int size) {
            return new userland[size];
        }
    };
}

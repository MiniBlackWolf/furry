package com.example.wolf.seed;

import android.os.Parcel;
import android.os.Parcelable;

public class userseed implements Parcelable {

    /**
     * suid : 1
     * uid : 1
     * sid : 28
     * buycount : 1
     * buydate : 1529650029000
     */

    private int suid;
    private int uid;
    private int sid;
    private int buycount;
    private long buydate;

    public int getSuid() {
        return suid;
    }

    public void setSuid(int suid) {
        this.suid = suid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public int getBuycount() {
        return buycount;
    }

    public void setBuycount(int buycount) {
        this.buycount = buycount;
    }

    public long getBuydate() {
        return buydate;
    }

    public void setBuydate(long buydate) {
        this.buydate = buydate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.suid);
        dest.writeInt(this.uid);
        dest.writeInt(this.sid);
        dest.writeInt(this.buycount);
        dest.writeLong(this.buydate);
    }

    public userseed() {
    }

    protected userseed(Parcel in) {
        this.suid = in.readInt();
        this.uid = in.readInt();
        this.sid = in.readInt();
        this.buycount = in.readInt();
        this.buydate = in.readLong();
    }

    public static final Parcelable.Creator<userseed> CREATOR = new Parcelable.Creator<userseed>() {
        @Override
        public userseed createFromParcel(Parcel source) {
            return new userseed(source);
        }

        @Override
        public userseed[] newArray(int size) {
            return new userseed[size];
        }
    };
}

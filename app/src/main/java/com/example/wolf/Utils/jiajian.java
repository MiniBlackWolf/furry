package com.example.wolf.Utils;

import android.os.Parcel;
import android.os.Parcelable;

public class jiajian implements Parcelable {
    private int d;

    public jiajian() {
    }

    public int getD() {
        return d;
    }

    public void setD(int d) {
        this.d = d;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.d);
    }

    protected jiajian(Parcel in) {
        this.d = in.readInt();
    }

    public static final Parcelable.Creator<jiajian> CREATOR = new Parcelable.Creator<jiajian>() {
        @Override
        public jiajian createFromParcel(Parcel source) {
            return new jiajian(source);
        }

        @Override
        public jiajian[] newArray(int size) {
            return new jiajian[size];
        }
    };
}

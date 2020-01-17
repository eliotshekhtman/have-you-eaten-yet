package com.example.haveyoueatenyet;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

public class Meal implements Parcelable {
    /* Byte = 8 bits
     * short = char = 2 bytes
     * int = float = 4 bytes
     * long = double = 8 bytes
     * boolean = 2 bits
     */
    // assume 88 bytes for 20 char length string
    private String hostName;
    private String mealName;
    // both ids are 8 bytes each
    private long hostId;
    private long mealId;
    // coordinates are 8 bytes each
    private long latitude;
    private long longitude;
    // want to set a character limit - let's say 1 kbyte for now
    private String description;
    private static long counter = 0;

    public Meal(Account account, String mealName,
                long latitude, long longitude, String description) {
        counter++;
        hostName = account.getName();
        hostId = account.getId();
        this.mealName = mealName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        //TODO: online
        mealId = counter;
    }

    public String getMealName() { return mealName; }
    public long getMealId() { return mealId; }
    public String getMealDesc() { return description; }
    public long getLatitude() { return latitude; }
    public long getLongitude() { return longitude; }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(hostName);
        out.writeString(mealName);
        out.writeLong(hostId);
        out.writeLong(mealId);
        out.writeLong(latitude);
        out.writeLong(longitude);
        out.writeString(description);

    }

    public Meal(Parcel in) {
        hostName = in.readString();
        mealName = in.readString();
        hostId = in.readLong();
        mealId = in.readLong();
        latitude = in.readLong();
        longitude = in.readLong();
        description = in.readString();
    }

    public static final Parcelable.Creator<Meal> CREATOR
            = new Parcelable.Creator<Meal>() {
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };
}

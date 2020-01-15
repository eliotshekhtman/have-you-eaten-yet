package com.example.haveyoueatenyet;

public class Meal {
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

    public Meal(Account account, String mealName,
                long latitude, long longitude, String description) {
        hostName = account.getName();
        hostId = account.getId();
        this.mealName = mealName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        //TODO: online
        mealId = 1;
    }

    public String getMealName() { return mealName; }

}

package com.example.haveyoueatenyet;

public class Account {
    private long id;
    private String name;
    private String description;
    private String username;
    private String password;
    private double currLat;
    private double currLong;

    public Account(String name, String username, String password) {
        this.name = name; this.username = username; this.password = password;
        id = hash(username, password);
    }

    public int hash(String username, String password) {
        //TODO: make a better and more secure hash function
        return intifyString(username);
    }

    public int intifyString(String str) {
        int result = 0;
        for(int i = 0; i < str.length(); i++) {
            result = result * 100 + ((int) str.charAt(i));
        }
        return result;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public double getLatitude() { return currLat; }
    public double getLongtiude() { return currLong; }

    public void setName(String name) { this.name = name; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setLatitude(double lat) { currLat = lat; }
    public void setLongitude(double lon) { currLong = lon; }
}

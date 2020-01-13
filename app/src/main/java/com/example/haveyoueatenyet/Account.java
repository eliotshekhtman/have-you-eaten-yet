package com.example.haveyoueatenyet;

public class Account {
    private long id;
    private String name;
    private String username;
    private String password;

    public Account(String name, String username, String password) {
        this.name = name; this.username = username; this.password = password;
        // TODO: figure out how to dynamically set id after online shit is figured out
        id = 1;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
}

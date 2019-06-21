package com.barvius.httpimage;

public class User {
    private String un;
    private long id;

    public User() {
    }

    public User(String un, long id) {
        this.un = un;
        this.id = id;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

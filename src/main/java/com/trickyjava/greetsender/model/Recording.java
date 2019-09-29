package com.trickyjava.greetsender.model;

import org.springframework.data.annotation.Id;

public class Recording {
    @Id
    private String id;

    private String greetingKey;

    private String user;

    private String browserDetails;

    private String ip;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGreetingKey() {
        return greetingKey;
    }

    public void setGreetingKey(String greetingKey) {
        this.greetingKey = greetingKey;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBrowserDetails() {
        return browserDetails;
    }

    public void setBrowserDetails(String browserDetails) {
        this.browserDetails = browserDetails;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}

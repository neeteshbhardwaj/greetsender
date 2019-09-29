package com.trickyjava.greetsender.model;

import org.springframework.data.annotation.Id;

import java.util.List;

public class GreetingCard {
    @Id
    private String id;

    private String view;

    private boolean enabled;

    private List<Page> pages;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }


    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
}

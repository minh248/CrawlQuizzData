package com.company.entity;

public class Item {
    private final int id;
    private String name;
    private String url;
    private static int count = 0;

    public Item(String name, String url) {
        this.id = ++count;
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }
}
